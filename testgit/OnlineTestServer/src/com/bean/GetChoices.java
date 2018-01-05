package com.bean;

public class GetChoices {
	//选择题ID，题干，a,b,c,d选项和答案
	public int choId;
	public String choContent,a,b,c,d,choAnswer;
	public GetChoices(int choId,String choContent,String a,String b,String c,String d,String choAnswer){
		this.choId = choId;
		this.choContent = choContent;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.choAnswer = choAnswer;
	}
	public int getChoId() {
		return choId;
	}
	public void setChoId(int choId) {
		this.choId = choId;
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
	
}
