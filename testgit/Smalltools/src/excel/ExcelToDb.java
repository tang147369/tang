package excel;

import java.util.List;

public class ExcelToDb {
	static String url,user,pwd,tableName;//用户输入的地址，用户名,密码表名
    public static void main(String[] args) {
        //得到表格中所有的数据
        List<DbEntity> listExcel=DbService.getAllByExcel("C://Users/Administrator/Desktop/book.xls");
        /*//得到数据库表中所有的数据
        List<StuEntity> listDb=StuService.getAllByDb();*/
        
        DbOperation db=new DbOperation(url,user,pwd,tableName);
        
        /*for (StuEntity stuEntity : listExcel) {
            int id=stuEntity.getId();
            if (!StuService.isExist(id)) {
                //不存在就添加
                String sql="insert into stu (name,sex,num) values(?,?,?)";
                String[] str=new String[]{stuEntity.getName(),stuEntity.getSex(),stuEntity.getNum()+""};
                db.AddU(sql, str);
            }else {
                //存在就更新
                String sql="update stu set name=?,sex=?,num=? where id=?";
                String[] str=new String[]{stuEntity.getName(),stuEntity.getSex(),stuEntity.getNum()+"",id+""};
                db.AddU(sql, str);
            }
        }*/
    }
}
