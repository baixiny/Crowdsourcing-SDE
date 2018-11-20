package com.websystique.springmvc.responseform;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.CStable.Questionnaire;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class Res_Questionnaire {
	private int state;
	private String error;
	private List<que_msg> message;
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

	public List<que_msg> getMessage() {
		return message;
	}
	public void setMessage(List<que_msg> message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Res_Questionnaire [state=" + state + ", error=" + error + ", message=" + message + "]";
	}
	public List<que_msg> getQuestionnaire(List<Questionnaire> Questionnaire) {
			List<que_msg> msglist=new ArrayList<que_msg>();
			Timestamp min=Questionnaire.get(0).getPublictime();
			int flag=0;
			for(int i=0;i<Questionnaire.size();i++) {
				if(min.after(Questionnaire.get(i).getPublictime())) {
					min=Questionnaire.get(i).getPublictime();
					flag=i;
				}	
			}
			
			int i=flag;
			
			que_msg msg=new que_msg();
			msg.setAnswercount(Questionnaire.get(i).getAnswercount());
			
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String dt = sdf.format(Questionnaire.get(i).getDeadline());  
			msg.setDeadline(dt);
			String pt =sdf.format(Questionnaire.get(i).getPublictime());
			msg.setPublictime(pt);
			msg.setDescription(Questionnaire.get(i).getDescription());
			msg.setIsShow(Questionnaire.get(i).getIsShow());
			msg.setLatitude(Questionnaire.get(i).getLatitude());
			msg.setLinkedtaskid(Questionnaire.get(i).getLinkedtaskid());
			msg.setLocation(Questionnaire.get(i).getLocation());
			msg.setLongitude(Questionnaire.get(i).getLongitude());
			msg.setQnaireid(Questionnaire.get(i).getQnaireid());
			msg.setStatus(Questionnaire.get(i).getStatus());
			JSONArray json=new JSONArray();
			json=JSONArray.fromObject((Questionnaire.get(i).getContent().getJSONObject(0).get("value")));
			System.out.println(json);
			msg.setContent(json);
			msglist.add(msg);
			
		return msglist;
		
	}
}
