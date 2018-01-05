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
	private String stuNum,courseName,testTime,getRetu;//getRetu为获得返回作答结果
	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.testresult_contents);
		Intent intent = getIntent();//获得intent
		stuNum = intent.getStringExtra("stuNum");//获得学号
		courseName = intent.getStringExtra("courseName");//获得测试课程名
		testTime = intent.getStringExtra("testTime");//获得测试时间
		System.out.println("ShowTestResults:"+stuNum+" "+courseName+" "+testTime);			
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				getRetu = new HttpGetTestResultContents().send(stuNum, courseName, testTime);
				Message m = handler.obtainMessage();//获取一个Message
				handler.sendMessage(m);//发送消息
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
