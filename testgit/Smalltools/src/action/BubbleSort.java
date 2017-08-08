package action;

public class BubbleSort {
	public StringBuffer bubbleSort(int numList[]){
		StringBuffer orderList = new StringBuffer();
		for(int i=0;i<numList.length;i++){
			for(int j=numList.length-1;j>i;j--){
				if(numList[j]<numList[j-1]){
					int tmp = numList[j];
					numList[j] = numList[j-1];
					numList[j-1] = tmp;
				}
			}
		}
		for(int i=0;i<numList.length;i++){
			orderList.append(numList[i]).append(" ");
		}
		return orderList;
	}
}
