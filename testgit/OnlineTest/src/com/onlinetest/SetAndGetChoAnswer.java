package com.onlinetest;

public class SetAndGetChoAnswer {
	public int num,AId,BId,CId,DId;//题号和选项Id
	public String answer,choose;//标准答案与作答
	public  SetAndGetChoAnswer(int num,int AId,int BId,int CId,int DId,String answer){
		this.num = num;
		this.AId = AId;
		this.BId = BId;
		this.CId = CId;
		this.DId = DId;
		this.answer = answer;
	}
	//获取选择题
	public String getChoice(int id){
		if(id == AId){
			return "A";
		}else if(id == BId){
			return "B";
		}else if(id == CId){
			return "C";
		}else if(id == DId){
			return "D";
		}else return null;
	}
	public void setChoose(String choose){
		this.choose = choose;
	}

}
