package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.HexadecimalConversion;
import excel.DbToExcel;
import excel.IsDbExists;

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
	    out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
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
		else if(toolName!=null && toolName.equals("downloadExcel")){
			//输入不为空
			if(url!=null && !url.equals("") && user!=null && !user.equals("") && pwd!=null && !pwd.equals("") && dbName!=null && !dbName.equals("") && tableName!=null && !tableName.equals("")){
				System.out.println("t1");
				//判断用户输入连接数据库表是否成功
				Boolean fl = new IsDbExists(url+"/"+dbName, user, pwd, tableName).isExists();
				//System.out.println("fl:"+fl);
				if(fl=true){
					new DbToExcel(url+"/"+dbName, user, pwd, tableName);//调用数据库导出Excel方法
					/*将文件流返回客户端服务器*/
					String path = "/root/file/"+tableName+".xls";
					File file = new File(path);// path是根据路径和文件名
					//判断文件是否存在,并且是刚刚创建的
				    if(file.exists() && (System.currentTimeMillis()-file.lastModified())<1000){
				    	System.out.println("t2");
				    	 String filename = file.getName();// 获取文件名称
						    InputStream fis = new BufferedInputStream(new FileInputStream(path));
						    byte[] buffer = new byte[fis.available()];
						    fis.read(buffer);
						    fis.close();
						    response.reset();
						    // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
						    response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
						    response.addHeader("Content-Length", "" + file.length());
						    OutputStream os = new BufferedOutputStream(response.getOutputStream());
						    response.setContentType("application/octet-stream");
						    os.write(buffer);// 输出文件
						    os.flush();
						    os.close();
				    }
				    else{
				    	//System.out.println("无法连接");
				    	response.sendRedirect("tools/dbToExcel.html");
				    }
				}
			}else{
				response.sendRedirect("tools/dbToExcel.html");
			}
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
