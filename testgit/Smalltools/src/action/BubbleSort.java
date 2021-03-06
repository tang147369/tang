package action;

public class BubbleSort {
	
	public StringBuffer bubbleSort(int numList[]){
		int list[] = numList.clone();
		//将字符数组转换为字符串并返回
		StringBuffer orderList = new StringBuffer();
		long start = System.currentTimeMillis();
		for(int i=0;i<list.length-1;i++){
			for(int j=list.length-1;j>i;j--){
				if(list[j]<list[j-1]){
					int tmp = list[j];
					list[j] = list[j-1];
					list[j-1] = tmp;
				}
			}
		}
		long end = System.currentTimeMillis();
		for(int i=0;i<list.length;i++){
			orderList.append(list[i]).append(" ");
		}
		String timeStr = Long.toString((end-start));
		
		return orderList.append(",").append("耗时:").append(timeStr).append("ms");
	}
	/*public static void main(String[] args) {
		int num[]={1,4,8,20,21,2582,24,2,4,5,2,4,8,5,8,5,4,5,5,2,5,2,5,2,5,8,6,3,8,5,9,7,4,55,5,8,854,5,56,55,55};
		new BubbleSort().bubbleSort(num);
	}*/
}
