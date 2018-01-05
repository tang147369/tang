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
	String target="http://10.0.139.247:8080/OnlineTestServer/servlet";//Ŀ���ַ
	HttpClient httpclient = new DefaultHttpClient();
	HttpPost httpRequest = new HttpPost(target);
	String retu;
	public String send(String stuNum,String courseName,String testTime){
		//��Ҫ���ݵĲ������浽���ϵ���
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("getTestResultContents", "getTestResultContents"));//��ѯ��ǲ���
	    params.add(new BasicNameValuePair("TestResultContentsStuNum", stuNum));//ѧ�ű��
		params.add(new BasicNameValuePair("TestResultContentsCourseName", courseName));//�γ���
		params.add(new BasicNameValuePair("TestResultContentsTestTime", testTime));//����ʱ��
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));//���ñ��뷽ʽ
			HttpResponse httpResponse = httpclient.execute(httpRequest);//ִ��HttpClient����
			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){//�������ɹ�
				retu = EntityUtils.toString(httpResponse.getEntity());//��ȡ���ص��ַ���
				System.out.println("��ѯ���ؽ��(������ϸ���)"+retu);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retu;
	}
}
