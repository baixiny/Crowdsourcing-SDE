package com.websystique.springmvc.CStable;

import java.sql.Timestamp;

public class Qnaireanswer {
	private String username;
	
	private int tid;  
	private int aid;  
	private double longitude;
    private double latitude;
    private double altitude;
	private String location;  
    private String picpath; 
    private Timestamp time;
    /*JSONObject answer = new JSONObject();*/
    private Object qnanswer;
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
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
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
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
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
	public Object getQnanswer() {
		return qnanswer;
	}
	public void setQnanswer(Object qnanswer) {
		this.qnanswer = qnanswer;
	}
    @Override
    public String toString() {  
        return "{\"aid\":\"" +aid+ "\",\"username\":\"" +username+ "\",\"tid\":\"" +tid+ "\",\"Qnanswer\":\"" + qnanswer + "\", \"longtitude\":\""  
                + longitude + "\", \"latitude\":\"" +latitude + "\", \"location\":\"" +location+"\", \"picpath\":\"" +picpath+"\", \"time\"£º\"" +time+"\"}";  
    }  
}
