package com.websystique.springmvc.responseform;

public class msg_cao {
	private String description;
	private Object question;
	@Override
	public String toString() {
		return "msg_cao [description=" + description + ", question=" + question + "]";
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getQuestion() {
		return question;
	}
	public void setQuestion(Object question) {
		this.question = question;
	}
}
