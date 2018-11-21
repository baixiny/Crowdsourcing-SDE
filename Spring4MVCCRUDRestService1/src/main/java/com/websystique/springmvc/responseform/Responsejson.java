package com.websystique.springmvc.responseform;

public class Responsejson {

	private String username;
	private	int status;
	private int error;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getError() {
		return error;
	}
	
	@Override
	public String toString() {
		return "Responsejson [username=" + username + ", status=" + status + ", error=" + error + "]";
	}
	public void setError(int error) {
		this.error = error;
	}
}
