package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTestCourse {
	private Connection conn;
	private String course;//查询到的正在测试的课程
	private ResultSet res,resSele;
	private PreparedStatement stat,statSele;
	public String testCourse(String StuNum){
		conn = new GetConnection().getConnction();//获得连接对象
		try {
			//预编译,该考生正在测试的科目
			stat = conn.prepareStatement("select * from chooseCourse where stuNum = ? and test='1'");
			//设置预编译语句参数
			stat.setString(1, StuNum);
			res = stat.executeQuery();//执行SQL语句
			//有正在考试的课程
			if(res.next()==true){
				//查询课程名称,预编译
				PreparedStatement statSele = conn.prepareStatement("select courseName from course where courseId=?");
				statSele.setInt(1, res.getInt(3));//设置预编译语句参数
				resSele = statSele.executeQuery();
				if(resSele.next()==true){
					course = resSele.getString(1);
				}
			}
			if(resSele!=null){
				resSele.close();
			}
			if(statSele!=null){
				statSele.close();
			}
			if(res!=null){
				res.close();
			}
			if(stat!=null){
				stat.close();
			}
			if(conn !=null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}
	public static void main(String[] args) {
		System.out.println(new GetTestCourse().testCourse("20140098"));
	}
}
