package com.onlinetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.service.HttpPostInfor;

public class MainActivity extends Activity {
	//�������
	private Button bt_login;
	private EditText username,password;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);//���ø�Activityʹ�õĲ���
		//��ȡ���
		 bt_login = (Button) findViewById(R.id.bt_login);
		 username = (EditText) findViewById(R.id.username);
		 password = (EditText) findViewById(R.id.password);
		 //����½��ť�󶨼����¼�
		 bt_login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//����Intent������ָ�����ݶ���
				intent = new Intent(MainActivity.this,LoginResult.class);
				//androi���������������߳��н���
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						//���÷������ݺ���
						String retu = new HttpPostInfor().send(username.getText().toString(),password.getText().toString());
						//System.out.println("retu:"+retu);
						String re[] = retu.split(",");//�ԡ�,���ָ�Ϊ�ַ�������
						//��½�ɹ��������µ�Activity
						if(re[0].equals("success")){
							System.out.println("��½�ɹ�");
							for(int i=1;i<re.length;i++){
								//System.out.println("��� ��"+re[i]+" ");
								intent.putExtra("name", re[1]);
								intent.putExtra("stuNum", re[2]);
								intent.putExtra("classN", re[3]);
								intent.putExtra("college", re[4]);
								intent.putExtra("major", re[5]);
							}
							startActivity(intent);//��Intent����Activity
						}else{
							//������Ϣ��ʾ��
							Looper.prepare();
							Toast.makeText(MainActivity.this, "�û������������", Toast.LENGTH_SHORT).show();
							Looper.loop();
						}
					}
				});
				t.start();//�����߳�
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
