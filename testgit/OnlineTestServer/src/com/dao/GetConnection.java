package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {
	private Connection conn;//声明Connecyion对象
	private String url,user,password;
	public Connection getConnction(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url="jdbc:mysql://127.0.0.1:3308/jiaowu?useUnicode=true&characterEncoding=UTF-8";
			user="root";
			password="_a147369852_b123+c";
			conn = DriverManager.getConnection(url, user, password);//获取与数据库的连接
			if(conn!=null){
				System.out.println("数据库连接成功 ");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	/*public static void main(String[] args) {
		new GetConnection().getConnction();
	}*/
}
