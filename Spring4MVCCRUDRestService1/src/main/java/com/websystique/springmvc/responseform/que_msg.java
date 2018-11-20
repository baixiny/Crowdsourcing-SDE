package com.websystique.springmvc.responseform;
import net.sf.json.JSONArray;

public class que_msg {
		




		private Integer qnaireid;  
		private Integer isshow;   
	    private String description; 
	    private double longitude;
	    private double latitude;
	    private String location; 
	    private Integer answercount;
	    private String status; 
	    private String publictime;
	    private String deadline;
	    private JSONArray content;
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

		
		public String getPublictime() {
			return publictime;
		}
		public void setPublictime(String publictime) {
			this.publictime = publictime;
		}
		public String getDeadline() {
			return deadline;
		}
		public void setDeadline(String deadline) {
			this.deadline = deadline;
		}
		public JSONArray getContent() {
			return content;
		}
		public void setContent(JSONArray content) {
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
