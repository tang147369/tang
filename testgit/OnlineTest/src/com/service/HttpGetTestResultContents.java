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

public class HttpGetTestResultContents {
	String target="http://10.0.139.247:8080/OnlineTestServer/servlet";//目标地址
	HttpClient httpclient = new DefaultHttpClient();
	HttpPost httpRequest = new HttpPost(target);
	String retu;
	public String send(String stuNum,String courseName,String testTime){
		//将要传递的参数保存到集合当中
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("getTestResultContents", "getTestResultContents"));//查询标记参数
	    params.add(new BasicNameValuePair("TestResultContentsStuNum", stuNum));//学号标记
		params.add(new BasicNameValuePair("TestResultContentsCourseName", courseName));//课程名
		params.add(new BasicNameValuePair("TestResultContentsTestTime", testTime));//测试时间
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));//设置编码方式
			HttpResponse httpResponse = httpclient.execute(httpRequest);//执行HttpClient请求
			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){//如果请求成功
				retu = EntityUtils.toString(httpResponse.getEntity());//获取返回的字符串
				System.out.println("查询返回结果(测试详细结果)"+retu);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retu;
	}
}
