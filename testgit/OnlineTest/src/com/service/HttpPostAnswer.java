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
	String recordResults;//����������
	//������ѧ�ţ����Կγ̣�ѡ����ش������ж���ش���������ʱ��
	public HttpPostAnswer(String testRestuNum,String reTestCourseName,String recordResults,String testTime){
		this.testRestuNum = testRestuNum;
		this.reTestCourseName = reTestCourseName;
		this.testTime =testTime;
		this.recordResults = recordResults;
		System.out.println("recordResults:"+recordResults);
	}
	public String send(){
		String target="http://10.0.139.247:8080/OnlineTestServer/servlet";//Ŀ���ַ
		HttpClient httpclient = new DefaultHttpClient();//����HttpClient����
		HttpPost httpRequest = new HttpPost(target);//����HttpPost����
		List<NameValuePair> params = new ArrayList<NameValuePair>();//��Ҫ���ݵĲ������浽List������
		params.add(new BasicNameValuePair("testAnswerflag", "testAnswerflag"));//�ش��ǲ���
		params.add(new BasicNameValuePair("testRestuNum", testRestuNum));//������ѧ��
		params.add(new BasicNameValuePair("reTestCourseName", reTestCourseName));//���Կγ�
		params.add(new BasicNameValuePair("recordResults", recordResults));//���Խ����Ϣ
		params.add(new BasicNameValuePair("testTime", testTime));//����ʱ��
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));//���ñ��뷽ʽ
			System.out.println("ps1");
			HttpResponse httpResponse = httpclient.execute(httpRequest);//ִ��HttpClient����
			System.out.println("ps2");
			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){//�������ɹ�
				retu = EntityUtils.toString(httpResponse.getEntity());//��ȡ���ص��ַ���
				System.out.println("��ѯ���ؽ��"+retu);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retu;
	}
}
