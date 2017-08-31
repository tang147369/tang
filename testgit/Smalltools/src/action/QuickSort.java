package action;

public class QuickSort {
	//¿ìËÙÅÅĞò
	long countTime;
	public int[] quickSort(int list[],int s,int t){
		int tmp,i=s,j=t;
		StringBuffer orderList = new StringBuffer();
		if(s<t){
			tmp=list[s];
			while(i!=j){
				while(j>i && list[j]>=tmp){
					j--;
				}
				list[i]=list[j];
				while(i<j && list[i]<=tmp){
					i++;
				}
				list[j]=list[i];
			}
			list[i]=tmp;
			quickSort(list,s,i-1);
			quickSort(list,i+1,t);
		}
		return list;
	}
	/*public static void main(String[] args) {
		int num[]=new int[400];
		for(int i=0;i<400;i++){
			num[i]=(int) (Math.random()*1000);
		}
		long start = System.currentTimeMillis();
		QuickSort q = new QuickSort();
		int a[] = q.quickSort(num, 0, num.length-1);
		long end = System.currentTimeMillis();
		System.out.println((end-start)+"ºÁÃë");
		for(int x:a){
			System.out.print(x+" ");
		}
		
	}*/
}
