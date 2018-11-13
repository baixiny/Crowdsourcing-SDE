package com.websystique.springmvc.responseform;

import java.util.List;

import com.websystique.springmvc.CStable.Task;

public class Result_task_one {
	private int status;
	private String message;
	private String error;
	private int length;

	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Result_task_one [status=" + status + ", message=" + message + ", error=" + error + ", length=" + length
				+ "]";
	}
	
	
	
}
