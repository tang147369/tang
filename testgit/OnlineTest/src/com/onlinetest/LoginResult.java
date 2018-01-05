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
	private String retu;//����˷��صĽ��
	private Handler handler;
	private Thread t;
	private Boolean isContinue=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginresult);
		Intent intentGet = getIntent();//���Intent
		//��ÿؼ�
		twName = (TextView) findViewById(R.id.textViewName);//�û���
		twStuNum = (TextView) findViewById(R.id.textViewStuNum);//ѧ��
		twCollege = (TextView) findViewById(R.id.textViewCollege);//ѧԺ
		twPMajorClass = (TextView) findViewById(R.id.textViewMajorClass);//רҵ�༶
		testCourse = (TextView) findViewById(R.id.testCourse);//�γ�
		//��ô�Activity��������ֵ
		name = intentGet.getStringExtra("name");//����û���
		stuNum = intentGet.getStringExtra("stuNum");//���ѧ��
		college = intentGet.getStringExtra("college");//���ѧԺ����
		major = intentGet.getStringExtra("major");//���רҵ����
		classN = intentGet.getStringExtra("classN");//��ð༶
		start = (Button) findViewById(R.id.start);
		//�����ı�����
		twName.setText(name);
		twStuNum.setText(stuNum);
		twCollege.setText(college);
		twPMajorClass.setText(major+classN);
		t =new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//���Ϸ��ʷ����
				while(true){
					retu = new HttpSeleTestCourse().send(stuNum);//ִ��������������ؽ��(ע�⣺��������������߳����)
					Message m = handler.obtainMessage();
					handler.sendMessage(m);
					try {
						t.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Login_1");
					//��ContinueΪTrueʱֹͣ�߳�
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
				//�����ڲ��ԵĿγ�ʱ�����ת��ֹͣˢ��
				if(!retu.equals("��")){
					Intent intent = new Intent(LoginResult.this,OTest.class);
					intent.putExtra("stuNum", stuNum);//��װѧ����Ϣ
					intent.putExtra("courseName", retu);//��װ�γ���Ϣ
					//System.out.println("cou_retu:"+retu);
					isContinue=true;
					startActivity(intent);
				}
			}
		});
	}

}
