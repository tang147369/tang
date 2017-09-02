package action;

public class InsertSort {
	//直接插入排序
	public StringBuffer insertSort(int numList[]){
		int tmp,j;
		int list[]=numList.clone();
		StringBuffer orderList = new StringBuffer();
		long start = System.currentTimeMillis();
		for(int i=1;i<list.length;i++){
			tmp=list[i];
			j=i-1;
			while(j>=0 && tmp<list[j]){
				list[j+1]=list[j];
				j--;
			}
			list[j+1]=tmp;
		}
		long end = System.currentTimeMillis();
		//System.out.println("\n"+(end-start)+"毫秒");
		for(int i=0;i<list.length;i++){
			orderList.append(list[i]).append(" ");
		}
		String timeStr = Long.toString((end-start));
		return orderList.append(",").append("耗时:").append(timeStr).append("ms");
	}
	/*public static void main(String[] args) {
		int num[]={1,4,8,2,4,5,2,4,8,5,8,5,4,5,5,2,5,2,5,2,5,8,6,3,8,5,9,7,4,55,5,8,854,5,56,55,55};
		new InsertSort().insertSort(num);
	}*/
}
