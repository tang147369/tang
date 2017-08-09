package action;

public class BubbleSort {
	public StringBuffer bubbleSort(int numList[]){
		StringBuffer orderList = new StringBuffer();
		long start = System.currentTimeMillis();
		for(int i=0;i<numList.length;i++){
			for(int j=numList.length-1;j>i;j--){
				if(numList[j]<numList[j-1]){
					int tmp = numList[j];
					numList[j] = numList[j-1];
					numList[j-1] = tmp;
				}
			}
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
		for(int i=0;i<numList.length;i++){
			orderList.append(numList[i]).append(" ");
		}
		String timeStr = Long.toString((end-start));
		StringBuffer timeStrb = new StringBuffer(timeStr);
		return orderList.append(",").append("ºÄÊ±:").append(timeStrb).append("ms");
	}
	/*public static void main(String[] args) {
		int num[]={1,4,8,2,4,5,2,4,8,5,8,5,4,5,5,2,5,2,5,2,5,8,6,3,8,5,9,7,4,55,5,8,854,5,56,55,55};
		new BubbleSort().bubbleSort(num);
	}*/
}
