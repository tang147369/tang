package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Select {
	private Connection conn;//����Connection����
	private String informationStr;
	private StringBuffer str = new StringBuffer();
	public String select(String username,String password){
		try {
			conn = new GetConnection().getConnction();//�������ݿ����ӷ���
			//Ԥ����
			PreparedStatement stat = conn.prepareStatement("select * from students where name=? and stuNum=?");
			//����Ԥ����������
			stat.setString(1, username);
			stat.setString(2, password);
			ResultSet res = stat.executeQuery();
			//�ж��Ƿ��в��ҳɹ�
			if(res.next()==true){
				//��ȡ��ѯ����
				String stuName = res.getString(1);
				String stuNum = res.getString(2);
				int ageId = res.getInt(3);
				int collegeId = res.getInt(4);
				int majorId = res.getInt(5);
				//Ԥ����
				PreparedStatement statInfo = conn.prepareStatement("select ageName,collegeName,majorName from classAge ca,college co,major mj where ca.ageId = ? and co.collegeId = ? and mj.majorId = ?");
				//����Ԥ����������
				statInfo.setInt(1, ageId);//���ID
				statInfo.setInt(2,collegeId);//ѧԺID
				statInfo.setInt(3,majorId);//רҵID
				ResultSet stuRes = statInfo.executeQuery();//ִ��SQL���
				//��ȡ��ѯ����
				while(stuRes.next()){
					//System.out.println(stuName+" "+stuNum+" "+stuRes.getString(1)+" "+stuRes.getString(2)+" "+stuRes.getString(3));
					str.append(stuName).append(",").append(stuNum).append(",").append(stuRes.getString(1)).append(",").append(stuRes.getString(2)).append(",").append(stuRes.getString(3));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		informationStr=str.toString();//��StringBufferת��ΪString
		return informationStr;
	}
	public static void main(String[] args) {
		System.out.println("result:"+new Select().select("tang", "20140098"));
	}
}
