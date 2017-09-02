package action;

public class MergeSort {
	
	public void Merge(int R[],int low,int mid,int high){
	  int i=low,j=mid+1,k=0;	  //k是R1的下标，i,j分别为第1，2段的下标
	  int R1[] = new int[high-low+1];
	  while(i<=mid && j<=high)	  //在第1段和第2段均未扫描完时循环
	    if(R[i]<=R[j])	  //将第1段中的元素放入R1中
	    {
	      R1[k]=R[i];
	      i++;k++;
	    }
	    else  	//将第2段中的元素放入R1中
	    {
	      R1[k]=R[j];
	      j++;k++;
	    }
	  while(i<=mid)  	//将第1段余下部分复制到R1中
	  {
	  	R1[k]=R[i];
	  	i++;k++;
	  }
	  while(j<=high)  	//将第2段余下部分复制到R1中
	  {
	    R1[k]=R[j];
	  	j++;k++;
	  }
	  for(k=0,i=low;i<=high;k++,i++)  	//将R1复制回R中
	    R[i]=R1[k];
	}
	void MergePass(int R[],int length,int n)	  //对整个表进行一趟归并
	{
	  int i;
	  for(i=0;i+2*length-1<n;i=i+2*length)	  //归并length长的两相邻子表
	    Merge(R,i,i+length-1,i+2*length-1);
	  if(i+length-1<n)	  //余下两个子表，后者长度小于length
	    Merge(R,i,i+length-1,n-1);	  //归并这两个子表
	}
	public StringBuffer mergeSort(int numList[],int n)
	{
		int R[]=numList.clone();
	  int length;
	  StringBuffer orderList = new StringBuffer();
	  long start = System.currentTimeMillis();
	  for(length=1;length<n;length=2*length)
	    MergePass(R,length,n);
	  System.out.println();
	  long end = System.currentTimeMillis();
	  for(int i=0;i<R.length;i++){
		  orderList.append(R[i]).append(" ");
	  }
	  String timeStr = Long.toString((end-start));
		
	  //System.out.println("\n"+orderList.append(",").append("耗时:").append(timeStr).append("ms"));
	  return orderList.append(",").append("耗时:").append(timeStr).append("ms");
	}
	/*public static void main(String[] args) {
		int R[]={45,8,2,1,5,12,45,35,20,10,14,48,5,9,41,40,1,5,2};
		new MergeSort().mergeSort(R,R.length);
	}*/
}
