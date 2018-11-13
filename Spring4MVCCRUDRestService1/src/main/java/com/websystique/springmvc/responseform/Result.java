package com.websystique.springmvc.responseform;

import net.sf.json.JSONArray;

public class Result {
	private int state;
	private String error;
	private JSONArray  message;
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
	
	public JSONArray getMessage() {
		return message;
	}
	public void setMessage(JSONArray message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Result [state=" + state + ", error=" + error + ", message=" + message + "]";
	}
	

}