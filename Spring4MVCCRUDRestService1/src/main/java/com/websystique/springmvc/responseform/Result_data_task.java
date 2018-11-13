package com.websystique.springmvc.responseform;

import java.util.List;

import com.websystique.springmvc.CStable.*;

public class Result_data_task {
	private int status;
	private String error;
	private List<data_task> data;
	private String message;
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

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<data_task> getData() {
		return data;
	}
	public void setData(List<data_task> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Result_data_task [status=" + status + ", error=" + error + ", data=" + data + ", message=" + message
				+ "]";
	}

}
