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
		String target = "http://10.0.139.247:8080//OnlineTestServer/servlet";	//Ҫ�ύ��Ŀ���ַ
		HttpClient httpclient = new DefaultHttpClient();  //����HttpClient����
		HttpPost httpRequest = new HttpPost(target); //����HttpPost����
		//��Ҫ���ݵĲ������浽List������
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("seleCourse", "course"));//��ѯ��ǲ���
		params.add(new BasicNameValuePair("seleStuNum", stuNum));//ѧ��
		try {
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "utf-8"));//���ñ��뷽ʽ
			HttpResponse httpResponse = httpclient.execute(httpRequest);//ִ��HttpClient����
			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){//�������ɹ�
				retu = EntityUtils.toString(httpResponse.getEntity());//��ȡ���ص��ַ���
				//System.out.println("���γ̣���ѯ���ؽ��"+retu);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retu;
	}
}
