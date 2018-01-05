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
	//获取选择题
	public GetQuestions(String stuNum,String courseName){
		this.stuNum = stuNum;
		this.courseName = courseName;
		con = new GetConnection().getConnction();//调用数据库连接方法
		//预编译
		try {
			PreparedStatement stat_ch = con.prepareStatement("select courseId from course where courseName = ?");
			stat_ch.setString(1, courseName);//设置预编译语句参数(课程名)
			ResultSet res_ch = stat_ch.executeQuery();//执行SQL语句查询课程id
			while(res_ch.next()){
				courseId = res_ch.getInt(1);//获取查询结果(课程id)
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
		Vector<GetChoices> listChoice = new Vector<GetChoices>();//用于保存每个选择题对象
		try {
			con = new GetConnection().getConnction();//调用数据库连接方法
			PreparedStatement stat = con.prepareStatement("select * from choices where courseId = ?");
			stat.setInt(1, courseId);//设置预编译语句参数(课程id)
			ResultSet res = stat.executeQuery();//执行SQL语句
			//遍历查询选择题结果集
			while(res.next()){
				int choiceId = res.getInt(1);//编号
				String choContent = res.getString(2);//题干
				//选项
				String a = res.getString(3);
				String b = res.getString(4);
				String c = res.getString(5);
				String d = res.getString(6);
				String choAnswer = res.getString(7);//答案
				//将每个选择题对象添加到集合中
				listChoice.add(new GetChoices(choiceId, choContent,a, b, c, d, choAnswer));
			}
			jsonArrayChoice = JSONArray.fromObject(listChoice);//List转换成JSON数据
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
	//获取判断题
	public String getJus(){
		Vector<GetJudges> listJudge = new Vector<GetJudges>();//用于保存每个判断题对象
		con = new GetConnection().getConnction();//调用数据库连接方法
		try {
			//预编译
			PreparedStatement stat = con.prepareStatement("select * from judges where courseId = ?");
			stat.setInt(1, courseId);
			ResultSet res = stat.executeQuery();//执行SQL语句
			//遍历查询判断题结果集
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
		String str = new GetQuestions("20140098","算法设计与分析基础").getChos()+" "+new GetQuestions("20140098","计算机组成原理").getJus();
		String s[] = str.split(" ");
		JSONArray json = JSONArray.fromObject(s);//将数组转换为JSON
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
