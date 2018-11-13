package com.websystique.springmvc.CStable;

import java.sql.Timestamp;

import com.websystique.springmvc.CStable.Task;


public class Historylist {
    /*JSONObject answer = new JSONObject();*/
    private int tid;
    private Timestamp time;
    public String getTasktype() {
		return tasktype;
	}
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	private String tasktype;
    private String location;
    private String picpath;
    
    public int getId(){
    	return tid;
    }
    public void setId(int tid) {  
        this.tid=tid;  
    }
    
    public Timestamp getTime(){
    	return time;
    }
    public void setTime(Timestamp time) {  
        this.time=time;  
    }
    public String getLocation(){
    	return location;
    }
    public void setLocation(String location) {  
        this.location=location;  
    }
    public String getPicpath(){
    	return picpath;
    }
    public void setPicpath(String picpath) {  
        this.picpath=picpath;  
    }
 
      @Override  
   
      public String toString() {  
          return "{\"tid\":\"" +tid+ "\",\"time\":\"" +time+ "\", \"tasktype\":\"" +tasktype+ "\", \"location\":\"" +location+ "\", \"picpath\":\"" +picpath+"\"}";  
    }
}