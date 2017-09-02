package excel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbOperation {
    /*String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://127.0.0.1;DatabaseName=javenforexcel";*/
    
    String driver = "com.mysql.jdbc.Driver";
    /*String url = "jdbc:mysql://127.0.0.1:3308/test";*/
    String url,user,pwd,tableName;
    
    Connection con = null;
    ResultSet res = null;
    ResultSet resName = null;
    //�������ӵ�ַ���û��������룬����
    public DbOperation(String url,String user,String pwd,String tableName){
    	this.url = url;
    	this.user = user;
    	this.pwd = pwd;
    	this.tableName = tableName;
    }
    //���ݿ�����
    public void DataBase() {
            try {
                Class.forName(driver);
                con = DriverManager.getConnection("jdbc:mysql://"+url, user, pwd);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                  System.err.println("װ�� JDBC/ODBC ��������ʧ�ܡ�" );  
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.err.println("�޷��������ݿ�" ); 
                e.printStackTrace();
            }
    }

    // ��ѯ
    public ResultSet  Search(String sql, String str[]) {
        DataBase();
        try {
            PreparedStatement pst =con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            res = pst.executeQuery();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    // ��ɾ�޸�
    public int AddU(String sql, String str[]) {
        int a = 0;
        DataBase();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            a = pst.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }
    public List<String> selectColName(){
    	//��������
    	List<String> colName = new ArrayList<String>();
    	DataBase();
    	try {
    		String dbName[] = url.split("/");//ͨ��������ݿ�����
    		//Ԥ������䣨�������TABLE_SCHEMA='���ݿ���'����Ȼ������������ݿ���ͬ���ֵ��
			PreparedStatement pst = con.prepareStatement("select COLUMN_NAME from information_schema.columns where table_name=? and TABLE_SCHEMA=?");
			//����Ԥ�������
			pst.setString(1, tableName);
			pst.setString(2, dbName[1]);
			resName = pst.executeQuery();//ִ��sql���
			while(resName.next()){
				System.out.println("������"+resName.getString(1));
				colName.add(resName.getString(1));//��������
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return colName;
    }
    /*public static void main(String[] args) {
		new DbOperation().selectColName();
	}*/
}
