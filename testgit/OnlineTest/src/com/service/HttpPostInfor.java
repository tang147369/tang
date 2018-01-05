package com.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpPostInfor {
	private String retu;
	public String send(String username,String password){
		String target="http://10.0.139.247:8080/OnlineTestServer/servlet";
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpRequst = new HttpPost(target);
		//创建一个ArrayList
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		
		NameValuePair login= new BasicNameValuePair("login", "login");
		NameValuePair name= new BasicNameValuePair("username", username);
		NameValuePair pwd= new BasicNameValuePair("password", password);
		param.add(login);
		param.add(name);
		param.add(pwd);
		try {
			httpRequst.setEntity(new UrlEncodedFormEntity(param, "utf-8"));
			HttpResponse response = httpclient.execute(httpRequst);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				//获得服务端的返回值
				retu = EntityUtils.toString(response.getEntity());
				//System.out.println("返回值："+retu);
			}else{
				System.out.println("失败");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retu;
	}
}
