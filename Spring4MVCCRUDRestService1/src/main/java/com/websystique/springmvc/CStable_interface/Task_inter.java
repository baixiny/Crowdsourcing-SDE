package com.websystique.springmvc.CStable_interface;

import java.util.List;

import com.websystique.springmvc.CStable.Task;

import org.apache.ibatis.annotations.Delete;  
import org.apache.ibatis.annotations.Insert;  
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;  
import org.apache.ibatis.annotations.Update; 
public interface Task_inter {
	@Insert("insert into task(tid,longitude,latitude,location,description,publictime,question,status,answercount,tasktype,deadline) values(#{tid},#{longitude},#{latitude},#{location},#{description},#{publictime},#{question,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler},#{status},#{answercount},#{tasktype},#{deadline})")
	public  Integer addTask(Task task);  
	
	@Update(" update task set tasktype=#{tasktype:VARCHAR} , longitude=#{longitude:DECIMAL},latitude=#{latitude:DECIMAL}, location=#{location:VARCHAR}, status=#{status:VARCHAR},answercount=#{answercount:INTEGER},description=#{description:VARCHAR},publictime=#{publictime:TIMESTAMP}, deadline=#{deadline:TIMESTAMP}, question=#{question,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler}where tid=#{tid:INTEGER}") 
    public boolean updateTask(Task task);  
    
	@Delete(" delete from task where tid= #{tid:INTEGER}")
    public  boolean deleteTask(Integer TID);  
    
	@Select("select * from task where tid = #{tid:INTEGER} ")
    public  Task findTask(Integer TID);  
   
	@Select("select count(*) from task")
    public int countTask();  
    
	@Select("select * from task ")
    public List<Task> listTask(); 
   
	@Select("select * from task where location like '%${location}%'")
    public List<Task> findTask1(@Param(value="location")String location);//by ygj ，用于通过匹配location字符返回一个task的列表。
}
