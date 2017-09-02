package action;

public class ShellSort {
	//Ï£¶ûÅÅÐò
	public StringBuffer shell(int numList[]){
		StringBuffer orderList = new StringBuffer();
		int list[]=numList.clone();
		int gap,tmp,j;
		gap=list.length/2;
		long start = System.currentTimeMillis();
		while(gap>0){
			for(int i=gap;i<list.length;i++){
				tmp=list[i];
				j=i-gap;
				while(j>=0 && tmp<list[j]){
					list[j+gap]=list[j];
					j=j-gap;
				}
				list[j+gap]=tmp;
			}
			gap=gap/2;
		}
		long end = System.currentTimeMillis();
		for(int i=0;i<list.length;i++){
			orderList.append(list[i]).append(" ");
		}
		String timeStr = Long.toString((end-start));
		
		//System.out.println("\n"+orderList.append(",").append("ºÄÊ±:").append(timeStr).append("ms"));
		return orderList.append(",").append("ºÄÊ±:").append(timeStr).append("ms");
	}
	public static void main(String[] args) {
		int R[]=new int[10];
		for(int i=0;i<10;i++){
			R[i]=(int) (Math.random()*1000);
		}
		new ShellSort().shell(R);
	}
}
