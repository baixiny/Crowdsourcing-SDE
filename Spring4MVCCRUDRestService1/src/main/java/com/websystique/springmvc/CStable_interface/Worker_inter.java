package com.websystique.springmvc.CStable_interface;


import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.CStable.Worker;

import org.apache.ibatis.annotations.Delete;  
import org.apache.ibatis.annotations.Insert;  
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;  
import org.apache.ibatis.annotations.Update; 
public interface Worker_inter {
	@Insert("insert into worker(username,password,phoneno,reward,sex,age,area,skill) values(#{username},#{password},#{phoneno},#{reward},#{sex},#{age},#{area},#{skill}) ")
	public  Integer addWorker(Worker user);  
	
	@Update("update worker set password=#{password:VARCHAR}, phoneno=#{phoneno:VARCHAR},reward=#{reward},sex=#{sex},age=#{age},area=#{area},skill=#{skill} where username=#{username:VARCHAR}  ")
    public boolean updateWorker(Worker user);  
	
    @Delete("delete from worker where username=#{username:VARCHAR}")
    public  boolean deleteWorker(String username);  
    
    @Select("select * from worker where username=#{username:VARCHAR}")
    public  Worker findWorker(String username);  
    
    @Select("select count(*) from worker ")
    public int countWorker();  
    
    @Select(" select * from worker")
    public List<Worker> listWorker(); 
}
