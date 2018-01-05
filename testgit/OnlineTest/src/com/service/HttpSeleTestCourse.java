package com.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpSeleTestCourse {
	private String retu;
	public String send(String stuNum){
		String target = "http://10.0.139.247:8080//OnlineTestServer/servlet";	//要提交的目标地址
		HttpClient httpclient = new DefaultHttpClient();  //创建HttpClient对象
		HttpPost httpRequest = new HttpPost(target); //创建HttpPost对象
		//将要传递的参数保存到List集合中
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("seleCourse", "course"));//查询标记参数
		params.add(new BasicNameValuePair("seleStuNum", stuNum));//学号
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));//设置编码方式
			HttpResponse httpResponse = httpclient.execute(httpRequest);//执行HttpClient请求
			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){//如果请求成功
				retu = EntityUtils.toString(httpResponse.getEntity());//获取返回的字符串
				//System.out.println("（课程）查询返回结果"+retu);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retu;
	}
}
