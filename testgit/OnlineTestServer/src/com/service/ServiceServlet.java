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
		//获取客户端传来的数据
		//登陆信息
		String login = request.getParameter("login");//登陆标签
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//查询正在测试的课程
		String seleCourse = request.getParameter("seleCourse");//查询标签
		String seleStuNum = request.getParameter("seleStuNum");//查询时的学号
		//获取题的请求
		String getQuestion = request.getParameter("getQuestion");//获取题的标签
		String questionStuNum = request.getParameter("questionStuNum");//获取题时的学号
		String questionCourseName = request.getParameter("courseName");//获取题时的课程名
		//测试结果部分
		String testAnswerflag = request.getParameter("testAnswerflag");//回答标记参数
		String testRestuNum = request.getParameter("testRestuNum");//测试人学号
		String reTestCourseName = request.getParameter("reTestCourseName");//测试课程
		String recordResults = request.getParameter("recordResults");//测试结果信息
		String testTime = request.getParameter("testTime");//测试时间
		//获取作答详细结果
		String getTestResultContents = request.getParameter("getTestResultContents");//获取作答结果的标签
		String TestResultContentsStuNum = request.getParameter("TestResultContentsStuNum");//学号
		String TestResultContentsCourseName = request.getParameter("TestResultContentsCourseName");//课程
		String TestResultContentsTestTime = request.getParameter("TestResultContentsTestTime");//测试时间
		//判断为登陆请求
		if(login!=null && login.equals("login") && username!= null && password!=null){
			String retu = new Select().select(username, password);//调用函数判断用户名与密码
			System.out.println("服务端："+retu);
			//返回登陆结果,对对象有操作后不为null
			if(retu!=null && !retu.equals("")){
				//登陆成功,输出,注意Println会加上换行符
				out.print("success,"+retu);
			}else{
				//登陆失败
				out.print("failed");
			}
		}
		//查询是否有正在测试的课程
		else if(seleCourse != null && seleCourse.equals("course") && seleStuNum != null){
			String testCourse = new GetTestCourse().testCourse(seleStuNum);
			//有正在测试的课程
			if(testCourse!=null && !testCourse.equals("")){
				out.print(testCourse);
			}
			//无正在测试的课程
			else{
				out.print("无");
			}
		}
		//获取题并将题返回给客户端
		else if(getQuestion != null && getQuestion.equals("getQuestion") && questionStuNum != null){
			System.out.println("server_questionStuNum:"+questionStuNum);
			System.out.println("questionCourseName:"+questionCourseName);
			String questions = new GetQuestions(questionStuNum,questionCourseName).getChos()+" "+new GetQuestions(questionStuNum,questionCourseName).getJus();//从数据库获取选择题与判断题
			//System.out.println("server_testCourse:"+questions);
			out.print(questions);//返回到服务端
		}
		//作答结果
		else if(testAnswerflag != null && testAnswerflag.equals("testAnswerflag") && testRestuNum != null){
			String answerRetu = new SaveAnswer(testRestuNum, reTestCourseName, recordResults, testTime).save();
			out.print(answerRetu);//返回结果success或failed
		}
		//作答结果详细内容
		else if(getTestResultContents != null && getTestResultContents.equals("getTestResultContents") && TestResultContentsStuNum != null){
			System.out.println("server TestResultContentsStuNum:"+TestResultContentsStuNum+" TestResultContentsCourseName:"+TestResultContentsCourseName+" TestResultContentsTestTime:"+TestResultContentsTestTime);//输出
			String TestResultContens = new GetTestResultContens(TestResultContentsStuNum, TestResultContentsCourseName, TestResultContentsTestTime).getResultsContents();//调用类和函数获取作答详细情况
			out.print(TestResultContens);//返回作答详细结果
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
