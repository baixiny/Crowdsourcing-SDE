package com.websystique.springmvc.responseform;

import net.sf.json.JSONArray;

public class Answer_data {
	private String t_id;
	private String question;
	private String task_type;
	private String u_id;
	private String publish_place;
	private String answer_desc;
	private String answer_time;
	private String pic;
	private JSONArray answer;
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getPublish_place() {
		return publish_place;
	}
	public void setPublish_place(String publish_place) {
		this.publish_place = publish_place;
	}
	public String getAnswer_desc() {
		return answer_desc;
	}
	public void setAnswer_desc(String answer_desc) {
		this.answer_desc = answer_desc;
	}
	public String getAnswer_time() {
		return answer_time;
	}
	public void setAnswer_time(String answer_time) {
		this.answer_time = answer_time;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public JSONArray getAnswer() {
		return answer;
	}
	public void setAnswer(JSONArray answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Answer_data [t_id=" + t_id + ", question=" + question + ", task_type=" + task_type + ", u_id=" + u_id
				+ ", publish_place=" + publish_place + ", answer_desc=" + answer_desc + ", answer_time=" + answer_time
				+ ", pic=" + pic + ", answer=" + answer + "]";
	}
	
	
}
