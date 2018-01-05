package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.bean.GetChoTestResultContents;
import com.bean.GetJudTestResultContents;

public class GetTestResultContens {
	private String stuNum,courseName,testTime;
	private Connection con;
	private int courseId;
	private List<GetChoTestResultContents> choList=new ArrayList<GetChoTestResultContents>();//�洢ѡ����
	private List<GetJudTestResultContents> judList=new ArrayList<GetJudTestResultContents>();//�洢�ж���
	private String choContent,a,b,c,d,choAnswer,judContent,judAnswer;
	private JSONArray jsonArrayCho,jsonArrayJud;//����ѡ���⣬�ж���������
	public GetTestResultContens(String stuNum,String courseName,String testTime){
		this.stuNum = stuNum;
		this.courseName = courseName;
		this.testTime = testTime;
	}
	public String getResultsContents(){
		con = new GetConnection().getConnction();//��ȡ���ݿ�����
		try {
			//��ѯ�γ�id
			PreparedStatement statCourseName = con.prepareStatement("select courseId from course where courseName = ?");
			statCourseName.setString(1, courseName);//����Ԥ����������(�γ���)
			ResultSet resCourseName = statCourseName.executeQuery();//ִ��SQL����ѯ�γ�id
			while(resCourseName.next()){
				courseId = resCourseName.getInt(1);//��ȡ��ѯ���(�γ�id)
				//System.out.println("getTestCourse courseId1:"+courseId);
			}
			//ѡ����
			
			//��ѯѡ������Ŀid�ʹ�
			PreparedStatement statChoAnswer = con.prepareStatement("select choiceId,choChoose from chotestresults where stuNum = ? and courseId = ? and testTimeId = ?");
			statChoAnswer.setString(1, stuNum);//����Ԥ����������(ѧ��)
			statChoAnswer.setInt(2, courseId);//����Ԥ����������(�γ�Id)
			statChoAnswer.setInt(3, 1);//����ʱ��
			ResultSet resChoAnswer = statChoAnswer.executeQuery();//ִ��sql���
			//ѡ��������
			PreparedStatement statChoContents = con.prepareStatement("select choContent, a, b, c, d, choAnswer from choices where courseId = ? and choiceId = ?");
			ResultSet resChoContents = null;
			while(resChoAnswer.next()){
				statChoContents.setInt(1, courseId);//����Ԥ����������(�γ�Id)
				statChoContents.setInt(2, resChoAnswer.getInt(1));//����Ԥ����������(���Id)
				resChoContents = statChoContents.executeQuery();//ִ��sql���
				while(resChoContents.next()){
					choContent = resChoContents.getString(1);
					a = resChoContents.getString(2);
					b = resChoContents.getString(3);
					c = resChoContents.getString(4);
					d = resChoContents.getString(5);
					choAnswer = resChoContents.getString(6);
				}
				//System.out.println("choContent+a+b+c+d+choAnswer:"+choContent+" "+a+" "+b+" "+c+" "+d+" "+choAnswer);
				//��ѡ�������𱣴浽list
				choList.add(new GetChoTestResultContents(choContent, a, b, choContent, d, choAnswer, resChoAnswer.getInt(1), resChoAnswer.getString(2)));
				//System.out.println("ѡ���ѯ����:"+resChoAnswer.getInt(1)+" "+resChoAnswer.getString(2));
			}
			//System.out.println(JSONArray.fromObject(choList));//ת��Ϊjson�����
			jsonArrayCho = JSONArray.fromObject(choList);//������ת��Ϊjson
			//�ж���
			//��ѯ�ж�����Ŀid�ʹ�
			PreparedStatement statJudAnswer = con.prepareStatement("select judgeId,judChoose from judgetestresults where stuNum = ? and courseId = ? and testTimeId = ?");
			statJudAnswer.setString(1, stuNum);//����Ԥ����������(ѧ��)
			statJudAnswer.setInt(2, courseId);//����Ԥ����������(�γ�Id)
			statJudAnswer.setInt(3, 1);//����ʱ��
			ResultSet resJudAnswer = statJudAnswer.executeQuery();//ִ��sql���
			PreparedStatement statJudContents = con.prepareStatement("select judContent, judAnswer from judges where courseId = ? and judgeId = ?");
			ResultSet resJudontents = null;
			while(resJudAnswer.next()){
				//System.out.println("�жϲ�ѯ����"+resJudAnswer.getInt(1)+" "+resJudAnswer.getString(2));
				statJudContents.setInt(1, courseId);
				statJudContents.setInt(2, resJudAnswer.getInt(1));//����Ԥ����������(���Id)
				resJudontents = statJudContents.executeQuery();//ִ��sql���
				while(resJudontents.next()){
					judContent = resJudontents.getString(1);
					judAnswer = resJudontents.getString(2);
				}
				judList.add(new GetJudTestResultContents(judContent, judAnswer, resJudAnswer.getInt(1), resJudAnswer.getString(2)));
			}
			//System.out.println(JSONArray.fromObject(judList));//ת��Ϊjson�����
			jsonArrayJud = JSONArray.fromObject(judList);////������ת��Ϊjson
			if(resJudontents != null){
				resJudontents.close();
			}
			if(statJudContents != null){
				statJudContents.close();
			}
		    if(resJudAnswer != null){
		    	resJudAnswer.close();
			}
		    if(statJudAnswer != null){
		    	statJudAnswer.close();
		    }
			if(resChoContents != null){
				resChoContents.close();
			}
			if(statChoContents != null){
				statChoContents.close();
			}
			if(resChoAnswer != null){
				resChoAnswer.close();
			}
			if(statChoAnswer != null){
				statChoAnswer.close();
			}
			if(resCourseName != null){
				resCourseName.close();
			}
			if(statCourseName != null){
				statCourseName.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArrayCho.toString()+" "+jsonArrayJud.toString();
	}
	public static void main(String[] args) {
		String retu = new GetTestResultContens("20140098", "��������ԭ��", null).getResultsContents();
		String s[] = retu.split(" ");
		JSONArray js = JSONArray.fromObject(s);
		System.out.println(js.size());
		System.out.println();
	}
}
