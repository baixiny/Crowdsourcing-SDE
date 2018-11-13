package com.websystique.springmvc.responseform;

import java.sql.Timestamp;


public class message_task {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String description;
	private String location;
	private String deadline;
	private double longitude;
	private double altitude;
	private String status;
	private String tasktype;
	private int answercount;
	
	private double distance;
	private String schedule;
	private Object qusetion;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTasktype() {
		return tasktype;
	}
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public int getAnswercount() {
		return answercount;
	}
	public void setAnswercount(int answercount) {
		this.answercount = answercount;
	}
	public Object getQusetion() {
		return qusetion;
	}
	public void setQusetion(Object qusetion) {
		this.qusetion = qusetion;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	@Override
	public String toString() {
		return "message_task [id=" + id + ", description=" + description + ", location=" + location + ", deadline="
				+ deadline + ", longitude=" + longitude + ", altitude=" + altitude + ", status=" + status
				+ ", tasktype=" + tasktype + ", answercount=" + answercount + ", qusetion=" + qusetion + ", distance="
				+ distance + ", schedule=" + schedule + "]";
	}
	
	
	
}
