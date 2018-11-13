package com.websystique.springmvc.CStable;

import java.sql.Timestamp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Questionnaire {
	private Integer qnaireid;  
	private Integer isshow;   
    private String description; 
    private double longitude;
    private double latitude;
    private String location; 
    private Integer answercount;
    private String status; 
    private Timestamp publictime;
    private Timestamp deadline;
    private Object content;
	private Integer linkedtaskid;
	
	public Integer getLinkedtaskid() {
		return linkedtaskid;
	}
	public void setLinkedtaskid(Integer linkedtaskid) {
		this.linkedtaskid = linkedtaskid;
	}
	public Integer getQnaireid() {
		return qnaireid;
	}
	public void setQnaireid(Integer qnaireid) {
		this.qnaireid = qnaireid;
	}
	public Integer getIsShow() {
		return isshow;
	}
	public void setIsShow(Integer isshow) {
		this.isshow = isshow;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getAnswercount() {
		return answercount;
	}
	public void setAnswercount(Integer answercount) {
		this.answercount = answercount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getPublictime() {
		return publictime;
	}
	public void setPublictime(Timestamp publictime) {
		this.publictime = publictime;
	}
	public Timestamp getDeadline() {
		return deadline;
	}
	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}
	public JSONArray getContent() {
		JSONArray content=JSONArray.fromObject(this.content);
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	} 
	@Override
	public String toString() {
		return "{\"qnaireid\":\"" + qnaireid + "\", \"isShow\":\"" + isshow
				+ "\", \"description\":\"" + description + "\", \"longitude\":\"" + longitude
				+ "\", \"latitude\":\"" + latitude + "\", \"location\":\"" + location
				+ "\", \"answercount\":\"" + answercount + "\", \"status\":\"" + status
				+ "\",\" publictime\":\"" + publictime + "\", \"deadline\":\"" + deadline 
				+ "\", \"content\":\"" + content + "\", \"linkedtaskid\":\""
				+ linkedtaskid + "\"}";
	}
	
}
