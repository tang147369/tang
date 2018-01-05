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
	private String reQuestions,stuNum,courseName,testTime;//ѧ�š��γ���������ʱ��
	private Handler handler;
	private boolean flag;//��־���ж��Ƿ��ȡ�˷���ֵ
	private int width,height;
	private RadioButton radioButtonChoA,radioButtonChoB,radioButtonChoC,radioButtonChoD;//ѡ��
	private Button bt_sub;//�ύ��ť
	private TextView tvCountTime;//����ʱ��ʾ��
	private int recLen = 300;//�ܵ�����
	private int min = 5;//����ʱ������
	private int seconds = 60;//����ʱ��
	private Timer timer = new Timer();  
	private TimerTask task;
	//���ڱ�������ѡ̸�жϽ��
	private StringBuffer choStr = new StringBuffer();
    private StringBuffer judgeStr = new StringBuffer();
	private Vector<SetAndGetChoAnswer> recordCho = new Vector<SetAndGetChoAnswer>();//����ѡ������ţ����𣬴�
	private Vector<SetAndGetJudgeAnswer> recordJudge = new Vector<SetAndGetJudgeAnswer>();//�����ж�����ţ����𣬴�
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);//����ҳ�沼��
		Intent intent = getIntent();//���Intent
		stuNum = intent.getStringExtra("stuNum");//���ѧ��
		courseName = intent.getStringExtra("courseName");//��ò��Կγ�
		System.out.println("courseName1:"+courseName);
		System.out.println("stuNum:"+stuNum);
		DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // ��Ļ��ȣ����أ�
        height = metric.heightPixels;   // ��Ļ�߶ȣ����أ�
		//�����п���UI����
		tLayout = (TableLayout) findViewById(R.id.tableLayout);//��ȡxml�ж����TableLayout
		//���ұ߾�
		tLayout.setLeft(10);
		tLayout.setRight(10);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				reQuestions = new HttpGetQuestions().send(stuNum,courseName);//�����̵߳����������ӷ�����ȡ����ֵ����Ŀ��
				Message m = handler.obtainMessage();//��ȡһ��Message
				handler.sendMessage(m);//������Ϣ
				//System.out.println("1"+reQuestions);
			}
		}).start();//�����߳�
		//System.out.println("reQuestions:"+reQuestions);
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(reQuestions != null){
					flag = true;
					//�ӷ���˻�ȡ��Ŀ��Ϣ�������ʾ����
					show();
					System.out.println("2"+flag);
				}
				//����ʱģ�飬������������ʾ��֮�󵹼�ʱ
				if(flag==true){
					task = new TimerTask() {    
				        @Override    
				        public void run() {    
				   
				            runOnUiThread(new Runnable() {      // UI thread    
				                @Override    
				                public void run() {  
				                	//��������ɹ�λ59
				                	seconds--;
				                	if(seconds<=0){
				                		seconds = 59;
				                    	min--;
				                    }
				                	if(seconds>0 && seconds<10){
				                		tvCountTime.setText("ע��,��������Խ������� "+min+" �� 0"+seconds+" ��");//����ʱ�任
				                	}else{
				                		tvCountTime.setText("ע��,��������Խ������� "+min+" �� "+seconds+" ��");//����ʱ�任
				                	}
				                	//ʱ�䵽
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
		//����ʱ
		System.out.println("2.5");
	}
	//����ѡ����ѡ��
	public Boolean setChoice(int id){
		/*ͨ��Idȷ����ţ����޸�������*/
		//�ж�idΪ�ĸ����ĸ�ѡ���
		for(int i=0;i<recordCho.size();i++){
			//�ж��Ƿ��и�ѡ��
			if(recordCho.get(i).getChoice(id)!=null){
				String choice = recordCho.get(i).getChoice(id);//����ѡ��
				recordCho.get(i).setChoose(choice);//�����û�ѡ��
				System.out.println();
				return true;
			}
		}
		return false;
	}
	//�����ж���ѡ��
	public Boolean setJudge(int id){
		for(int i=0;i<recordJudge.size();i++){
			if(recordJudge.get(i).getChoice(id)!=null){
				String choice = recordJudge.get(i).getChoice(id);//����ѡ��
				recordJudge.get(i).setChoose(choice);
				return true;
			}
		}
		return false;
	}
	//�ж��ǲ�������ѡ�ѡ����
	public Boolean isNull(){
		Boolean flagNull = true; 
		//�ж�ѡ�����Ƿ�ѡ����
		for(int i=0;i<recordCho.size();i++){
			if(recordCho.get(i).choose==null){
				flagNull = false;
			}
		}
		//�ж����Ƿ�ѡ����
		for(int j=0;j<recordJudge.size();j++){
			if(recordJudge.get(j).choose==null){
				flagNull = false;
			}
		}
		return flagNull;
	}
	//ѡ�����ж�����ƴ�Ӵ���
	public void dealChoAndJudge(){
		//���û�����ÿ����ƴ�Ӵ���
		String recordChoStr[] = new String[recordCho.size()];
		for(int i=0;i<recordChoStr.length;i++){
			recordChoStr[i] = recordCho.get(i).num+","+recordCho.get(i).choose+","+recordCho.get(i).answer;
		}
		String recordJudgeStr[] = new String[recordJudge.size()];
		for(int i=0;i<recordJudgeStr.length;i++){
			recordJudgeStr[i] = recordJudge.get(i).num+","+recordJudge.get(i).choose+","+recordJudge.get(i).answer;
		}
		//��ѡ������ж���������ƴ��
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
		//ѡ������ж����ÿո���ƴ�Ӳ�����http�ഫ�䵽�����
	}
	//��ʾ��Ŀ
	public void show(){
		System.out.println("3");
		if(flag==true){
			System.out.println("3.5");
			//�����ԭΪjson����
			String s[] = reQuestions.split(" ");
			System.out.println("4");
			JSONArray json = JSONArray.fromObject(s);
			
			System.out.println("5");
			//��ʱ��
			counTableLayout = (TableLayout) findViewById(R.id.tableL);//��ȡxml�ж����TableLayout(����ʱ)
			TableRow tableRowCountTime = new TableRow(this);
			tvCountTime = new TextView(this);//����ʱ��
			tvCountTime.setTextColor(Color.RED);//����������ɫ
			tableRowCountTime.addView(tvCountTime);
			counTableLayout.addView(tableRowCountTime);
			
			/*ѡ����*/
			TableRow tableRowChoTitle = new TableRow(this);//����TableRow
			TextView choice = new TextView(this);
			choice.setText("һ��ѡ����");//��������
			choice.setTextSize(TypedValue.COMPLEX_UNIT_PX,32);//ָ����λ
			tableRowChoTitle.addView(choice);//��TextView��ӵ�TableWRow
			tLayout.addView(tableRowChoTitle);//����ѡ���⡱����ӵ�TableLayout
			ChoID = new int[json.getJSONArray(0).size()];//����ѡ����ID����ǰʱ���
			
			for(int i=0;i<json.getJSONArray(0).size();i++){
				//��ɲ���
				TableRow tableRowCho = new TableRow(this);
				LinearLayout linearLayoutCho = new LinearLayout(this);
				TextView questionCho = new TextView(this);//��Ŀ����
				/*Json�����е�һ��Ϊѡ���⣬�ڶ���Ϊ�ж��⣬ѡ���������Ŀ��ѡ��abcd���ж���������Ŀ��Ϣ*/
				//��Json�����л�ȡѡ������Ŀ��Ϣ
				questionCho.setText((i+1)+"��"+json.getJSONArray(0).getJSONObject(i).get("choContent"));
				questionCho.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
				questionCho.setSingleLine(false);//���ú���Ի���
				questionCho.setWidth(width-10);//�߾ࣨ�����ñ߾���޷����У�
				//ѡ���
				//ѡ��A
				TableRow tableRowChoA = new TableRow(this);
				LinearLayout linearLayoutChoA = new LinearLayout(this);
				TextView questionChoA = new TextView(this);//ѡ������
				//
				questionChoA.setText("A��"+json.getJSONArray(0).getJSONObject(i).get("a"));
				questionChoA.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
				questionChoA.setSingleLine(false);
				questionChoA.setWidth(width-10);//�߾�
				//ѡ��B
				TableRow tableRowChoB = new TableRow(this);
				LinearLayout linearLayoutChoB = new LinearLayout(this);
				TextView questionChoB = new TextView(this);//ѡ������
				questionChoB.setText("B��"+json.getJSONArray(0).getJSONObject(i).get("b"));
				questionChoB.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
				questionChoB.setSingleLine(false);
				questionChoB.setWidth(width-10);//�߾�
				//ѡ��C
				TableRow tableRowChoC = new TableRow(this);
				LinearLayout linearLayoutChoC = new LinearLayout(this);
				TextView questionChoC = new TextView(this);//ѡ������
				questionChoC.setText("C��"+json.getJSONArray(0).getJSONObject(i).get("c"));
				questionChoC.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
				questionChoC.setSingleLine(false);
				questionChoC.setWidth(width-10);//�߾�
				//ѡ��D
				TableRow tableRowChoD = new TableRow(this);
				LinearLayout linearLayoutChoD = new LinearLayout(this);
				TextView questionChoD = new TextView(this);//ѡ������
				questionChoD.setText("D��"+json.getJSONArray(0).getJSONObject(i).get("d"));
				questionChoD.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);
				questionChoD.setSingleLine(false);
				questionChoD.setWidth(width-10);//�߾�
				//��ѡ����
				TableRow tableRowChoAnswer = new TableRow(this);
				LinearLayout linearLayoutAnswer = new LinearLayout(this);
				RadioGroup  radioGroupCho = new RadioGroup(this);//��ѡ����
				radioGroupCho.setOrientation(0);//0�����������
				//System.out.println("Cho long:"+System.currentTimeMillis());
				ChoID[i] = (int) System.currentTimeMillis();
				radioGroupCho.setId(ChoID[i]);//����IDΪ��ǰʱ���
				//System.out.println("Cho int:"+ChoID[i]);//
				radioButtonChoA = new RadioButton(this);
				radioButtonChoA.setId((int) System.currentTimeMillis());//����ѡ��idΪ��ǰʱ���
				radioButtonChoA.setText("A");
				/*System.out.println("A��ID��"+radioButtonChoA.getId());*/
				
				radioButtonChoB = new RadioButton(this);
				radioButtonChoB.setId((int) System.currentTimeMillis());//����ѡ��idΪ��ǰʱ���
				radioButtonChoB.setText("B");
				/*System.out.println("B��ID��"+radioButtonChoB.getId());*/
				
				radioButtonChoC = new RadioButton(this);
				radioButtonChoC.setId((int) System.currentTimeMillis());//����ѡ��idΪ��ǰʱ���
				radioButtonChoC.setText("C");
				/*System.out.println("C��ID��"+radioButtonChoC.getId());*/
				
				radioButtonChoD = new RadioButton(this);
				radioButtonChoD.setId((int) System.currentTimeMillis());//����ѡ��idΪ��ǰʱ���
				radioButtonChoD.setText("D");
				/*System.out.println("D��ID��"+radioButtonChoD.getId());*/
				//���ϱ�����ţ�ѡ��id�ʹ𰸶���
				recordCho.add(new SetAndGetChoAnswer((int)json.getJSONArray(0).getJSONObject(i).get("choId"),radioButtonChoA.getId(),radioButtonChoB.getId(),radioButtonChoC.getId(),radioButtonChoD.getId(),json.getJSONArray(0).getJSONObject(i).get("choAnswer").toString()));
				radioGroupCho.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(RadioGroup arg0, int checkedId) {
						System.out.println("checkedId:"+checkedId);
						// TODO Auto-generated method stub
						setChoice(checkedId);//������ֵ����
						//��ÿ�ν�����
						/*for(int i=0;i<recordCho.size();i++){
							System.out.println("��ţ�"+recordCho.get(i).num+" ѡ��"+recordCho.get(i).choose+" �𰸣�"+recordCho.get(i).answer);
						}*/
					}
				});
				/*ѡ������ӵ����Բ����ٽ����Բ�����ӵ�����*/
				//���
				linearLayoutCho.addView(questionCho);
				tableRowCho.addView(linearLayoutCho);//��linearLayoutCho��ӵ�TableRow
				//ѡ��A����������
				linearLayoutChoA.addView(questionChoA);
				tableRowChoA.addView(linearLayoutChoA);
				//ѡ��B����������
				linearLayoutChoB.addView(questionChoB);
				tableRowChoB.addView(linearLayoutChoB);
				//ѡ��C����������
				linearLayoutChoC.addView(questionChoC);
				tableRowChoC.addView(linearLayoutChoC);
				//ѡ��D����������
				linearLayoutChoD.addView(questionChoD);
				tableRowChoD.addView(linearLayoutChoD);
				//�𰸰�ť��ӵ���
				radioGroupCho.addView(radioButtonChoA);
				radioGroupCho.addView(radioButtonChoB);
				radioGroupCho.addView(radioButtonChoC);
				radioGroupCho.addView(radioButtonChoD);
				linearLayoutAnswer.addView(radioGroupCho);
				tableRowChoAnswer.addView(linearLayoutAnswer);
				
				//��ѡ��������ӵ�TableLayout
				tLayout.addView(tableRowCho);//�������ӵ�TableLayout
				tLayout.addView(tableRowChoA);//��ѡ��A����ӵ�TableLayout
				tLayout.addView(tableRowChoB);//��ѡ��B����ӵ�TableLayout
				tLayout.addView(tableRowChoC);//��ѡ��B����ӵ�TableLayout
				tLayout.addView(tableRowChoD);//��ѡ��B����ӵ�TableLayout
				tLayout.addView(tableRowChoAnswer);//��������ӵ�TableLayout
			}
			
			/*�ж���*/
			//����
			TableRow tableRowJudgeTitle = new TableRow(this);//����TableRow
			TextView judge = new TextView(this);
			judge.setText("�����ж���");//��������
			judge.setTextSize(TypedValue.COMPLEX_UNIT_PX,32);//ָ����λ
			tableRowJudgeTitle.addView(judge);//��TextView��ӵ�TableWRow
			tLayout.addView(tableRowJudgeTitle);
			JudgeId = new int[json.getJSONArray(1).size()];
			for(int i=0;i<json.getJSONArray(1).size();i++){
				//���
				TableRow tableRowJudge = new TableRow(this);
				LinearLayout linearLayoutJudge = new LinearLayout(this);
				TextView questionJudge = new TextView(this);
				//��JSON�����л�ȡ�ж�����Ϣ����ʾ
				questionJudge.setText((i+1)+"��"+json.getJSONArray(1).getJSONObject(i).get("judContent"));//��Ŀ
				questionJudge.setTextSize(TypedValue.COMPLEX_UNIT_PX,28);//ָ����λ�����������С
				questionJudge.setWidth(width-200);//��һ���־�����öԴ�ѡ��
				questionJudge.setSingleLine(false);//���Ի��У���Ӧ�ֵĶ���
				//��ť��
				RadioGroup radioGroupJudge = new RadioGroup(this);
				radioGroupJudge.setOrientation(0);//��ť�����򲼾�
				//System.out.println("Judge long:"+System.currentTimeMillis());
				//Ϊ��ť����id
				JudgeId[i] = (int) System.currentTimeMillis();//idΪ��ǰʱ���
				radioGroupJudge.setId(JudgeId[i]);
				//System.out.println("Judge int:"+Judge[i]);
				//�Դ�ť
				RadioButton radioButtonJudgeT = new RadioButton(this);
				radioButtonJudgeT.setId((int) System.currentTimeMillis());//�����ж�ѡ��ID
				radioButtonJudgeT.setText("��");
				RadioButton radioButtonJudgeF = new RadioButton(this);
				radioButtonJudgeF.setId((int) System.currentTimeMillis());//�����ж�ѡ��ID
				radioButtonJudgeF.setText("��");
				//���ϱ�����ţ����𣬴�
				recordJudge.add(new SetAndGetJudgeAnswer((int)json.getJSONArray(1).getJSONObject(i).get("judgeId"), radioButtonJudgeT.getId(), radioButtonJudgeF.getId(), json.getJSONArray(1).getJSONObject(i).get("judAnswer").toString()));
				radioGroupJudge.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(RadioGroup arg0, int checkedId) {
						// TODO Auto-generated method stub
						setJudge(checkedId);//���ú����жϺ��趨����ѡ��
						/*for(int i=0;i<recordJudge.size();i++){
							System.out.println("��ţ�"+recordJudge.get(i).num+" ����"+recordJudge.get(i).choose+" �𰸣�"+recordJudge.get(i).answer);
						}*/
					}
				});
				
				/*�ж���������ӵ����Բ����ٽ����Բ�����ӵ�����*/
				radioGroupJudge.addView(radioButtonJudgeT);
				radioGroupJudge.addView(radioButtonJudgeF);
				linearLayoutJudge.addView(questionJudge);
				linearLayoutJudge.addView(radioGroupJudge);
				tableRowJudge.addView(linearLayoutJudge);
				
				//���ж�������ӵ�TableLayout
				tLayout.addView(tableRowJudge);
			}
			/*����Ϊ��ǰ�ύ��ť��*/
			TableRow rowBtSub = new TableRow(this);
			LinearLayout lnRowBtSub = new LinearLayout(this);
			bt_sub  = new Button(this);//�ύ��ť
			bt_sub.setText("�ύ");
			bt_sub.setWidth(200);
			bt_sub.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					//�ж��Ƿ���Ϊ�������Ŀ
					if(isNull()){
						//Toast.makeText(Test.this, "�����ύ!", Toast.LENGTH_SHORT).show();
						dealChoAndJudge();//���ú�����ѡ������ж�����ƴ��
						//�����������������,�����̲߳���ʹ��Http
						Thread t = new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								String answerRetu = new HttpPostAnswer(stuNum, courseName,choStr+" "+judgeStr, testTime).send();//���÷�������������
								String serverAnswerRetu[] = answerRetu.split(" ");//�Կո�ָ����˷��صı�Ǻ�����������
								//�������������ɹ������½���
								if(serverAnswerRetu[0] != null && serverAnswerRetu[0].equals("success")){
									Intent intent = new Intent();//���Intent
									intent.putExtra("stuNum", stuNum);//ѧ��
									intent.putExtra("courseName", courseName);//���Կγ�
									intent.putExtra("testTime", testTime);//����ʱ��
									intent.putExtra("serverAnswerRetu", serverAnswerRetu[1]);//���Խ��
									intent.setClass(OTest.this, AnswerResult.class);//ָ�����ݶ���
									startActivity(intent);
								}
							}
						});
						t.start();
					}else{
						Toast.makeText(OTest.this, "������δ�����ѡ��!", Toast.LENGTH_SHORT).show();
						//new HttpPostAnswer(stuNum, courseName, recordCho, recordJudge, testTime).main();
					}
				}
			});
			lnRowBtSub.addView(bt_sub);
			rowBtSub.addView(lnRowBtSub);
			tLayout.addView(rowBtSub);//���������ӵ����岼��
		}
	}
}
