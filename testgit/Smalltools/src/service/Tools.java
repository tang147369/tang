package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excel.DbToExcel;
import action.HexadecimalConversion;

public class Tools extends HttpServlet {
	public Tools() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("get");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//��ȡ�ӿͻ��˴�����ֵ
		String toolName = request.getParameter("toolName");//���ߵ�����
		String num = request.getParameter("num");//��Ҫת���Ľ�����
		String type = request.getParameter("type");//���Ƶ�����
		//�������ݿ�ı�
		String url = request.getParameter("dbUrl");//���ݿ��ַ�Ͷ˿�
		String user = request.getParameter("user");//�û���
		String pwd = request.getParameter("pwd");//���ݿ�����
		String dbName = request.getParameter("dbName");//���ݿ���
		String tableName = request.getParameter("table");//����
		//�ж����ĸ�����
		//����ת������
		if(toolName!=null && toolName.equals("conversion")){
			//���ý���ת����������÷���ֵ
			StringBuffer s = new HexadecimalConversion().conversion(num,Integer.parseInt(type));
			out.println(s);
		}
		//���ݿ��������
		else if(toolName!=null && toolName.equals("downloadExcel")){
			out.print("info: "+url+"/"+dbName+" "+user+" "+pwd+" "+tableName);
			new DbToExcel(url+"/"+dbName, user, pwd, tableName);//�������ݿ⵼��Excel����
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
