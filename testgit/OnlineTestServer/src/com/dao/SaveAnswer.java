package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveAnswer {
	private Connection con;
	private String choStr,JudgeStr;//选择题，判断题拼接结果
//	private String recordChoStr[],recordJudgeStr[];//每道选择题，判断题题号，作答与答案
//	private int choNum[],judgeNum[];//选择题判断题题号
//	private String choChoose,JudgeChooose;//选择判断题作答
//	private String choResult[],judgeResult[];//选择判断题结果(true or false)
	private String testRestuNum,reTestCourseName,testTime,recordResults;
	private int reTestCourseId; //测试课程Id
	private int chT,chF;//保存选择题作答结果对错个数
	private int jdT,jdF;//保判断题作答结果对错个数
	public SaveAnswer(String testRestuNum,String reTestCourseName,String recordResults,String testTime){
		this.testRestuNum = testRestuNum;
		this.reTestCourseName = reTestCourseName;
		this.testTime = testTime;
		this.recordResults = recordResults;
		System.out.println("server:"+testRestuNum+" "+reTestCourseName+" "+recordResults+" "+testTime);
	}
	public String save(){
		con = new GetConnection().getConnction();//获得数据库连接对象
		String str[] = recordResults.split(" ");//以空格分开选择判断
		choStr = str[0];
		JudgeStr = str[1];
		System.out.println("JudgeStr:"+choStr+""+JudgeStr);
		String recordChoStr[] = choStr.split("\\|");//以“|”分割出每道选择题
		try {
			PreparedStatement statCourse = con.prepareStatement("select courseId from course where courseName = ?");//创建prepareStatement对象
			statCourse.setString(1, reTestCourseName);//设置预编译语句参数
			ResultSet res = statCourse.executeQuery();//查询结果
			//如果查询结果不为空
			if(res.next()){
				reTestCourseId = res.getInt(1);
			}
			PreparedStatement choStat = con.prepareStatement("insert into chotestresults(stuNum,courseId,choiceId,choChoose,choResults,testTimeId) values(?,?,?,?,?,?)");//创建prepareStatement对象
			//循环添加语句
			for(int i=0;i<recordChoStr.length;i++){
				String choResultInfo[] =  recordChoStr[i].split("\\,");//根据“,”分割出每道选择题题号作答和答案,需用转义
				int choNum = Integer.parseInt(choResultInfo[0]);//选择题题号
				String choChoose = choResultInfo[1];//选择题作答
				String choResult = null;//保存判断作答结果
				//判断作答结果对错并保存，对是“T”，错是“F”
				if(choResultInfo[1].equals(choResultInfo[2])){
					choResult = "T";
					chT++;//选择题正确时+1
				}else{
					choResult = "F";
					chF++;//选择题错误时+1
				}
				//设置预编译语句参数
				choStat.setString(1, testRestuNum);//学号
				choStat.setInt(2, reTestCourseId);//测试课程Id
				choStat.setInt(3, choNum);//选择题id
				choStat.setString(4, choChoose);//选择题作答
				choStat.setString(5, choResult);//选择题作答判断结果
				choStat.setInt(6, 1);//测试时间
				choStat.executeUpdate();//执行sql语句
			}
			PreparedStatement judgeStat = con.prepareStatement("insert into judgetestresults(stuNum,courseId,judgeId,judChoose,judResults,testTimeId) values(?,?,?,?,?,?)");
			String recordJudgeStr[] = JudgeStr.split("\\|");//以“|”分割出每道填空题
			for(int i=0;i<recordJudgeStr.length;i++){
				String judgeResultInfo[] =  recordJudgeStr[i].split("\\,");//根据“,”分割出每道判断题题号作答和答案,需用转义
				int choNum = Integer.parseInt(judgeResultInfo[0]);//判断题题号
				String judgeChoose = judgeResultInfo[1];//判断题作答
				String judgeResult = null;//保存判断作答结果
				//判断作答结果对错并保存，对是“T”，错是“F”
				if(judgeResultInfo[1].equals(judgeResultInfo[2])){
					judgeResult = "T";
					jdT++;//判断题正确时+1
				}else{
					judgeResult = "F";
					jdF++;//选择题错误时+1
				}
				//设置预编译语句参数
				judgeStat.setString(1, testRestuNum);//学号
				judgeStat.setInt(2, reTestCourseId);//测试课程Id
				judgeStat.setInt(3, choNum);//判断题id
				judgeStat.setString(4, judgeChoose);//判断题作答
				judgeStat.setString(5, judgeResult);//判断题作答判断结果
				judgeStat.setInt(6, 1);//测试时间
				judgeStat.executeUpdate();//执行sql语句
			}
			if(judgeStat != null){
				judgeStat.close();
			}
			if(statCourse != null){
				statCourse.close();
			}
			if(res != null){
				res.close();
			}
			if(choStat != null){
				choStat.close();
			}
			if(con != null){
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "success"+" "+"选择题正确"+chT+"道,错误"+chF+"道.判断题正确"+jdT+"道,错误"+jdF+"道";
	}
}
