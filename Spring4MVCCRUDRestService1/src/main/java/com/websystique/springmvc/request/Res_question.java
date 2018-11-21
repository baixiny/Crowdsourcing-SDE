package com.websystique.springmvc.request;

import net.sf.json.JSONArray;

public class Res_question {
	private String questionnaireDataNum;
	private String questionnaireTaskNum;
	private String questionnaireDataDesc;
	private String questionnaireDataAddr;
	private String questionnaireDataShow;
	private String publishDate;
	private String completeDate;
	private String answerslength;
	private JSONArray question;
	public String getQuestionnaireDataNum() {
		return questionnaireDataNum;
	}
	public void setQuestionnaireDataNum(String questionnaireDataNum) {
		this.questionnaireDataNum = questionnaireDataNum;
	}
	public String getQuestionnaireTaskNum() {
		return questionnaireTaskNum;
	}
	public void setQuestionnaireTaskNum(String questionnaireTaskNum) {
		this.questionnaireTaskNum = questionnaireTaskNum;
	}
	public String getQuestionnaireDataDesc() {
		return questionnaireDataDesc;
	}
	public void setQuestionnaireDataDesc(String questionnaireDataDesc) {
		this.questionnaireDataDesc = questionnaireDataDesc;
	}
	public String getQuestionnaireDataAddr() {
		return questionnaireDataAddr;
	}
	public void setQuestionnaireDataAddr(String questionnaireDataAddr) {
		this.questionnaireDataAddr = questionnaireDataAddr;
	}
	public String getQuestionnaireDataShow() {
		return questionnaireDataShow;
	}
	public void setQuestionnaireDataShow(String questionnaireDataShow) {
		this.questionnaireDataShow = questionnaireDataShow;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}
	public String getAnswerslength() {
		return answerslength;
	}
	public void setAnswerslength(String answerslength) {
		this.answerslength = answerslength;
	}
	public JSONArray getQuestion() {
		return question;
	}
	public void setQuestion(JSONArray question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "Res_question [questionnaireDataNum=" + questionnaireDataNum + ", questionnaireTaskNum="
				+ questionnaireTaskNum + ", questionnaireDataDesc=" + questionnaireDataDesc + ", questionnaireDataAddr="
				+ questionnaireDataAddr + ", questionnaireDataShow=" + questionnaireDataShow + ", publishDate="
				+ publishDate + ", completeDate=" + completeDate + ", answerslength=" + answerslength + ", question="
				+ question + "]";
	}
	
}
