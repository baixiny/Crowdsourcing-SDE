package com.websystique.springmvc.responseform;

public class Result_cao {
	private int state;
	private String error;
	private msg_cao message;
	@Override
	public String toString() {
		return "Result_cao [state=" + state + ", error=" + error + "]";
	}
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
	public msg_cao getMessage() {
		return message;
	}
	public void setMessage(msg_cao message) {
		this.message = message;
	}
	public void setMessage(String description,Object question)
	{
		this.message.setDescription(description);
		this.message.setQuestion(question);
	}

}
