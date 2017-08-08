package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BubbleSort;

public class Time_efficiency extends HttpServlet {
	int numList[];//储存随机数
	/**
	 * Constructor of the object.
	 */
	public Time_efficiency() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String num = request.getParameter("input_num");
		String order = request.getParameter("order");
		String regex = "\\d{1,6}";
		StringBuffer sNumList,orderList;
		int newNum=0;
		System.out.println("num:"+num+"  order:"+order);
		
		//生成随机数
		if(num != null && order == null){
			if(num.matches(regex)){
				newNum = Integer.parseInt(num);
				System.out.println("newNum:"+newNum);
				if(newNum>0 && newNum<=99999){
					numList = new int[newNum];
					sNumList= new StringBuffer();
					for(int i=0;i<newNum;i++){
						numList[i]=(int)(Math.random()*1000);
						sNumList.append(numList[i]).append(" ");
					}
					out.print(sNumList);
				}else{
					out.print("input_false");
				}
			}
		}
		//冒泡排序
		else if(order.equals("order") && num!=null){
			//调用BubbleSort类的冒泡排序方法并以StingBuffer形式返回
			BubbleSort bubble = new BubbleSort();
			orderList = bubble.bubbleSort(numList);
			out.println(orderList);
			
		}
		else{
			out.print("input_false");
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
