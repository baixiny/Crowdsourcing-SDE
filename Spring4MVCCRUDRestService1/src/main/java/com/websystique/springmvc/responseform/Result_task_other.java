package com.websystique.springmvc.responseform;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.math.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.websystique.springmvc.CStable.*;
import com.websystique.springmvc.CStable_interface.Answer_inter;
import com.websystique.springmvc.CStable_interface.Task_inter;
import com.websystique.springmvc.MybatisUtil.MybatisUtil;
//返回体，包括 state error message{ ID location description deadline}
public class Result_task_other {
	private int state;
	private String error;
	private List<message_task> message;
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
	public List<message_task> getMessage() {
		return message;
	}
	public void setMessage(List<message_task> message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "Result_task_other [state=" + state + ", error=" + error + ", message=" + message + "]";
	}
	public List<message_task> getmessage(List<Task> task,double x,double y){
		 List<message_task> msg=new ArrayList<message_task>();
		 Location location=new Location();
		 SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
	     SqlSession sqlSession = sqlSessionFactory.openSession();  
		 Answer_inter answer_inter = sqlSession.getMapper(Answer_inter.class);
		
		 for(int i=0;i<task.size();i++)
		{	
			if(task.get(i).getStatus().equals("正在执行"));
			{
				message_task t=new message_task();
			
			t.setId(task.get(i).getId());
			t.setLocation(task.get(i).getLocation());
			t.setDescription(task.get(i).getDescription());
			double m=answer_inter.countAnswer(task.get(i).getId());
			double n=Double.parseDouble(task.get(i).getAnswersnum());
		
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String tsStr = sdf.format(task.get(i).getDeadline());  
			
		    t.setDeadline(tsStr);
			t.setDistance((int)Math.abs(location.algorithm(x, y, task.get(i).getLongitude(), task.get(i).getLatitude())));
			t.setLongitude(task.get(i).getLongitude());
			t.setAltitude(task.get(i).getLatitude());
			if(m==0){
				t.setSchedule("0%");
			}else{
				t.setSchedule(m/n*100+"%");
			}
			t.setAnswercount(task.get(i).getAnswercount());
			t.setTasktype(task.get(i).getTasktype());
			t.setStatus(task.get(i).getStatus());
			t.setQusetion(task.get(i).getQuestion());
			t.setAnswersnum(task.get(i).getAnswersnum());
			msg.add(t);
			
			}
		}
		return msg;
		
	}
	public List<message_task> getmessage(List<Task> task){
		 List<message_task> msg=new ArrayList<message_task>();
		 
		for(int i=0;i<task.size();i++)
		{	
			if(task.get(i).getStatus().equals("正在执行"));
			{
				message_task t=new message_task();
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String tsStr = sdf.format(task.get(i).getDeadline());  
			    System.out.println(tsStr);
			t.setId(task.get(i).getId());
			t.setLocation(task.get(i).getLocation());
			t.setDescription(task.get(i).getDescription());
			t.setDeadline(tsStr);
			
			
			msg.add(t);
			
			}
		}
		return msg;
		
	}
}
