package com.websystique.springmvc.request;

public class question {
	private String qid;
	private String description;
	private int labelid;
	private String labeldescription;
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLabelid() {
		return labelid;
	}
	public void setLabelid(int labelid) {
		this.labelid = labelid;
	}
	public String getLabeldescription() {
		return labeldescription;
	}
	public void setLabeldescription(String labeldescription) {
		this.labeldescription = labeldescription;
	}
	@Override
	public String toString() {
		return "question [qid=" + qid + ", description=" + description + ", labelid=" + labelid + ", labeldescription="
				+ labeldescription + "]";
	}
	
	
	
	

	
}
