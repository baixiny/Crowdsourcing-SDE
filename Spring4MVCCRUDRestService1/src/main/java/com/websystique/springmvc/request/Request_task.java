package com.websystique.springmvc.request;

import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Request_task {
	private String taskName;
	private String taskDesc;
	private String taskAddr;
	private String completeDate;
	private String publishDate;
	private String taskPrice;
	private String taskTime;
	private String answersNum;
	private JSONArray questions;
	private int answersLength;
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public String getTaskTime(){
		return taskTime;
	}
	public void setTaskTime(String taskTime){
		this.taskTime = taskTime;
	}
	public void setAnswersNum(String answersNum){
		this.answersNum = answersNum;
	}
	public String getAnswersNum(){
		return answersNum;
	}


	public int getAnswersLength() {
		return answersLength;
	}
	public void setAnswersLength(int answersLength) {
		this.answersLength = answersLength;
	}
	
	public String getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getTaskAddr() {
		return taskAddr;
	}
	public void setTaskaddr(String taskAddr) {
		this.taskAddr = taskAddr;
	}
	public String getTaskPrice() {
		return taskPrice;
	}
	public void setTaskPrice(String taskPrice) {
		this.taskPrice = taskPrice;
	}
	public JSONArray getQuestions() {
		return questions;
	}
	public void setQuestions(JSONArray questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "Request_task [taskName=" + taskName + ", taskDesc=" + taskDesc + ", taskAddr=" + taskAddr
				+ ", completeDate=" + completeDate + ", publishDate=" + publishDate + ", taskPrice=" + taskPrice
				+ ", taskTime=" + taskTime + ", answersNum =" + answersNum +", questions=" + questions + "]";
	}
	
}
