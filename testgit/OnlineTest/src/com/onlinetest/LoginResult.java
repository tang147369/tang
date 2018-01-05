package com.onlinetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.service.HttpSeleTestCourse;

public class LoginResult extends Activity{
	private Button start;
	private String name,stuNum,classN,college,major;
	private TextView twName,twStuNum,twCollege,twPMajorClass,testCourse;
	private String retu;//服务端返回的结果
	private Handler handler;
	private Thread t;
	private Boolean isContinue=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginresult);
		Intent intentGet = getIntent();//获得Intent
		//获得控件
		twName = (TextView) findViewById(R.id.textViewName);//用户名
		twStuNum = (TextView) findViewById(R.id.textViewStuNum);//学号
		twCollege = (TextView) findViewById(R.id.textViewCollege);//学院
		twPMajorClass = (TextView) findViewById(R.id.textViewMajorClass);//专业班级
		testCourse = (TextView) findViewById(R.id.testCourse);//课程
		//获得从Activity传过来的值
		name = intentGet.getStringExtra("name");//获得用户名
		stuNum = intentGet.getStringExtra("stuNum");//获得学号
		college = intentGet.getStringExtra("college");//获得学院名称
		major = intentGet.getStringExtra("major");//获得专业名称
		classN = intentGet.getStringExtra("classN");//获得班级
		start = (Button) findViewById(R.id.start);
		//设置文本内容
		twName.setText(name);
		twStuNum.setText(stuNum);
		twCollege.setText(college);
		twPMajorClass.setText(major+classN);
		t =new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//不断访问服务端
				while(true){
					retu = new HttpSeleTestCourse().send(stuNum);//执行网络操作并返回结果(注意：网络操作需在子线程完成)
					Message m = handler.obtainMessage();
					handler.sendMessage(m);
					try {
						t.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Login_1");
					//当Continue为True时停止线程
					if(isContinue){
						System.out.println("Login_over");
						break;
					}
				}
			}
		});t.start();
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if(retu!=null){
					testCourse.setText(retu);
				}
				super.handleMessage(msg);
			}
		};
		start.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//有正在测试的课程时点击跳转并停止刷新
				if(!retu.equals("无")){
					Intent intent = new Intent(LoginResult.this,OTest.class);
					intent.putExtra("stuNum", stuNum);//封装学号信息
					intent.putExtra("courseName", retu);//封装课程信息
					//System.out.println("cou_retu:"+retu);
					isContinue=true;
					startActivity(intent);
				}
			}
		});
	}

}
