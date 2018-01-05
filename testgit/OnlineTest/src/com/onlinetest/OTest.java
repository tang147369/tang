package com.onlinetest;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import net.sf.json.JSONArray;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.service.HttpGetQuestions;
import com.service.HttpPostAnswer;

public class OTest extends Activity{
	private TableLayout tLayout,counTableLayout;
	private static int ChoID[],JudgeId[];
	private String reQuestions,stuNum,courseName,testTime;//学号、课程名、测试时间
	private Handler handler;
	private boolean flag;//标志，判断是否获取了返回值
	private int width,height;
	private RadioButton radioButtonChoA,radioButtonChoB,radioButtonChoC,radioButtonChoD;//选项
	private Button bt_sub;//提交按钮
	private TextView tvCountTime;//倒计时显示框
	private int recLen = 300;//总的秒数
	private int min = 5;//倒计时分钟数
	private int seconds = 60;//倒计时秒
	private Timer timer = new Timer();  
	private TimerTask task;
	//用于保存作答选谈判断结果
	private StringBuffer choStr = new StringBuffer();
    private StringBuffer judgeStr = new StringBuffer();
	private Vector<SetAndGetChoAnswer> recordCho = new Vector<SetAndGetChoAnswer>();//保存选择题题号，作答，答案
	private Vector<SetAndGetJudgeAnswer> recordJudge = new Vector<SetAndGetJudgeAnswer>();//保存判断题题号，作答，答案
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);//设置页面布局
		Intent intent = getIntent();//获得Intent
		stuNum = intent.getStringExtra("stuNum");//获得学号
		courseName = intent.getStringExtra("courseName");//获得测试课程
		System.out.println("courseName1:"+courseName);
		System.out.println("stuNum:"+stuNum);
		DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）
        height = metric.heightPixels;   // 屏幕高度（像素）
		//代码中控制UI界面
		tLayout = (TableLayout) findViewById(R.id.tableLayout);//获取xml中定义的TableLayout
		//左右边距
		tLayout.setLeft(10);
		tLayout.setRight(10);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				reQuestions = new HttpGetQuestions().send(stuNum,courseName);//开启线程调用网络连接方法获取返回值（题目）
				Message m = handler.obtainMessage();//获取一个Message
				handler.sendMessage(m);//发送消息
				//System.out.println("1"+reQuestions);
			}
		}).start();//开启线程
		//System.out.println("reQuestions:"+reQuestions);
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(reQuestions != null){
					flag = true;
					//从服务端获取题目信息后调用显示函数
					show();
					System.out.println("2"+flag);
				}
				//倒计时模块，当所有内容显示了之后倒计时
				if(flag==true){
					task = new TimerTask() {    
				        @Override    
				        public void run() {    
				   
				            runOnUiThread(new Runnable() {      // UI thread    
				                @Override    
				                public void run() {  
				                	//秒表计数完成归位59
				                	seconds--;
				                	if(seconds<=0){
				                		seconds = 59;
				                    	min--;
				                    }
				                	if(seconds>0 && seconds<10){
				                		tvCountTime.setText("注意,现在离测试结束还有 "+min+" 分 0"+seconds+" 秒");//倒计时变换
				                	}else{
				                		tvCountTime.setText("注意,现在离测试结束还有 "+min+" 分 "+seconds+" 秒");//倒计时变换
				                	}
				                	//时间到
				                    if(min <= 0){ 	
				                        timer.cancel();    
				                        tvCountTime.setVisibility(View.GONE);    
				                    }    
				                }    
				            });    
				        }    
				    };   
				    timer.schedule(task, 1000, 1000);       // timeTask
				}
				super.handleMessage(msg);
			}
		}; 
		//倒计时
		System.out.println("2.5");
	}
	//设置选择题选项
	public Boolean setChoice(int id){
		/*通过Id确定题号，再修改作作答*/
		//判断id为哪个题哪个选项的
		for(int i=0;i<recordCho.size();i++){
			//判断是否有该选项
			if(recordCho.get(i).getChoice(id)!=null){
				String choice = recordCho.get(i).getChoice(id);//返回选项
				recordCho.get(i).setChoose(choice);//设置用户选项
				System.out.println();
				return true;
			}
		}
		return false;
	}
	//设置判断题选项
	public Boolean setJudge(int id){
		for(int i=0;i<recordJudge.size();i++){
			if(recordJudge.get(i).getChoice(id)!=null){
				String choice = recordJudge.get(i).getChoice(id);//返回选项
				recordJudge.get(i).setChoose(choice);
				return true;
			}
		}
		return false;
	}
	//判断是不是所有选项都选中了
	public Boolean isNull(){
		Boolean flagNull = true; 
		//判断选择题是否都选中了
		for(int i=0;i<recordCho.size();i++){
			if(recordCho.get(i).choose==null){
				flagNull = false;
			}
		}
		//判断题是否都选中了
		for(int j=0;j<recordJudge.size();j++){
			if(recordJudge.get(j).choose==null){
				flagNull = false;
			}
		}
		return flagNull;
	}
	//选择题判断题做拼接处理
	public void dealChoAndJudge(){
		//将用户作答每题做拼接处理
		String recordChoStr[] = new String[recordCho.size()];
		for(int i=0;i<recordChoStr.length;i++){
			recordChoStr[i] = recordCho.get(i).num+","+recordCho.get(i).choose+","+recordCho.get(i).answer;
		}
		String recordJudgeStr[] = new String[recordJudge.size()];
		for(int i=0;i<recordJudgeStr.length;i++){
			recordJudgeStr[i] = recordJudge.get(i).num+","+recordJudge.get(i).choose+","+recordJudge.get(i).answer;
		}
		//将选择题和判断题作整体拼接
		for(int i=0;i<recordChoStr.length;i++){
			if(i == recordChoStr.length-1){
				choStr.append(recordChoStr[i]);
			}else{
				choStr.append(recordChoStr[i]).append("|");
			}
		}
		for(int i=0;i<recordJudgeStr.length;i++){
			if(i == recordChoStr.length-1){
				judgeStr.append(recordJudgeStr[i]);
			}else{
				judgeStr.append(recordJudgeStr[i]).append("|");
			}
		}
		/*System.out.println("choStr:"+choStr);
		System.out.println("choJudge:"+choJudge);*/
		//选择题和判断题用空格作拼接并调用http类传输到服务端
	}
	//显示题目
	public void show(){
		System.out.println("3");
		if(flag==true){
			System.out.println("3.5");
			//处理后还原为json数组
			String s[] = reQuestions.split(" ");
			System.out.println("4");
			JSONArray json = JSONArray.fromObject(s);
			
			System.out.println("5");
			//计时栏
			counTableLayout = (TableLayout) findViewById(R.id.tableL);//获取xml中定义的TableLayout(倒计时)
			TableRow tableRowCountTime = new TableRow(this);
			tvCountTime = new TextView(this);//倒计时框
			tvCountTime.setTextColor(Color.RED);//设置字体颜色
			tableRowCountTime.addView(tvCountTime);
			counTableLayout.addView(tableRowCountTime);
			
			/*选择题*/
			TableRow tableRowChoTitle = new TableRow(this);//创建TableRow
			TextView choice = new TextView(this);
			choice.setText("一、选择题");//设置内容
			choice.setTextSize(TypedValue.COMPLEX_UNIT_PX,32);//指定单位
			tableRowChoTitle.addView(choice);//将TextView添加到TableWRow
			tLayout.addView(tableRowChoTitle);//将“选择题”行添加到TableLayout
			ChoID = new int[json.getJSONArray(0).size()];//保存选择题ID，当前时间戳
			
			for(int i=0;i<json.getJSONArray(0).size();i++){
				//题干部分
				TableRow tableRowCho = new TableRow(this);
				LinearLayout linearLayoutCho = new LinearLayout(this);
				TextView questionCho = new TextView(this);//题目名称
				/*Json数组中第一个为选择题，第二个为判断题，选择题包括题目和选项abcd，判断题则有题目信息*/
				//从Json数组中获取选择题题目信息
				questionCho.setText((i+1)+"、"+json.getJSONArray(0).getJSONObject(i).get("choContent"));
				questionCho.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
				questionCho.setSingleLine(false);//设置后可以换行
				questionCho.setWidth(width-10);//边距（不设置边距就无法换行）
				//选项部分
				//选项A
				TableRow tableRowChoA = new TableRow(this);
				LinearLayout linearLayoutChoA = new LinearLayout(this);
				TextView questionChoA = new TextView(this);//选项名称
				//
				questionChoA.setText("A、"+json.getJSONArray(0).getJSONObject(i).get("a"));
				questionChoA.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
				questionChoA.setSingleLine(false);
				questionChoA.setWidth(width-10);//边距
				//选项B
				TableRow tableRowChoB = new TableRow(this);
				LinearLayout linearLayoutChoB = new LinearLayout(this);
				TextView questionChoB = new TextView(this);//选项名称
				questionChoB.setText("B、"+json.getJSONArray(0).getJSONObject(i).get("b"));
				questionChoB.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
				questionChoB.setSingleLine(false);
				questionChoB.setWidth(width-10);//边距
				//选项C
				TableRow tableRowChoC = new TableRow(this);
				LinearLayout linearLayoutChoC = new LinearLayout(this);
				TextView questionChoC = new TextView(this);//选项名称
				questionChoC.setText("C、"+json.getJSONArray(0).getJSONObject(i).get("c"));
				questionChoC.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
				questionChoC.setSingleLine(false);
				questionChoC.setWidth(width-10);//边距
				//选项D
				TableRow tableRowChoD = new TableRow(this);
				LinearLayout linearLayoutChoD = new LinearLayout(this);
				TextView questionChoD = new TextView(this);//选项名称
				questionChoD.setText("D、"+json.getJSONArray(0).getJSONObject(i).get("d"));
				questionChoD.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
				questionChoD.setSingleLine(false);
				questionChoD.setWidth(width-10);//边距
				//答案选项行
				TableRow tableRowChoAnswer = new TableRow(this);
				LinearLayout linearLayoutAnswer = new LinearLayout(this);
				RadioGroup  radioGroupCho = new RadioGroup(this);//答案选项组
				radioGroupCho.setOrientation(0);//0代表横向排列
				//System.out.println("Cho long:"+System.currentTimeMillis());
				ChoID[i] = (int) System.currentTimeMillis();
				radioGroupCho.setId(ChoID[i]);//设置ID为当前时间戳
				//System.out.println("Cho int:"+ChoID[i]);//
				radioButtonChoA = new RadioButton(this);
				radioButtonChoA.setId((int) System.currentTimeMillis());//设置选项id为当前时间戳
				radioButtonChoA.setText("A");
				/*System.out.println("A的ID："+radioButtonChoA.getId());*/
				
				radioButtonChoB = new RadioButton(this);
				radioButtonChoB.setId((int) System.currentTimeMillis());//设置选项id为当前时间戳
				radioButtonChoB.setText("B");
				/*System.out.println("B的ID："+radioButtonChoB.getId());*/
				
				radioButtonChoC = new RadioButton(this);
				radioButtonChoC.setId((int) System.currentTimeMillis());//设置选项id为当前时间戳
				radioButtonChoC.setText("C");
				/*System.out.println("C的ID："+radioButtonChoC.getId());*/
				
				radioButtonChoD = new RadioButton(this);
				radioButtonChoD.setId((int) System.currentTimeMillis());//设置选项id为当前时间戳
				radioButtonChoD.setText("D");
				/*System.out.println("D的ID："+radioButtonChoD.getId());*/
				//集合保存题号，选项id和答案对象
				recordCho.add(new SetAndGetChoAnswer((int)json.getJSONArray(0).getJSONObject(i).get("choId"),radioButtonChoA.getId(),radioButtonChoB.getId(),radioButtonChoC.getId(),radioButtonChoD.getId(),json.getJSONArray(0).getJSONObject(i).get("choAnswer").toString()));
				radioGroupCho.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(RadioGroup arg0, int checkedId) {
						System.out.println("checkedId:"+checkedId);
						// TODO Auto-generated method stub
						setChoice(checkedId);//调用设值函数
						//将每次结果输出
						/*for(int i=0;i<recordCho.size();i++){
							System.out.println("题号："+recordCho.get(i).num+" 选择："+recordCho.get(i).choose+" 答案："+recordCho.get(i).answer);
						}*/
					}
				});
				/*选择题添加到线性布局再将线性布局添加到行中*/
				//题干
				linearLayoutCho.addView(questionCho);
				tableRowCho.addView(linearLayoutCho);//将linearLayoutCho添加到TableRow
				//选项A添加内容组件
				linearLayoutChoA.addView(questionChoA);
				tableRowChoA.addView(linearLayoutChoA);
				//选项B添加内容组件
				linearLayoutChoB.addView(questionChoB);
				tableRowChoB.addView(linearLayoutChoB);
				//选项C添加内容组件
				linearLayoutChoC.addView(questionChoC);
				tableRowChoC.addView(linearLayoutChoC);
				//选项D添加内容组件
				linearLayoutChoD.addView(questionChoD);
				tableRowChoD.addView(linearLayoutChoD);
				//答案按钮添加到组
				radioGroupCho.addView(radioButtonChoA);
				radioGroupCho.addView(radioButtonChoB);
				radioGroupCho.addView(radioButtonChoC);
				radioGroupCho.addView(radioButtonChoD);
				linearLayoutAnswer.addView(radioGroupCho);
				tableRowChoAnswer.addView(linearLayoutAnswer);
				
				//将选择题行添加到TableLayout
				tLayout.addView(tableRowCho);//将题干添加到TableLayout
				tLayout.addView(tableRowChoA);//将选项A行添加到TableLayout
				tLayout.addView(tableRowChoB);//将选项B行添加到TableLayout
				tLayout.addView(tableRowChoC);//将选项B行添加到TableLayout
				tLayout.addView(tableRowChoD);//将选项B行添加到TableLayout
				tLayout.addView(tableRowChoAnswer);//将答案行添加到TableLayout
			}
			
			/*判断题*/
			//标题
			TableRow tableRowJudgeTitle = new TableRow(this);//创建TableRow
			TextView judge = new TextView(this);
			judge.setText("二、判断题");//设置内容
			judge.setTextSize(TypedValue.COMPLEX_UNIT_PX,32);//指定单位
			tableRowJudgeTitle.addView(judge);//将TextView添加到TableWRow
			tLayout.addView(tableRowJudgeTitle);
			JudgeId = new int[json.getJSONArray(1).size()];
			for(int i=0;i<json.getJSONArray(1).size();i++){
				//题干
				TableRow tableRowJudge = new TableRow(this);
				LinearLayout linearLayoutJudge = new LinearLayout(this);
				TextView questionJudge = new TextView(this);
				//从JSON数组中获取判断题信息并显示
				questionJudge.setText((i+1)+"、"+json.getJSONArray(1).getJSONObject(i).get("judContent"));//题目
				questionJudge.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);//指定单位和设置字体大小
				questionJudge.setWidth(width-200);//留一部分距离放置对错选项
				questionJudge.setSingleLine(false);//可以换行，适应字的多少
				//按钮组
				RadioGroup radioGroupJudge = new RadioGroup(this);
				radioGroupJudge.setOrientation(0);//按钮面板横向布局
				//System.out.println("Judge long:"+System.currentTimeMillis());
				//为按钮设置id
				JudgeId[i] = (int) System.currentTimeMillis();//id为当前时间戳
				radioGroupJudge.setId(JudgeId[i]);
				//System.out.println("Judge int:"+Judge[i]);
				//对错按钮
				RadioButton radioButtonJudgeT = new RadioButton(this);
				radioButtonJudgeT.setId((int) System.currentTimeMillis());//设置判断选项ID
				radioButtonJudgeT.setText("对");
				RadioButton radioButtonJudgeF = new RadioButton(this);
				radioButtonJudgeF.setId((int) System.currentTimeMillis());//设置判断选项ID
				radioButtonJudgeF.setText("错");
				//集合保存题号，作答，答案
				recordJudge.add(new SetAndGetJudgeAnswer((int)json.getJSONArray(1).getJSONObject(i).get("judgeId"), radioButtonJudgeT.getId(), radioButtonJudgeF.getId(), json.getJSONArray(1).getJSONObject(i).get("judAnswer").toString()));
				radioGroupJudge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(RadioGroup arg0, int checkedId) {
						// TODO Auto-generated method stub
						setJudge(checkedId);//调用函数判断和设定保存选项
						/*for(int i=0;i<recordJudge.size();i++){
							System.out.println("题号："+recordJudge.get(i).num+" 作答："+recordJudge.get(i).choose+" 答案："+recordJudge.get(i).answer);
						}*/
					}
				});
				
				/*判断题内容添加到线性布局再将线性布局添加到行中*/
				radioGroupJudge.addView(radioButtonJudgeT);
				radioGroupJudge.addView(radioButtonJudgeF);
				linearLayoutJudge.addView(questionJudge);
				linearLayoutJudge.addView(radioGroupJudge);
				tableRowJudge.addView(linearLayoutJudge);
				
				//将判断题行添加到TableLayout
				tLayout.addView(tableRowJudge);
			}
			/*下面为提前提交按钮行*/
			TableRow rowBtSub = new TableRow(this);
			LinearLayout lnRowBtSub = new LinearLayout(this);
			bt_sub  = new Button(this);//提交按钮
			bt_sub.setText("提交");
			bt_sub.setWidth(200);
			bt_sub.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					//判断是否有为作答的题目
					if(isNull()){
						//Toast.makeText(Test.this, "可以提交!", Toast.LENGTH_SHORT).show();
						dealChoAndJudge();//调用函数对选择题和判断题做拼接
						//传输作答结果至服务端,开启线程才能使用Http
						Thread t = new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								String answerRetu = new HttpPostAnswer(stuNum, courseName,choStr+" "+judgeStr, testTime).send();//调用方法传输作答结果
								String serverAnswerRetu[] = answerRetu.split(" ");//以空格分割服务端返回的标记和作答结果数据
								//如果作答结果传输成功，打开新界面
								if(serverAnswerRetu[0] != null && serverAnswerRetu[0].equals("success")){
									Intent intent = new Intent();//获得Intent
									intent.putExtra("stuNum", stuNum);//学号
									intent.putExtra("courseName", courseName);//测试课程
									intent.putExtra("testTime", testTime);//测试时间
									intent.putExtra("serverAnswerRetu", serverAnswerRetu[1]);//测试结果
									intent.setClass(OTest.this, AnswerResult.class);//指定传递对象
									startActivity(intent);
								}
							}
						});
						t.start();
					}else{
						Toast.makeText(OTest.this, "您还有未作答的选项!", Toast.LENGTH_SHORT).show();
						//new HttpPostAnswer(stuNum, courseName, recordCho, recordJudge, testTime).main();
					}
				}
			});
			lnRowBtSub.addView(bt_sub);
			rowBtSub.addView(lnRowBtSub);
			tLayout.addView(rowBtSub);//将该组件添加到整体布局
		}
	}
}
