package com.onlinetest;

import net.sf.json.JSONArray;

import com.service.HttpGetTestResultContents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class ShowTestResultContents extends Activity {
	private String stuNum,courseName,testTime,getRetu;//getRetuΪ��÷���������
	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testresult_contents);
		Intent intent = getIntent();//���intent
		stuNum = intent.getStringExtra("stuNum");//���ѧ��
		courseName = intent.getStringExtra("courseName");//��ò��Կγ���
		testTime = intent.getStringExtra("testTime");//��ò���ʱ��
		System.out.println("ShowTestResults:"+stuNum+" "+courseName+" "+testTime);			
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				getRetu = new HttpGetTestResultContents().send(stuNum, courseName, testTime);
				Message m = handler.obtainMessage();//��ȡһ��Message
				handler.sendMessage(m);//������Ϣ
			}
		}).start();
		handler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if(getRetu!=null){
					TextView t = (TextView)findViewById(R.id.textView2);
					t.setText(getRetu);
					String s[] = getRetu.split(" ");
					JSONArray json = JSONArray.fromObject(s);
					System.out.println("json.size():"+json.size());
				}
			}
		};
	}
	public void showContents(){
		
	}
}
