package excel;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

public class DbService {
    /**
     * 查询stu表中所有的数据
     * @return 
     */
	static String url,user,pwd,tableName;//用户输入的地址，用户名和密码
	public DbService(String url,String user,String pwd,String tableName){
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		this.tableName = tableName;
	}
    public static List<DbEntity> getAllByDb(List<String> listColName){
        List<DbEntity> list=new ArrayList<DbEntity>();
        try {
            DbOperation db=new DbOperation(url,user,pwd,tableName);
            String sql="select * from "+tableName+"";
            ResultSet rs= db.Search(sql, null);
            while (rs.next()) {
            	Object col[] = new Object[listColName.size()];//保存每行的信息
            	for(int i=0;i<listColName.size();i++){
            		col[i] = rs.getObject(listColName.get(i));
            	}
                /*int id=rs.getInt("id");
                String name=rs.getString("name");
                String sex=rs.getString("sex");
                int num=rs.getInt("num");
                list.add(new StuEntity(id, name, sex, num));*/
            	list.add(new DbEntity(col));//添加每行数据
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * 查询指定目录中电子表格中所有的数据
     * @param file 文件完整路径
     * @return
     */
    public static List<DbEntity> getAllByExcel(String file){
        List<DbEntity> list=new ArrayList<DbEntity>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet("Test Shee 1");//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String name=rs.getCell(j++, i).getContents();
                    String sex=rs.getCell(j++, i).getContents();
                    String num=rs.getCell(j++, i).getContents();
                    
                    System.out.println("id:"+id+" name:"+name+" sex:"+sex+" num:"+num);
                    //list.add(new StuEntity(Integer.parseInt(id), name, sex, Integer.parseInt(num)));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        return list;
        
    }
    
    /**
     * 通过Id判断是否存在
     * @param id
     * @return
     */
    public static boolean isExist(int id){
        try {
            DbOperation db=new DbOperation(url,user,pwd,tableName);
            ResultSet rs=db.Search("select * from "+tableName+" where id=?", new String[]{id+""});
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        /*List<StuEntity> all=getAllByDb();
        for (StuEntity stuEntity : all) {
            System.out.println(stuEntity.toString());
        }*/
        
        System.out.println(isExist(1));
        
    }
    
}