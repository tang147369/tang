package excel;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class DbToExcel {
	static String url,user,pwd,tableName;//�û�����ĵ�ַ���û���,�������
	public DbToExcel(String url,String user,String pwd,String tableName){
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		this.tableName = tableName;
		try {
     	   WritableWorkbook wwb = null;
          
            // ������д���Excel������
            String fileName = "C://Users/Administrator/Desktop/root/file/"+tableName+".xls";
            File file=new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //��fileNameΪ�ļ���������һ��Workbook
            wwb = Workbook.createWorkbook(file);

            // ����������
            WritableSheet ws = wwb.createSheet("Shee 1", 0);
            
            List<String> listColName = new DbOperation(url,user,pwd,tableName).selectColName();//��ѯ���ݿ��ȡ�������
            Label labelName[] = new Label[listColName.size()];//�������������Ϣ
            //��ѯ���ݿ������е�����
            List<DbEntity> list= new DbService(url,user,pwd,tableName).getAllByDb(listColName);
           
            for(int i=0;i<listColName.size();i++){
                //Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
                labelName[i]= new Label(i, 0, listColName.get(i));
              //���������ÿ�е�����
                ws.addCell(labelName[i]);
            }
            /*//Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
            Label labelId= new Label(0, 0, "���(id)");//��ʾ��
            Label labelName= new Label(1, 0, "����(name)");
            Label labelSex= new Label(2, 0, "�Ա�(sex)");
            Label labelNum= new Label(3, 0, "нˮ(num)");*/
           
            //���������ÿ�е����ݣ���һ����(j)�������ڶ�����(i+1)����
            for (int i = 0; i < list.size(); i++) {
         	    //��������ÿ����Ϣ
                Label label[] = new Label[listColName.size()];
         	   for(int j=0;j<listColName.size();j++){
         		   label[j]= new Label(j, i+1, list.get(i).get(j)+"");
         		   ws.addCell(label[j]);//��ÿ��������ӵ���������
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
          
           //д���ĵ�
            wwb.write();
           // �ر�Excel����������
            wwb.close();
          
     } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     } 
	}
    /*public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
    	System.out.println("���������ӵ�ַ�Ͷ˿�");
    	url = scan.next();
    	System.out.println("���������ݿ���");
    	url=url+"/"+scan.next();
    	System.out.println("�������û���");
    	user = scan.next();
    	System.out.println("����������");
    	pwd = scan.next();
    	System.out.println("���������");
    	tableName = scan.next();
    	new DbToExcel("10.0.139.247:3308/test", "root1", "_a147369852_b123+c", "goods");
        
    }*/
}