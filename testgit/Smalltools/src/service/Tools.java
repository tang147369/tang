package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.HexadecimalConversion;
import excel.DbToExcel;

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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
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
			//���벻Ϊ��
			if(url!=null && !url.equals("") && user!=null && !user.equals("") && pwd!=null && !pwd.equals("") && dbName!=null && !dbName.equals("") && tableName!=null && !tableName.equals("")){
				new DbToExcel(url+"/"+dbName, user, pwd, tableName);//�������ݿ⵼��Excel����
				/*���ļ������ؿͻ��˷�����*/
				String path = "C://Users/Administrator/Desktop/temp/"+tableName+".xls";
				File file = new File(path);// path�Ǹ���·�����ļ���
			    String filename = file.getName();// ��ȡ�ļ�����
			    InputStream fis = new BufferedInputStream(new FileInputStream(path));
			    byte[] buffer = new byte[fis.available()];
			    fis.read(buffer);
			    fis.close();
			    response.reset();
			    // ��ȥ���ļ������еĿո�,Ȼ��ת�������ʽΪutf-8,��֤����������,����ļ�������������������ؿ����Զ���ʾ���ļ���
			    response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
			    response.addHeader("Content-Length", "" + file.length());
			    OutputStream os = new BufferedOutputStream(response.getOutputStream());
			    response.setContentType("application/octet-stream");
			    os.write(buffer);// ����ļ�
			    os.flush();
			    os.close();
			}else{
				out.print("<script>���벻��Ϊ�գ�</script>");
			}
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
