package com.websystique.springmvc.responseform;

public class result_task_add {
	private int state;
	private String error;
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
	@Override
	public String toString() {
		return "result_task_add [state=" + state + ", error=" + error + "]";
	}
	
}
