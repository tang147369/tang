package com.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.GetQuestions;
import com.dao.GetTestCourse;
import com.dao.GetTestResultContens;
import com.dao.SaveAnswer;
import com.dao.Select;

public class ServiceServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//��ȡ�ͻ��˴���������
		//��½��Ϣ
		String login = request.getParameter("login");//��½��ǩ
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//��ѯ���ڲ��ԵĿγ�
		String seleCourse = request.getParameter("seleCourse");//��ѯ��ǩ
		String seleStuNum = request.getParameter("seleStuNum");//��ѯʱ��ѧ��
		//��ȡ�������
		String getQuestion = request.getParameter("getQuestion");//��ȡ��ı�ǩ
		String questionStuNum = request.getParameter("questionStuNum");//��ȡ��ʱ��ѧ��
		String questionCourseName = request.getParameter("courseName");//��ȡ��ʱ�Ŀγ���
		//���Խ������
		String testAnswerflag = request.getParameter("testAnswerflag");//�ش��ǲ���
		String testRestuNum = request.getParameter("testRestuNum");//������ѧ��
		String reTestCourseName = request.getParameter("reTestCourseName");//���Կγ�
		String recordResults = request.getParameter("recordResults");//���Խ����Ϣ
		String testTime = request.getParameter("testTime");//����ʱ��
		//��ȡ������ϸ���
		String getTestResultContents = request.getParameter("getTestResultContents");//��ȡ�������ı�ǩ
		String TestResultContentsStuNum = request.getParameter("TestResultContentsStuNum");//ѧ��
		String TestResultContentsCourseName = request.getParameter("TestResultContentsCourseName");//�γ�
		String TestResultContentsTestTime = request.getParameter("TestResultContentsTestTime");//����ʱ��
		//�ж�Ϊ��½����
		if(login!=null && login.equals("login") && username!= null && password!=null){
			String retu = new Select().select(username, password);//���ú����ж��û���������
			System.out.println("����ˣ�"+retu);
			//���ص�½���,�Զ����в�����Ϊnull
			if(retu!=null && !retu.equals("")){
				//��½�ɹ�,���,ע��Println����ϻ��з�
				out.print("success,"+retu);
			}else{
				//��½ʧ��
				out.print("failed");
			}
		}
		//��ѯ�Ƿ������ڲ��ԵĿγ�
		else if(seleCourse != null && seleCourse.equals("course") && seleStuNum != null){
			String testCourse = new GetTestCourse().testCourse(seleStuNum);
			//�����ڲ��ԵĿγ�
			if(testCourse!=null && !testCourse.equals("")){
				out.print(testCourse);
			}
			//�����ڲ��ԵĿγ�
			else{
				out.print("��");
			}
		}
		//��ȡ�Ⲣ���ⷵ�ظ��ͻ���
		else if(getQuestion != null && getQuestion.equals("getQuestion") && questionStuNum != null){
			System.out.println("server_questionStuNum:"+questionStuNum);
			System.out.println("questionCourseName:"+questionCourseName);
			String questions = new GetQuestions(questionStuNum,questionCourseName).getChos()+" "+new GetQuestions(questionStuNum,questionCourseName).getJus();//�����ݿ��ȡѡ�������ж���
			//System.out.println("server_testCourse:"+questions);
			out.print(questions);//���ص������
		}
		//������
		else if(testAnswerflag != null && testAnswerflag.equals("testAnswerflag") && testRestuNum != null){
			String answerRetu = new SaveAnswer(testRestuNum, reTestCourseName, recordResults, testTime).save();
			out.print(answerRetu);//���ؽ��success��failed
		}
		//��������ϸ����
		else if(getTestResultContents != null && getTestResultContents.equals("getTestResultContents") && TestResultContentsStuNum != null){
			System.out.println("server TestResultContentsStuNum:"+TestResultContentsStuNum+" TestResultContentsCourseName:"+TestResultContentsCourseName+" TestResultContentsTestTime:"+TestResultContentsTestTime);//���
			String TestResultContens = new GetTestResultContens(TestResultContentsStuNum, TestResultContentsCourseName, TestResultContentsTestTime).getResultsContents();//������ͺ�����ȡ������ϸ���
			out.print(TestResultContens);//����������ϸ���
		}
		out.flush();
		out.close();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("Get");
		out.flush();
		out.close();
	}
}
