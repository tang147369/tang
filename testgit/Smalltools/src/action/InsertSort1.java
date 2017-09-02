package action;

public class InsertSort1 {
	//’€∞Î≤Â»Î≈≈–Ú
	public StringBuffer insertSort1(int numList[]){
		int list[]=numList.clone();
		int tmp,j,low,high,mid;
		/*for(int x:list){
			System.out.print(x+"|");
		}*/
		StringBuffer orderList = new StringBuffer();
		long start = System.currentTimeMillis();
		for(int i=1;i<list.length;i++){
			low=0;high=i-1;
			j=i-1;
			tmp=list[i];
			while(low<=high){
				mid=(low+high)/2;
				if(tmp<list[mid]){
					high=mid-1;
				}else{
					low=mid+1;
				}
			}
			for(j=i-1;j>=high+1;j--){
				list[j+1]=list[j];
			}
			list[high+1]=tmp;
		}
		long end = System.currentTimeMillis();
		for(int i=0;i<list.length;i++){
			orderList.append(list[i]).append(" ");
		}
		String timeStr = Long.toString((end-start));
		
		//System.out.println();
		/*for(int x:list){
			System.out.print(x+" ");
		}*/
		//System.out.println("\n"+orderList.append(",").append("∫ƒ ±:").append(timeStrb).append("ms"));
		return orderList.append(",").append("∫ƒ ±:").append(timeStr).append("ms");
	}
	/*public static void main(String[] args) {
		int num[]={1,4,8,2,4,5,2,4,8,5,8,5,4,5,5,2,5,2,5,2,5,8,6,3,8,5,9,7,4,55,5,8,854,5,56,55,55};
		new InsertSort1().insertSort1(num);
	}*/
}
