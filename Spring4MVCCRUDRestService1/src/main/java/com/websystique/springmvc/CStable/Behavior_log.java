package com.websystique.springmvc.CStable;


import java.sql.Timestamp;



public class Behavior_log {
	private String username; 
    private double longitude;
    private double altitude;
    private String location;  
    private String operation; 
    private Timestamp operationtime;
   
    public String getUsername() {  
    	
      return username;
    }  
  
    public void setUsername(String username) {   
        this.username=username;
    }  
  

  
    public double  getlongtitude() {  
        return longitude;  
    }  
  
    public void setLongtitude(double longtitude) {  
        this.longitude = longtitude;  
    }  
    public double  getAltitude() {  
        return altitude;  
    }  
  
    public void setAltitude(double altitude) {  
        this.altitude = altitude;  
    }  
  
    public String getLocation() {  
        return location;  
    }  
  
    public void setLocation(String operation) {  
        this.operation = operation;  
    }  
    public String getOperation() {  
        return operation;  
    }  
  
    public void setOperation(String operation) {  
        this.operation = operation;  
    }  
    public Timestamp getOperationtime() {  
        return operationtime;  
    }  
  
    public void setOperationtime(Timestamp operationtime) {  
        this.operationtime = operationtime;  
    }  
    @Override  
    public String toString() {  
        return "{\"username\":\" "+ username + "\",\"longitude\":\"" + longitude + "\", \"altitude\":\"" +altitude + "\", \"location:\"" +location+"\", \"operation\":\"" +operation+"\", \"operationtime\":\"" +operationtime+"\"}";  
    }  
}
