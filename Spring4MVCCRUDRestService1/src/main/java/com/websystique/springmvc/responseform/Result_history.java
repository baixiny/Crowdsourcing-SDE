package com.websystique.springmvc.responseform;

import java.util.List;

import com.websystique.springmvc.CStable.Historylist;



public class Result_history {
	private int state;
	private String error;
	private List<h_msg>  message;
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	

	public  List<h_msg> getMessage() {
		return message;
	}
	public void setMessage( List<h_msg> message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Result [state=" + state + ", error=" + error + ", message=" + message + "]";
	}
	
}
