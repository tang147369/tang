package com.onlinetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnswerResult extends Activity{
	private TextView tViewAnswerResult;//������
	private Button btLookResults;
	private String stuNum,courseName,testTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer_result);
		Intent intent = getIntent();//���Intent
		stuNum = intent.getStringExtra("stuNum");//ѧ��
		courseName = intent.getStringExtra("courseName");//���Կγ�
		testTime = intent.getStringExtra("testTime");//����ʱ��
		String serverAnswerRetu = intent.getStringExtra("serverAnswerRetu");
		tViewAnswerResult = (TextView)findViewById(R.id.tViewAnswerResult);//��ý����ʾ
		tViewAnswerResult.setText(serverAnswerRetu);//�����ı�������
		System.out.println("tViewAnswerResult:"+tViewAnswerResult);
		btLookResults = (Button)findViewById(R.id.button1);//��ð�ť
		//��ť����¼�
		btLookResults.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent1 = new Intent();
				intent1.setClass(AnswerResult.this, ShowTestResultContents.class);//ָ�����ݶ���
				intent1.putExtra("stuNum", stuNum);//ѧ��
				intent1.putExtra("courseName", courseName);//���Կγ�
				intent1.putExtra("testTime", testTime);//����ʱ��
				startActivity(intent1);//����intent��Ӧ��activity
			}
		});
	}
	
}
