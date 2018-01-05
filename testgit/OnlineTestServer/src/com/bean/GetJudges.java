package com.bean;

public class GetJudges {
	//ÅĞ¶ÏÌâID,ÄÚÈİ£¬´ğ°¸
	public int judgeId;
	public String judContent,judAnswer;
	public GetJudges(int judgeId,String judContent,String judAnswer){
		this.judgeId = judgeId;
		this.judContent = judContent;
		this.judAnswer = judAnswer;
	}
	public int getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(int judgeId) {
		this.judgeId = judgeId;
	}
	public String getJudContent() {
		return judContent;
	}
	public void setJudContent(String judContent) {
		this.judContent = judContent;
	}
	public String getJudAnswer() {
		return judAnswer;
	}
	public void setJudAnswer(String judAnswer) {
		this.judAnswer = judAnswer;
	}
}
