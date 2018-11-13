package com.websystique.springmvc.responseform;
import java.util.List;

import com.websystique.springmvc.CStable.*;


public class Result_answer {
	private int status;

	private List<Answer_data> data;
	private String message;
	private String error;

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	


	public List<Answer_data> getData() {
		return data;
	}
	public void setData(List<Answer_data> data) {
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
		return "Result_answer [status=" + status + ", data=" + data + ", message=" + message + ", error=" + error + "]";
	}
	
}
