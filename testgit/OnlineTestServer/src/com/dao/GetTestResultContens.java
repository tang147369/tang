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
	private List<GetChoTestResultContents> choList=new ArrayList<GetChoTestResultContents>();//存储选择题
	private List<GetJudTestResultContents> judList=new ArrayList<GetJudTestResultContents>();//存储判断题
	private String choContent,a,b,c,d,choAnswer,judContent,judAnswer;
	private JSONArray jsonArrayCho,jsonArrayJud;//保存选择题，判断题作答结果
	public GetTestResultContens(String stuNum,String courseName,String testTime){
		this.stuNum = stuNum;
		this.courseName = courseName;
		this.testTime = testTime;
	}
	public String getResultsContents(){
		con = new GetConnection().getConnction();//获取数据库连接
		try {
			//查询课程id
			PreparedStatement statCourseName = con.prepareStatement("select courseId from course where courseName = ?");
			statCourseName.setString(1, courseName);//设置预编译语句参数(课程名)
			ResultSet resCourseName = statCourseName.executeQuery();//执行SQL语句查询课程id
			while(resCourseName.next()){
				courseId = resCourseName.getInt(1);//获取查询结果(课程id)
				//System.out.println("getTestCourse courseId1:"+courseId);
			}
			//选择题
			
			//查询选择题题目id和答案
			PreparedStatement statChoAnswer = con.prepareStatement("select choiceId,choChoose from chotestresults where stuNum = ? and courseId = ? and testTimeId = ?");
			statChoAnswer.setString(1, stuNum);//设置预编译语句参数(学号)
			statChoAnswer.setInt(2, courseId);//设置预编译语句参数(课程Id)
			statChoAnswer.setInt(3, 1);//测试时间
			ResultSet resChoAnswer = statChoAnswer.executeQuery();//执行sql语句
			//选择题内容
			PreparedStatement statChoContents = con.prepareStatement("select choContent, a, b, c, d, choAnswer from choices where courseId = ? and choiceId = ?");
			ResultSet resChoContents = null;
			while(resChoAnswer.next()){
				statChoContents.setInt(1, courseId);//设置预编译语句参数(课程Id)
				statChoContents.setInt(2, resChoAnswer.getInt(1));//设置预编译语句参数(题号Id)
				resChoContents = statChoContents.executeQuery();//执行sql语句
				while(resChoContents.next()){
					choContent = resChoContents.getString(1);
					a = resChoContents.getString(2);
					b = resChoContents.getString(3);
					c = resChoContents.getString(4);
					d = resChoContents.getString(5);
					choAnswer = resChoContents.getString(6);
				}
				//System.out.println("choContent+a+b+c+d+choAnswer:"+choContent+" "+a+" "+b+" "+c+" "+d+" "+choAnswer);
				//将选择题作答保存到list
				choList.add(new GetChoTestResultContents(choContent, a, b, choContent, d, choAnswer, resChoAnswer.getInt(1), resChoAnswer.getString(2)));
				//System.out.println("选择查询作答:"+resChoAnswer.getInt(1)+" "+resChoAnswer.getString(2));
			}
			//System.out.println(JSONArray.fromObject(choList));//转化为json后输出
			jsonArrayCho = JSONArray.fromObject(choList);//将对象转化为json
			//判断题
			//查询判断题题目id和答案
			PreparedStatement statJudAnswer = con.prepareStatement("select judgeId,judChoose from judgetestresults where stuNum = ? and courseId = ? and testTimeId = ?");
			statJudAnswer.setString(1, stuNum);//设置预编译语句参数(学号)
			statJudAnswer.setInt(2, courseId);//设置预编译语句参数(课程Id)
			statJudAnswer.setInt(3, 1);//测试时间
			ResultSet resJudAnswer = statJudAnswer.executeQuery();//执行sql语句
			PreparedStatement statJudContents = con.prepareStatement("select judContent, judAnswer from judges where courseId = ? and judgeId = ?");
			ResultSet resJudontents = null;
			while(resJudAnswer.next()){
				//System.out.println("判断查询作答："+resJudAnswer.getInt(1)+" "+resJudAnswer.getString(2));
				statJudContents.setInt(1, courseId);
				statJudContents.setInt(2, resJudAnswer.getInt(1));//设置预编译语句参数(题号Id)
				resJudontents = statJudContents.executeQuery();//执行sql语句
				while(resJudontents.next()){
					judContent = resJudontents.getString(1);
					judAnswer = resJudontents.getString(2);
				}
				judList.add(new GetJudTestResultContents(judContent, judAnswer, resJudAnswer.getInt(1), resJudAnswer.getString(2)));
			}
			//System.out.println(JSONArray.fromObject(judList));//转化为json后输出
			jsonArrayJud = JSONArray.fromObject(judList);////将对象转化为json
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
		String retu = new GetTestResultContens("20140098", "计算机组成原理", null).getResultsContents();
		String s[] = retu.split(" ");
		JSONArray js = JSONArray.fromObject(s);
		System.out.println(js.size());
		System.out.println();
	}
}
