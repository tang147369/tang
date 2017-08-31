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
		//获取从客户端传来的值
		String toolName = request.getParameter("toolName");//工具的名称
		String num = request.getParameter("num");//需要转化的进制数
		String type = request.getParameter("type");//进制的类型
		//导出数据库的表
		String url = request.getParameter("dbUrl");//数据库地址和端口
		String user = request.getParameter("user");//用户名
		String pwd = request.getParameter("pwd");//数据库密码
		String dbName = request.getParameter("dbName");//数据库名
		String tableName = request.getParameter("table");//表名
		//判断是哪个工具
		//进制转换工具
		if(toolName!=null && toolName.equals("conversion")){
			//调用进制转换函数并获得返回值
			StringBuffer s = new HexadecimalConversion().conversion(num,Integer.parseInt(type));
			out.println(s);
		}
		//数据库表导出工具
		else if(toolName!=null && toolName.equals("downloadExcel")){
			out.print("info: "+url+"/"+dbName+" "+user+" "+pwd+" "+tableName);
			new DbToExcel(url+"/"+dbName, user, pwd, tableName);//调用数据库导出Excel方法
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
