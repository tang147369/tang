package action;

public class MergeSort {
	
	public void Merge(int R[],int low,int mid,int high){
	  int i=low,j=mid+1,k=0;	  //k��R1���±꣬i,j�ֱ�Ϊ��1��2�ε��±�
	  int R1[] = new int[high-low+1];
	  while(i<=mid && j<=high)	  //�ڵ�1�κ͵�2�ξ�δɨ����ʱѭ��
	    if(R[i]<=R[j])	  //����1���е�Ԫ�ط���R1��
	    {
	      R1[k]=R[i];
	      i++;k++;
	    }
	    else  	//����2���е�Ԫ�ط���R1��
	    {
	      R1[k]=R[j];
	      j++;k++;
	    }
	  while(i<=mid)  	//����1�����²��ָ��Ƶ�R1��
	  {
	  	R1[k]=R[i];
	  	i++;k++;
	  }
	  while(j<=high)  	//����2�����²��ָ��Ƶ�R1��
	  {
	    R1[k]=R[j];
	  	j++;k++;
	  }
	  for(k=0,i=low;i<=high;k++,i++)  	//��R1���ƻ�R��
	    R[i]=R1[k];
	}
	void MergePass(int R[],int length,int n)	  //�����������һ�˹鲢
	{
	  int i;
	  for(i=0;i+2*length-1<n;i=i+2*length)	  //�鲢length�����������ӱ�
	    Merge(R,i,i+length-1,i+2*length-1);
	  if(i+length-1<n)	  //���������ӱ����߳���С��length
	    Merge(R,i,i+length-1,n-1);	  //�鲢�������ӱ�
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
		
	  //System.out.println("\n"+orderList.append(",").append("��ʱ:").append(timeStr).append("ms"));
	  return orderList.append(",").append("��ʱ:").append(timeStr).append("ms");
	}
	/*public static void main(String[] args) {
		int R[]={45,8,2,1,5,12,45,35,20,10,14,48,5,9,41,40,1,5,2};
		new MergeSort().mergeSort(R,R.length);
	}*/
}
