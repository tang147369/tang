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
	//声明组件
	private Button bt_login;
	private EditText username,password;
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);//设置该Activity使用的布局
		//获取组件
		 bt_login = (Button) findViewById(R.id.bt_login);
		 username = (EditText) findViewById(R.id.username);
		 password = (EditText) findViewById(R.id.password);
		 //给登陆按钮绑定监听事件
		 bt_login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//创建Intent对象并置指定传递对象
				intent = new Intent(MainActivity.this,LoginResult.class);
				//androi后网络操作需放在线程中进行
				Thread t = new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						//调用发送数据函数
						String retu = new HttpPostInfor().send(username.getText().toString(),password.getText().toString());
						//System.out.println("retu:"+retu);
						String re[] = retu.split(",");//以‘,’分割为字符串数组
						//登陆成功则启动新的Activity
						if(re[0].equals("success")){
							System.out.println("登陆成功");
							for(int i=1;i<re.length;i++){
								//System.out.println("输出 ："+re[i]+" ");
								intent.putExtra("name", re[1]);
								intent.putExtra("stuNum", re[2]);
								intent.putExtra("classN", re[3]);
								intent.putExtra("college", re[4]);
								intent.putExtra("major", re[5]);
							}
							startActivity(intent);//将Intent传给Activity
						}else{
							//弹出消息提示框
							Looper.prepare();
							Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
							Looper.loop();
						}
					}
				});
				t.start();//启动线程
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
