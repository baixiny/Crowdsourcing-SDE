package com.websystique.springmvc.CStable;

import java.sql.Timestamp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class Newanswer {
	private String username;
	private int tid;  
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	private int aid;  
    private double longitude;
    private double latitude;
    private String location;  
    private String picpath; 
    private Timestamp time;
    private Object answer;
   
    public String getUsername() {  
    	
      return username;
    }  
  
    public void setUsername(String username) {  
        this.username=username;  
    }  
    public int getId() {  
    	
        return tid;
      }  
    
      public void setId(int tid) {  
          this.tid=tid;  
      } 
    
    public JSONArray getAnswer() {  
    	JSONArray answer=JSONArray.fromObject(this.answer);
        return answer;  
    }  
  
    public void setAnswer(Object answer) {  
        this.answer = answer;  
    }  
  
    public double  getlongitude() {  
        return longitude;  
    }  
  
    public void setLongitude(double longtitude) {  
        this.longitude = longtitude;  
    }  
    public double  getLatitude() {  
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
    @Override  
    public String toString() {  
        return "{\"aid\":\"" +aid+ "\",\"username\":\"" +username+ "\",\"tid\":\"" +tid+ "\",\"Answer\":\"" + answer + "\", \"longitude\":\""  
                + longitude + "\", \"latitude\":\"" +latitude + "\", \"location\":\"" +location+"\", \"picpath\":\"" +picpath+"\", \"time\"£º\"" +time+"\"}";  
    }  
}
