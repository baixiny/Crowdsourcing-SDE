package com.websystique.springmvc.responseform;

import java.util.List;

import com.websystique.springmvc.CStable.Answer;

public class Result_answer_one {
	private int state;
	private String error;
	private Answer message;
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
	

	
	public Answer getMessage() {
		return message;
	}
	public void setMessage(Answer message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Result [state=" + state + ", error=" + error + ", message=" + message + "]";
	}
}
