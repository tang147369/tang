package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetTestCourse {
	private Connection conn;
	private String course;//��ѯ�������ڲ��ԵĿγ�
	private ResultSet res,resSele;
	private PreparedStatement stat,statSele;
	public String testCourse(String StuNum){
		conn = new GetConnection().getConnction();//������Ӷ���
		try {
			//Ԥ����,�ÿ������ڲ��ԵĿ�Ŀ
			stat = conn.prepareStatement("select * from chooseCourse where stuNum = ? and test='1'");
			//����Ԥ����������
			stat.setString(1, StuNum);
			res = stat.executeQuery();//ִ��SQL���
			//�����ڿ��ԵĿγ�
			if(res.next()==true){
				//��ѯ�γ�����,Ԥ����
				PreparedStatement statSele = conn.prepareStatement("select courseName from course where courseId=?");
				statSele.setInt(1, res.getInt(3));//����Ԥ����������
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
