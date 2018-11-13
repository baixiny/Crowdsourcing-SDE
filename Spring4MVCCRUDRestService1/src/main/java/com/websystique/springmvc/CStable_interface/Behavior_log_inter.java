package com.websystique.springmvc.CStable_interface;

import java.util.List;

import com.websystique.springmvc.CStable.Behavior_log;
import org.apache.ibatis.annotations.Delete;  
import org.apache.ibatis.annotations.Insert;  
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;  
import org.apache.ibatis.annotations.Update; 
public interface Behavior_log_inter {
	@Insert("insert into behavior_log(username,longitude,altitude,location,operation,operationtime) values(#{username},#{longitude},#{altitude},#{location},#{opeartion},#{operationtime}) ")
	public  Integer addBehavior_log(Behavior_log behavior_log);  
	@Update("update  behavior_log set longitude=#{longitude:DECIMAL},altitude=#{altitude:DECIMAL}, location=#{location:LONGVARCHAR},opeartion=#{operation:LONGVARCHAR},operationtime=#{operationtime:TIMESTAMP}where username=#{username:VARCHAR} ")
    public boolean updateBehavior_log(Behavior_log behavior_log);  
    @Delete(" delete from  behavior_log where username=#{username:VARCHAR} ")
    public  boolean deleteBehavior_log(Integer WID);  
    @Select(" select * from  behavior_log where username=#{username:VARCHAR} ")
    public  Behavior_log findBehavior_log(Integer WID);  
    @Select("  select count(*) from  behavior_log ")
    public int countBehavior_log();  
    @Select("  select * from  behavior_log ")
    public List<Behavior_log> listBehavior_log(); 
}
