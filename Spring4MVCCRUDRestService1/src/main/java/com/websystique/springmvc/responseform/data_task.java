package com.websystique.springmvc.responseform;

import net.sf.json.JSONArray;

public class data_task {
	private String t_id;
	private String task_name;
	private String desc;
	private String publish_time;
	private String end_time;
	private String expect_answer_count;
	private String actual_answer_count;
	private String task_status;
	private String task_progress;
	private JSONArray task_question;
	
	public JSONArray getTask_question() {
		return task_question;
	}
	public void setTask_question(JSONArray task_question) {
		this.task_question = task_question;
	}
	public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getTask_name() {
		return task_name;
	}
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getExpect_answer_count() {
		return expect_answer_count;
	}
	public void setExpect_answer_count(String expect_answer_count) {
		this.expect_answer_count = expect_answer_count;
	}
	public String getActual_answer_count() {
		return actual_answer_count;
	}
	public void setActual_answer_count(String actual_answer_count) {
		this.actual_answer_count = actual_answer_count;
	}
	public String getTask_status() {
		return task_status;
	}
	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}
	public String getTask_progress() {
		return task_progress;
	}
	public void setTask_progress(String task_progress) {
		this.task_progress = task_progress;
	}
	@Override
	public String toString() {
		return "data_task [t_id=" + t_id + ", task_name=" + task_name + ", desc=" + desc + ", publish_time="
				+ publish_time + ", end_time=" + end_time + ", expect_answer_count=" + expect_answer_count
				+ ", actual_answer_count=" + actual_answer_count + ", task_status=" + task_status + ", task_progress="
				+ task_progress + ", task_question=" + task_question + "]";
	}
	
	
}
