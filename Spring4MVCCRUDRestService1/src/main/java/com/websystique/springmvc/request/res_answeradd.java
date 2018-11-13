package com.websystique.springmvc.request;

import java.util.List;

public class res_answeradd {
	private String username;
	private int tid;
	private double longitude;
	private double latitude;
	private String location;
	private List<question> question;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public List<question> getQuestion() {
		return question;
	}
	public void setQuestion(List<question> question) {
		this.question = question;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	@Override
	public String toString() {
		return "res_answeradd [username=" + username + ", tid=" + tid + ", longitude=" + longitude + ", latitude="
				+ latitude + ", location=" + location + ", question=" + question + "]";
	}
	
}
