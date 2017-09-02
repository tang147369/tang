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
     * ��ѯstu�������е�����
     * @return 
     */
	static String url,user,pwd,tableName;//�û�����ĵ�ַ���û���������
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
            	Object col[] = new Object[listColName.size()];//����ÿ�е���Ϣ
            	for(int i=0;i<listColName.size();i++){
            		col[i] = rs.getObject(listColName.get(i));
            	}
                /*int id=rs.getInt("id");
                String name=rs.getString("name");
                String sex=rs.getString("sex");
                int num=rs.getInt("num");
                list.add(new StuEntity(id, name, sex, num));*/
            	list.add(new DbEntity(col));//���ÿ������
            }
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     * ��ѯָ��Ŀ¼�е��ӱ�������е�����
     * @param file �ļ�����·��
     * @return
     */
    public static List<DbEntity> getAllByExcel(String file){
        List<DbEntity> list=new ArrayList<DbEntity>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet("Test Shee 1");//����rwb.getSheet(0)
            int clos=rs.getColumns();//�õ����е���
            int rows=rs.getRows();//�õ����е���
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //��һ�����������ڶ���������
                    String id=rs.getCell(j++, i).getContents();//Ĭ������߱��Ҳ��һ�� ���������j++
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
     * ͨ��Id�ж��Ƿ����
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