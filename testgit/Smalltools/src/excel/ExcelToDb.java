package excel;

import java.util.List;

public class ExcelToDb {
	static String url,user,pwd,tableName;//�û�����ĵ�ַ���û���,�������
    public static void main(String[] args) {
        //�õ���������е�����
        List<DbEntity> listExcel=DbService.getAllByExcel("C://Users/Administrator/Desktop/book.xls");
        /*//�õ����ݿ�������е�����
        List<StuEntity> listDb=StuService.getAllByDb();*/
        
        DbOperation db=new DbOperation(url,user,pwd,tableName);
        
        /*for (StuEntity stuEntity : listExcel) {
            int id=stuEntity.getId();
            if (!StuService.isExist(id)) {
                //�����ھ����
                String sql="insert into stu (name,sex,num) values(?,?,?)";
                String[] str=new String[]{stuEntity.getName(),stuEntity.getSex(),stuEntity.getNum()+""};
                db.AddU(sql, str);
            }else {
                //���ھ͸���
                String sql="update stu set name=?,sex=?,num=? where id=?";
                String[] str=new String[]{stuEntity.getName(),stuEntity.getSex(),stuEntity.getNum()+"",id+""};
                db.AddU(sql, str);
            }
        }*/
    }
}
