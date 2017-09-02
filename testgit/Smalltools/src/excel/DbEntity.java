package excel;

public class DbEntity {
    private Object col[];
    public DbEntity(Object col1[]) {
    	col = new Object[col1.length];
        for(int i=0;i<col.length;i++){
        	col[i]=col1[i];
        }
    }
    public Object get(int j) {
    	System.out.println("col:"+col[j]);
        return col[j];
    }
}