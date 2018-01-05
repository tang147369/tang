package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveAnswer {
	private Connection con;
	private String choStr,JudgeStr;//ѡ���⣬�ж���ƴ�ӽ��
//	private String recordChoStr[],recordJudgeStr[];//ÿ��ѡ���⣬�ж�����ţ��������
//	private int choNum[],judgeNum[];//ѡ�����ж������
//	private String choChoose,JudgeChooose;//ѡ���ж�������
//	private String choResult[],judgeResult[];//ѡ���ж�����(true or false)
	private String testRestuNum,reTestCourseName,testTime,recordResults;
	private int reTestCourseId; //���Կγ�Id
	private int chT,chF;//����ѡ�����������Դ����
	private int jdT,jdF;//���ж����������Դ����
	public SaveAnswer(String testRestuNum,String reTestCourseName,String recordResults,String testTime){
		this.testRestuNum = testRestuNum;
		this.reTestCourseName = reTestCourseName;
		this.testTime = testTime;
		this.recordResults = recordResults;
		System.out.println("server:"+testRestuNum+" "+reTestCourseName+" "+recordResults+" "+testTime);
	}
	public String save(){
		con = new GetConnection().getConnction();//������ݿ����Ӷ���
		String str[] = recordResults.split(" ");//�Կո�ֿ�ѡ���ж�
		choStr = str[0];
		JudgeStr = str[1];
		System.out.println("JudgeStr:"+choStr+""+JudgeStr);
		String recordChoStr[] = choStr.split("\\|");//�ԡ�|���ָ��ÿ��ѡ����
		try {
			PreparedStatement statCourse = con.prepareStatement("select courseId from course where courseName = ?");//����prepareStatement����
			statCourse.setString(1, reTestCourseName);//����Ԥ����������
			ResultSet res = statCourse.executeQuery();//��ѯ���
			//�����ѯ�����Ϊ��
			if(res.next()){
				reTestCourseId = res.getInt(1);
			}
			PreparedStatement choStat = con.prepareStatement("insert into chotestresults(stuNum,courseId,choiceId,choChoose,choResults,testTimeId) values(?,?,?,?,?,?)");//����prepareStatement����
			//ѭ��������
			for(int i=0;i<recordChoStr.length;i++){
				String choResultInfo[] =  recordChoStr[i].split("\\,");//���ݡ�,���ָ��ÿ��ѡ�����������ʹ�,����ת��
				int choNum = Integer.parseInt(choResultInfo[0]);//ѡ�������
				String choChoose = choResultInfo[1];//ѡ��������
				String choResult = null;//�����ж�������
				//�ж��������Դ����棬���ǡ�T�������ǡ�F��
				if(choResultInfo[1].equals(choResultInfo[2])){
					choResult = "T";
					chT++;//ѡ������ȷʱ+1
				}else{
					choResult = "F";
					chF++;//ѡ�������ʱ+1
				}
				//����Ԥ����������
				choStat.setString(1, testRestuNum);//ѧ��
				choStat.setInt(2, reTestCourseId);//���Կγ�Id
				choStat.setInt(3, choNum);//ѡ����id
				choStat.setString(4, choChoose);//ѡ��������
				choStat.setString(5, choResult);//ѡ���������жϽ��
				choStat.setInt(6, 1);//����ʱ��
				choStat.executeUpdate();//ִ��sql���
			}
			PreparedStatement judgeStat = con.prepareStatement("insert into judgetestresults(stuNum,courseId,judgeId,judChoose,judResults,testTimeId) values(?,?,?,?,?,?)");
			String recordJudgeStr[] = JudgeStr.split("\\|");//�ԡ�|���ָ��ÿ�������
			for(int i=0;i<recordJudgeStr.length;i++){
				String judgeResultInfo[] =  recordJudgeStr[i].split("\\,");//���ݡ�,���ָ��ÿ���ж����������ʹ�,����ת��
				int choNum = Integer.parseInt(judgeResultInfo[0]);//�ж������
				String judgeChoose = judgeResultInfo[1];//�ж�������
				String judgeResult = null;//�����ж�������
				//�ж��������Դ����棬���ǡ�T�������ǡ�F��
				if(judgeResultInfo[1].equals(judgeResultInfo[2])){
					judgeResult = "T";
					jdT++;//�ж�����ȷʱ+1
				}else{
					judgeResult = "F";
					jdF++;//ѡ�������ʱ+1
				}
				//����Ԥ����������
				judgeStat.setString(1, testRestuNum);//ѧ��
				judgeStat.setInt(2, reTestCourseId);//���Կγ�Id
				judgeStat.setInt(3, choNum);//�ж���id
				judgeStat.setString(4, judgeChoose);//�ж�������
				judgeStat.setString(5, judgeResult);//�ж��������жϽ��
				judgeStat.setInt(6, 1);//����ʱ��
				judgeStat.executeUpdate();//ִ��sql���
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
		
		return "success"+" "+"ѡ������ȷ"+chT+"��,����"+chF+"��.�ж�����ȷ"+jdT+"��,����"+jdF+"��";
	}
}
