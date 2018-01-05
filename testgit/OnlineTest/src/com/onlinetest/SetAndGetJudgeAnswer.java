package com.onlinetest;

public class SetAndGetJudgeAnswer {
	public int num,JudgeTId,JudgeFId;
	public String answer,choose;
	public SetAndGetJudgeAnswer(int num,int JudgeTId,int JudgeFId,String answer){
		this.num = num;
		this.JudgeTId = JudgeTId;
		this.JudgeFId = JudgeFId;
		this.answer = answer;
	}
	public String getChoice(int id){
		if(id == JudgeTId){
			return "¶Ô";
		}else if(id == JudgeFId){
			return "´í";
		}else{
			return null;
		}
	}
	public void setChoose(String choose){
		this.choose = choose;
	}
}
