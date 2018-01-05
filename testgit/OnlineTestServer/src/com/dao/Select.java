package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Select {
	private Connection conn;//声明Connection对象
	private String informationStr;
	private StringBuffer str = new StringBuffer();
	public String select(String username,String password){
		try {
			conn = new GetConnection().getConnction();//调用数据库连接方法
			//预编译
			PreparedStatement stat = conn.prepareStatement("select * from students where name=? and stuNum=?");
			//设置预编译语句参数
			stat.setString(1, username);
			stat.setString(2, password);
			ResultSet res = stat.executeQuery();
			//判断是否有查找成功
			if(res.next()==true){
				//获取查询数据
				String stuName = res.getString(1);
				String stuNum = res.getString(2);
				int ageId = res.getInt(3);
				int collegeId = res.getInt(4);
				int majorId = res.getInt(5);
				//预编译
				PreparedStatement statInfo = conn.prepareStatement("select ageName,collegeName,majorName from classAge ca,college co,major mj where ca.ageId = ? and co.collegeId = ? and mj.majorId = ?");
				//设置预编译语句参数
				statInfo.setInt(1, ageId);//年纪ID
				statInfo.setInt(2,collegeId);//学院ID
				statInfo.setInt(3,majorId);//专业ID
				ResultSet stuRes = statInfo.executeQuery();//执行SQL语句
				//获取查询数据
				while(stuRes.next()){
					//System.out.println(stuName+" "+stuNum+" "+stuRes.getString(1)+" "+stuRes.getString(2)+" "+stuRes.getString(3));
					str.append(stuName).append(",").append(stuNum).append(",").append(stuRes.getString(1)).append(",").append(stuRes.getString(2)).append(",").append(stuRes.getString(3));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		informationStr=str.toString();//将StringBuffer转换为String
		return informationStr;
	}
	public static void main(String[] args) {
		System.out.println("result:"+new Select().select("tang", "20140098"));
	}
}
