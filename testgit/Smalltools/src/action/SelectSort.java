package action;

public class SelectSort {
	public StringBuffer select(int numList[]){
		//直接选择排序
		int k;
		int list[] = numList.clone();
		//将字符数组转换为字符串并返回
		StringBuffer orderList = new StringBuffer();
		long start = System.currentTimeMillis();
		for(int i=0;i<list.length-1;i++){
			k=i;
			for(int j=i+1;j<list.length;j++){
				if(list[j]<list[k])
					k=j;
			}
			if(k!=i){
				int temp = list[k];
				list[k] = list[i];
				list[i] = temp;
 			}
		}
		for(int x:list){
			System.out.print(x+" ");
		}
		long end = System.currentTimeMillis();
		for(int i=0;i<list.length;i++){
			orderList.append(list[i]).append(" ");
		}
		String timeStr = Long.toString((end-start));
		//System.out.println("\n"+orderList.append(",").append("耗时:").append(timeStr).append("ms"));
		return orderList.append(",").append("耗时:").append(timeStr).append("ms");
	}
	/*public static void main(String[] args) {
		int R[]={45,8,2,1,5,12,45,35,20,10,14,48,5,9,41,40,1,5,2};
		new SelectSort().select(R);
	}*/
}
