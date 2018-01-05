package com.onlinetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerResult extends Activity{
	private TextView tViewAnswerResult;//作答结果
	private Button btLookResults;
	private String stuNum,courseName,testTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer_result);
		Intent intent = getIntent();//获得Intent
		stuNum = intent.getStringExtra("stuNum");//学号
		courseName = intent.getStringExtra("courseName");//测试课程
		testTime = intent.getStringExtra("testTime");//测试时间
		String serverAnswerRetu = intent.getStringExtra("serverAnswerRetu");
		tViewAnswerResult = (TextView)findViewById(R.id.tViewAnswerResult);//获得结果显示
		tViewAnswerResult.setText(serverAnswerRetu);//设置文本框内容
		System.out.println("tViewAnswerResult:"+tViewAnswerResult);
		btLookResults = (Button)findViewById(R.id.button1);//获得按钮
		//按钮点击事件
		btLookResults.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent();
				intent1.setClass(AnswerResult.this, ShowTestResultContents.class);//指定传递对象
				intent1.putExtra("stuNum", stuNum);//学号
				intent1.putExtra("courseName", courseName);//测试课程
				intent1.putExtra("testTime", testTime);//测试时间
				startActivity(intent1);//启动intent对应的activity
			}
		});
	}
	
}
