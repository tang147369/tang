package excel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsDbExists {
	private String driver="com.mysql.jdbc.Driver";
	private String url,user,pwd,tableName;
	private Connection con;
	private PreparedStatement stat;
	private ResultSet res;
	public IsDbExists(String url,String user,String pwd,String tableName){
		this.url = url;
    	this.user = user;
    	this.pwd = pwd;
    	this.tableName = tableName;
	}
	public Boolean isExists(){
		Boolean flag = false;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:mysql://"+url, user, pwd);
			stat = con.prepareStatement("select * from "+tableName);
			res = stat.executeQuery();
			while(res.next()){
				flag=true;//连接成功且表存在
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			if(res!=null){
				res.close();
			}
			if(stat!=null){
				stat.close();
			}
			if(con!=null){
				con.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return flag;
		}
	}
	/*public static void main(String args[]){
		System.out.println(new IsDbExists("localhost:3308/test", "root1", "_a147369852_b123+c", "goods").isExists());
	}*/
}
