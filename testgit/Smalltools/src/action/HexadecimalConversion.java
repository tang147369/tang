package action;

import java.math.BigInteger;

public class HexadecimalConversion {
	public StringBuffer conversion(String num,int type){
		//符合要求的进制其它表现形式
		if(type==16){
			if(num.startsWith("0x") || num.startsWith("0X")){
				num = num.substring(2, num.length());
			}
			if(num.endsWith("H") || num.endsWith("h")){
				num = num.substring(0, num.length()-1); 
			}
		}
		if(type==8){
			if(num.startsWith("0")){
				num = num.substring(1, num.length());
			}
			if(num.endsWith("O") || num.endsWith("o")){
				num = num.substring(0, num.length()-1); 
			}
		}
		if(type==10){
			if(num.endsWith("D") || num.endsWith("d")){
				num = num.substring(0, num.length()-1); 
			}
		}
		if(type==2){
			if(num.endsWith("B") || num.endsWith("b")){
				num = num.substring(0, num.length()-1); 
			}
		}
		System.out.println(num);
		StringBuffer str=new StringBuffer();
		BigInteger b = new BigInteger(num,type);//type指定传来的数的进制
		String two=b.toString(2);
		String eight=b.toString(8);
		String ten=b.toString(10);
		String sixteen=b.toString(16);
		//将结果拼接后返回
		str.append(two).append(",").append(eight).append(",").append(ten).append(",").append(sixteen);
		
		return str;
	}
	public static void main(String[] args) {
		System.out.println(new HexadecimalConversion().conversion("0x8H",16));
	}
}
