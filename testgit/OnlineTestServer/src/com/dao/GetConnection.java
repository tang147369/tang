package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class GetConnection {
	private Connection conn;//����Connecyion����
	private String url,user,password;
	public Connection getConnction(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			url="jdbc:mysql://127.0.0.1:3308/jiaowu?useUnicode=true&characterEncoding=UTF-8";
			user="root";
			password="_a147369852_b123+c";
			conn = DriverManager.getConnection(url, user, password);//��ȡ�����ݿ������
			if(conn!=null){
				System.out.println("���ݿ����ӳɹ� ");
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
