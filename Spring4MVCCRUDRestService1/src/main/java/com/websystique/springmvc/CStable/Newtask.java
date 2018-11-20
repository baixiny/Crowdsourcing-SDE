package com.websystique.springmvc.CStable;

import java.sql.Timestamp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Newtask {
		private Integer tid;   
	    private String description; 
	    private double longitude;
	    private double latitude;
	    private String location; 
	    private Integer answercount;
	    private String status; 
	    private String tasktype; 
	    private Timestamp publictime;
	    private Timestamp deadline;
	    private Object question;
	    
	    public Integer getId() {  
	    	
	      return tid;
	    } 
	  
	    public void setId(Integer tid) {  
	        this.tid=tid;  
	    }  
	  
	    public JSONArray getQuestion() {
	    	JSONArray question=JSONArray.fromObject(this.question);
	        return question;  
	    }  
	  
	    public void setQuestion(Object question) {  
	        this.question = question;  
	    }  
	    public double  getLongitude() {  
	        return longitude;  
	    }  
	  
	    public void setLongitude(double longitude) {  
	        this.longitude = longitude;  
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
	    public String getDescription() {  
	        return description;  
	    }  
	  
	    public void setDescription(String description) {  
	        this.description = description;  
	    }  
	    public Integer getAnswercount() {  
	    	
	        return answercount;
	      }  
	    
	      public void setAnswercount(Integer answercount) {  
	          this.answercount=answercount;  
	      }  
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
	    @Override  
	    public String toString() {  
	        return "{\"tid\":\"" + tid + "\", \"tasktype\":\"" + tasktype + "\", longitude\":\""  
	                + longitude + "\", latitude\":\"" +latitude + "\", \"location\":\"" +location+"\", \"description\":\"" +description+"\",\" publictime\":\"" +
	        		publictime+"\",\" deadline\":\"" +deadline+"\", \"answercount\":\"" +answercount+"\",\"status:\"" +status+"\",\"question:\"" +question+"\"}";  
	    }  
	}

