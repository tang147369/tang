package excel;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class DbToExcel {
	static String url,user,pwd,tableName;//用户输入的地址，用户名,密码表名
	public DbToExcel(String url,String user,String pwd,String tableName){
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		this.tableName = tableName;
		try {
     	   WritableWorkbook wwb = null;
          
            // 创建可写入的Excel工作簿
            String fileName = "C://Users/Administrator/Desktop/root/file/"+tableName+".xls";
            File file=new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //以fileName为文件名来创建一个Workbook
            wwb = Workbook.createWorkbook(file);

            // 创建工作表
            WritableSheet ws = wwb.createSheet("Shee 1", 0);
            
            List<String> listColName = new DbOperation(url,user,pwd,tableName).selectColName();//查询数据库获取表的列名
            Label labelName[] = new Label[listColName.size()];//保存插入列名信息
            //查询数据库中所有的数据
            List<DbEntity> list= new DbService(url,user,pwd,tableName).getAllByDb(listColName);
           
            for(int i=0;i<listColName.size();i++){
                //要插入到的Excel表格的行号，默认从0开始
                labelName[i]= new Label(i, 0, listColName.get(i));
              //工作表添加每行的列名
                ws.addCell(labelName[i]);
            }
            /*//要插入到的Excel表格的行号，默认从0开始
            Label labelId= new Label(0, 0, "编号(id)");//表示第
            Label labelName= new Label(1, 0, "姓名(name)");
            Label labelSex= new Label(2, 0, "性别(sex)");
            Label labelNum= new Label(3, 0, "薪水(num)");*/
           
            //工作表添加每行的数据，第一个是(j)列数，第二个是(i+1)行数
            for (int i = 0; i < list.size(); i++) {
         	    //保存插入的每行信息
                Label label[] = new Label[listColName.size()];
         	   for(int j=0;j<listColName.size();j++){
         		   label[j]= new Label(j, i+1, list.get(i).get(j)+"");
         		   ws.addCell(label[j]);//将每个数据添加到工作表中
         	   }
                /*Label labelId_i= new Label(0, i+1, list.get(i).getId()+"");
                Label labelName_i= new Label(1, i+1, list.get(i).getName());
                Label labelSex_i= new Label(2, i+1, list.get(i).getSex());
                Label labelNum_i= new Label(3, i+1, list.get(i).getNum()+"");
                ws.addCell(labelId_i);
                ws.addCell(labelName_i);
                ws.addCell(labelSex_i);
                ws.addCell(labelNum_i);*/
            }
          
           //写进文档
            wwb.write();
           // 关闭Excel工作簿对象
            wwb.close();
          
     } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     } 
	}
    /*public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	System.out.println("请输入连接地址和端口");
    	url = scan.next();
    	System.out.println("请输入数据库名");
    	url=url+"/"+scan.next();
    	System.out.println("请输入用户名");
    	user = scan.next();
    	System.out.println("请输入密码");
    	pwd = scan.next();
    	System.out.println("请输入表名");
    	tableName = scan.next();
    	new DbToExcel("10.0.139.247:3308/test", "root1", "_a147369852_b123+c", "goods");
        
    }*/
}