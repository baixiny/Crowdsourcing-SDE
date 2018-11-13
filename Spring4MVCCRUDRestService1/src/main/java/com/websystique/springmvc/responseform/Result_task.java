package com.websystique.springmvc.responseform;

import java.util.List;

import com.websystique.springmvc.CStable.*;

public class Result_task {
	private int status;
	private String error;
	private List<Task> message;
	public int getState() {
		return status;
	}
	public void setState(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	public List<Task> getMessage() {
		return message;
	}
	public void setMessage(List<Task> message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Result_task [status=" + status + ", error=" + error + ", message=" + message + "]";
	}
	
}
