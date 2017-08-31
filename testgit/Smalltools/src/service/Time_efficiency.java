package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.BubbleSort;
import action.HeapSort;
import action.InsertSort;
import action.InsertSort1;
import action.MergeSort;
import action.QuickSort;
import action.SelectSort;
import action.ShellSort;

public class Time_efficiency extends HttpServlet {
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
		String num = request.getParameter("input_num");//输入的位数
		String type = request.getParameter("type");//排序的类型
		String order = request.getParameter("order");//排序命令
		String randomNumber = request.getParameter("randomNumber");//从客户端获取得到的
		String regex = "\\d{1,6}";
		StringBuffer sNumList=new StringBuffer();
		StringBuffer orderList = new StringBuffer();
		int newNum=0;
		int numList[]=null;//储存随机数
		System.out.println("num:"+num+" order:"+order+" type:"+type+" numList:"+numList);
		if(numList!=null){
			for(int x:numList){
				System.out.print(x+" .");
			}
		}
		//生成随机数
		if(num != null && order == null){
			//再次判断是否合法 
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
					out.print("false");
				}
			}
		}
		//排序
		else if(num == null && randomNumber!=null && order.equals("order")){
			String str[] = randomNumber.split(" ");//按空格分割字符串
			int randomList[]=new int[str.length];
			//转化为整型数组
			for(int i=0;i<str.length;i++){
				randomList[i] = Integer.parseInt(str[i]);
				//System.out.print(randomList[i]+"|");
			}
			//直接插入排序
			if(type.equals("insert")){
				orderList = new InsertSort().insertSort(randomList);
			}
			//折半插入排序
			else if(type.equals("insert1")){
				orderList = new InsertSort1().insertSort1(randomList);
			}
			else if(type.equals("shell")){
				orderList = new ShellSort().shell(randomList);
			}
			//冒泡排序
			else if(type.equals("bubble")){
				orderList = new BubbleSort().bubbleSort(randomList);
				//System.out.println("order"+orderList);
			}
			else if(type.equals("quick")){
				int list[]=randomList.clone();
				//快速排序有递归调用，时间计算不在函数内部进行
				long start = System.currentTimeMillis();
				int a[] = new QuickSort().quickSort(list, 0, list.length-1);
				long end = System.currentTimeMillis();
				//时间转换
				String timeStr = Long.toString((end-start));
				for(int i=0;i<a.length;i++){
					orderList.append(a[i]).append(" ");
				}
				orderList.append(",").append("耗时:").append(timeStr).append("ms");
			}
			else if(type.equals("select")){
				//直接选择排序
				orderList = new SelectSort().select(randomList);
			}
			else if(type.equals("heap")){
				//堆排序
				orderList = new HeapSort().heapSort(randomList);
			}
			else if(type.equals("merge")){
				orderList = new MergeSort().mergeSort(randomList, randomList.length);
			}
			/*else if(type.equals("radix")){
				
			}*/
			out.println(orderList);
			
		}
		else{
			out.print("false");
		}
		out.flush();
		out.close();
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
