package com.websystique.springmvc.CStable;

import java.sql.Timestamp;

import com.websystique.springmvc.CStable.Task;


public class Historylist_detail {

    /*JSONObject answer = new JSONObject();*/
    private Object answer;  
   private String picpath; 
   
    private Task task;
    
    public Task getTask(){
    	return task;
    }
    public void setTask(Task task) {  
        this.task=task;  
    }
    public String getPicpath(){
    	return picpath;
    }
    public void setPicpath(String picpath) {  
        this.picpath=picpath;  
    }
    public Object getAnswer(){
    	return answer;
    }
    public void setAnswer(Object answer) {  
        this.answer=answer;  
    }
    
   
 /*public String getTasktype() {  
    	
        return tasktype;
      }  
    
      public void setTasktype(String tasktype) {  
          this.tasktype=tasktype;  
      } */
 
      @Override  
      /*public String toString() {  
          return "{\"tid\":\"" +tid+ "\",\"tasktype\":\"" +tasktype+ "\", \"description\":\"" +description+ "\",\"time\":\"" +time+ "\",\"Answer\":\"" + answer + "\", \"question\":\"" +question+"\", \"status\"£º\"" +status+"\"}";  
    }*/
      public String toString() {  
          return "{\"picpath:\""+picpath+ "\","+ "\"answer\":\"" + answer + "\"}";  
    }
}