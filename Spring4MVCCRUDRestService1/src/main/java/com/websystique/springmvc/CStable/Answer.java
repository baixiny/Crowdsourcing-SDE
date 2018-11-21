

package com.websystique.springmvc.CStable;


import java.sql.Timestamp;


public class Answer {
	private String username;
	private int aid;
	private int tid;  
    private double longitude;
    private double latitude;
    private String location;  
    private String picpath; 
    private Timestamp time;
    /*JSONObject answer = new JSONObject();*/
    private Object answer;

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
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

	public String getPicpath() {
		return picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Object getAnswer() {
		return answer;
	}

	public void setAnswer(Object answer) {
		this.answer = answer;
	}


	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	@Override  
    public String toString() {  
        return "{\"aid\":\"" +aid+"\",\"username\":\"" +username+ "\",\"tid\":\"" +tid+ "\",\"Answer\":\"" + answer + "\", \"longtitude\":\""  
                + longitude + "\", \"latitude\":\"" +latitude + "\", \"location\":\"" +location+"\", \"picpath\":\"" +picpath+"\", \"time\"£º\"" +time+"\"}";  
    }  
}

