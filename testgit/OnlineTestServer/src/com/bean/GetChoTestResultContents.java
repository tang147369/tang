package com.bean;

public class GetChoTestResultContents {
	public String choContent,a,b,c,d,choAnswer,choChoose;
	public int choiceId;
	public GetChoTestResultContents(String choContent,String a,String b,String c,String d,String choAnswer,int choiceId,String choChoose){
		this.choContent = choContent;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.choAnswer = choAnswer;
		this.choiceId = choiceId;
		this.choChoose = choChoose;
	}
	public String getChoContent() {
		return choContent;
	}
	public void setChoContent(String choContent) {
		this.choContent = choContent;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getChoAnswer() {
		return choAnswer;
	}
	public void setChoAnswer(String choAnswer) {
		this.choAnswer = choAnswer;
	}
	public String getChoChoose() {
		return choChoose;
	}
	public void setChoChoose(String choChoose) {
		this.choChoose = choChoose;
	}
	public int getChoiceId() {
		return choiceId;
	}
	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}
	
}
