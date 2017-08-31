package action;

public class HeapSort {
	public void sift(int R[],int low,int high){
		int i=low,j=2*i;
		int tmp=R[i];
		while(j<=high){
			if(j<high && R[j]<R[j+1]){
				j++;
			}
			if(tmp<R[j]){
				R[i]=R[j];
				i=j;
				j=2*i;
			}
			else break;
		}
		R[i]=tmp;
	}
	public StringBuffer heapSort(int numList[]){
		int list[]=numList.clone();
		int list1[]=new int[list.length+1];
		StringBuffer orderList = new StringBuffer();
		//判断位数
		if(list.length<2000){
			
			list1[0]=0;
			//移位处理
			for(int i=0;i<list.length;i++){
				list1[i+1]=list[i];
			}
			//开始排序
			long start1 = System.currentTimeMillis();
			int tmp1;
			int n=list1.length-1;
			for(int i=n/2;i>=1;i--)
				sift(list1,i,n);
			for(int i=n;i>=2;i--){
				tmp1=list1[1];
				list1[1]=list1[i];
				list1[i]=tmp1;
				sift(list1,1,i-1);
			}
			//排序结束
			long end1 = System.currentTimeMillis();
			for(int x:list1){
				System.out.print(x+" ");
			}
			for(int i=1;i<list1.length;i++){
				orderList.append(list1[i]).append(" ");
			}
			String timeStr1 = Long.toString((end1-start1));
			System.out.println("\n"+orderList.append(",").append("耗时:").append(timeStr1).append("ms"));
			return orderList.append(",").append("耗时:").append(timeStr1).append("ms");
		}else{
			int tmp;
			int n=list.length-1;
			long start = System.currentTimeMillis();
			for(int i=n/2;i>=1;i--)
				sift(list,i,n);
			for(int i=n;i>=2;i--){
				tmp=list[1];
				list[1]=list[i];
				list[i]=tmp;
				sift(list,1,i-1);
			}
			long end = System.currentTimeMillis();
			for(int i=1;i<list.length;i++){
				orderList.append(list[i]).append(" ");
			}
			String timeStr = Long.toString((end-start));
			//System.out.println("\n"+orderList.append(",").append("耗时:").append(timeStr).append("ms"));
			return orderList.append(",").append("耗时:").append(timeStr).append("ms");
		}
	}
	/*public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int R[]=new int[10];
		for(int i=0;i<10;i++){
			R[i]=(int) (Math.random()*1000);
		}
		new HeapSort().heapSort(R);
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}*/
}
