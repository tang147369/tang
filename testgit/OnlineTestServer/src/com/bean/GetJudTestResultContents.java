package com.bean;

public class GetJudTestResultContents {
	public String judContent,judAnswer,judChoose;
	public int judgeId;
	public GetJudTestResultContents(String judContent,String judAnswer,int judgeId,String judChoose){
		this.judContent = judContent;
		this.judAnswer = judAnswer;
		this.judgeId = judgeId;
		this.judChoose = judChoose;
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
	public String getJudChoose() {
		return judChoose;
	}
	public void setJudChoose(String judChoose) {
		this.judChoose = judChoose;
	}
	public int getJudgeId() {
		return judgeId;
	}
	public void setJudgeId(int judgeId) {
		this.judgeId = judgeId;
	}
	
}
