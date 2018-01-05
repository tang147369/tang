package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;

import com.onlinetest.SetAndGetChoAnswer;
import com.onlinetest.SetAndGetJudgeAnswer;

public class HttpPostAnswer {
	String testRestuNum,reTestCourseName,testTime,retu;
	String recordResults;//保存作答结果
	//作答人学号，测试课程，选择题回答结果，判断题回答结果，测试时间
	public HttpPostAnswer(String testRestuNum,String reTestCourseName,String recordResults,String testTime){
		this.testRestuNum = testRestuNum;
		this.reTestCourseName = reTestCourseName;
		this.testTime =testTime;
		this.recordResults = recordResults;
		System.out.println("recordResults:"+recordResults);
	}
	public String send(){
		String target="http://10.0.139.247:8080/OnlineTestServer/servlet";//目标地址
		HttpClient httpclient = new DefaultHttpClient();//创建HttpClient对象
		HttpPost httpRequest = new HttpPost(target);//创建HttpPost对象
		List<NameValuePair> params = new ArrayList<NameValuePair>();//将要传递的参数保存到List集合中
		params.add(new BasicNameValuePair("testAnswerflag", "testAnswerflag"));//回答标记参数
		params.add(new BasicNameValuePair("testRestuNum", testRestuNum));//测试人学号
		params.add(new BasicNameValuePair("reTestCourseName", reTestCourseName));//测试课程
		params.add(new BasicNameValuePair("recordResults", recordResults));//测试结果信息
		params.add(new BasicNameValuePair("testTime", testTime));//测试时间
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));//设置编码方式
			System.out.println("ps1");
			HttpResponse httpResponse = httpclient.execute(httpRequest);//执行HttpClient请求
			System.out.println("ps2");
			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){//如果请求成功
				retu = EntityUtils.toString(httpResponse.getEntity());//获取返回的字符串
				System.out.println("查询返回结果"+retu);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retu;
	}
}
