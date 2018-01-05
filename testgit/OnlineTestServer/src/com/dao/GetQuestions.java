package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import net.sf.json.JSONArray;

import com.bean.GetChoices;
import com.bean.GetJudges;

public class GetQuestions {
	private Connection con;
	public JSONArray jsonArrayChoice,jsonArrayjudge;
	private String stuNum,courseName;
	private int courseId = -1;
	//��ȡѡ����
	public GetQuestions(String stuNum,String courseName){
		this.stuNum = stuNum;
		this.courseName = courseName;
		con = new GetConnection().getConnction();//�������ݿ����ӷ���
		//Ԥ����
		try {
			PreparedStatement stat_ch = con.prepareStatement("select courseId from course where courseName = ?");
			stat_ch.setString(1, courseName);//����Ԥ����������(�γ���)
			ResultSet res_ch = stat_ch.executeQuery();//ִ��SQL����ѯ�γ�id
			while(res_ch.next()){
				courseId = res_ch.getInt(1);//��ȡ��ѯ���(�γ�id)
				System.out.println("courseId1:"+courseId);
			}
			if(res_ch != null){
				res_ch.close();
			}
			if(stat_ch != null){
				stat_ch.close();
			}
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getChos(){
		Vector<GetChoices> listChoice = new Vector<GetChoices>();//���ڱ���ÿ��ѡ�������
		try {
			con = new GetConnection().getConnction();//�������ݿ����ӷ���
			PreparedStatement stat = con.prepareStatement("select * from choices where courseId = ?");
			stat.setInt(1, courseId);//����Ԥ����������(�γ�id)
			ResultSet res = stat.executeQuery();//ִ��SQL���
			//������ѯѡ��������
			while(res.next()){
				int choiceId = res.getInt(1);//���
				String choContent = res.getString(2);//���
				//ѡ��
				String a = res.getString(3);
				String b = res.getString(4);
				String c = res.getString(5);
				String d = res.getString(6);
				String choAnswer = res.getString(7);//��
				//��ÿ��ѡ���������ӵ�������
				listChoice.add(new GetChoices(choiceId, choContent,a, b, c, d, choAnswer));
			}
			jsonArrayChoice = JSONArray.fromObject(listChoice);//Listת����JSON����
			/*System.out.println(jsonArray);
			for(int i=0;i<jsonArray.size();i++){
				System.out.print(jsonArray.getJSONObject(i).get("choId")+" ");
				System.out.print(jsonArray.getJSONObject(i).get("choContent")+" ");
				System.out.print(jsonArray.getJSONObject(i).get("a")+" ");
				System.out.print(jsonArray.getJSONObject(i).get("b")+" ");
				System.out.print(jsonArray.getJSONObject(i).get("c")+" ");
				System.out.print(jsonArray.getJSONObject(i).get("d")+" ");
				System.out.println(jsonArray.getJSONObject(i).get("choAnswer")+" ");
				
			}*/
			if(res != null){
				res.close();
			}
			if(stat != null){
				stat.close();
			}
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArrayChoice.toString();
	}
	//��ȡ�ж���
	public String getJus(){
		Vector<GetJudges> listJudge = new Vector<GetJudges>();//���ڱ���ÿ���ж������
		con = new GetConnection().getConnction();//�������ݿ����ӷ���
		try {
			//Ԥ����
			PreparedStatement stat = con.prepareStatement("select * from judges where courseId = ?");
			stat.setInt(1, courseId);
			ResultSet res = stat.executeQuery();//ִ��SQL���
			//������ѯ�ж�������
			while(res.next()){
				int judgeId = res.getInt(1);
				String judContent = res.getString(2);
				String judAnswer = res.getString(3);
				listJudge.add(new GetJudges(judgeId, judContent, judAnswer));
			}
			jsonArrayjudge = JSONArray.fromObject(listJudge);
			//System.out.println(jsonArrayjudge.toString());
			/*for(int i=0;i<jsonArray.size();i++){
				System.out.println(jsonArray.getJSONObject(i).get("judgeId")+" "+jsonArray.getJSONObject(i).get("judContent")+" "+jsonArray.getJSONObject(i).get("judAnswer"));
			}*/
			if(res != null){
				res.close();
			}
			if(stat != null){
				stat.close();
			}
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonArrayjudge.toString();
	}
	public static void main(String[] args) {
		String str = new GetQuestions("20140098","�㷨������������").getChos()+" "+new GetQuestions("20140098","��������ԭ��").getJus();
		String s[] = str.split(" ");
		JSONArray json = JSONArray.fromObject(s);//������ת��ΪJSON
		System.out.println(json);
		for(int i=0;i<json.getJSONArray(0).size();i++){
			System.out.println(json.getJSONArray(0).getJSONObject(i).get("choContent"));
			System.out.println(json.getJSONArray(0).getJSONObject(i).get("choAnswer"));
		}
		System.out.println("-------------------");
		for(int i=0;i<json.getJSONArray(1).size();i++){
			System.out.println(json.getJSONArray(1).getJSONObject(i).get("judContent"));
			System.out.println(json.getJSONArray(1).getJSONObject(i).get("judAnswer"));
		}
	}
}
