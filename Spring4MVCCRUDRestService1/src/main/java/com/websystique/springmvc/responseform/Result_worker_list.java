package com.websystique.springmvc.responseform;
import java.util.List;

import com.websystique.springmvc.CStable.Worker;

public class Result_worker_list {
	private int status;
	private List<User_data> data;
	private String message;
	private String error;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public List<User_data> getData() {
		return data;
	}
	public void setData(List<User_data> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "Result_worker_list [status=" + status + ", data=" + data + ", message=" + message + ", error=" + error
				+ "]";
	}
	
	
}
