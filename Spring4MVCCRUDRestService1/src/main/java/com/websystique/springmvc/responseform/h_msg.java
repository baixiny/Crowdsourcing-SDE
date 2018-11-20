package com.websystique.springmvc.responseform;

import java.sql.Timestamp;

public class h_msg {
	private int tid;
	private String time;
	private String title;
	private String location;
	private String icon;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Override
	public String toString() {
		return "h_msg [tid=" + tid + ", time=" + time + ", title=" + title + ", location=" + location + ", icon=" + icon
				+ "]";
	}
	
	

}
