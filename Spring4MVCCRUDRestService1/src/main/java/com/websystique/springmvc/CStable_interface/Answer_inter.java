package com.websystique.springmvc.CStable_interface;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Delete;  
import org.apache.ibatis.annotations.Insert;  
import org.apache.ibatis.annotations.Select;  
import org.apache.ibatis.annotations.Update;  

import com.websystique.springmvc.CStable.Answer;
import com.websystique.springmvc.CStable.Historylist;
import com.websystique.springmvc.CStable_interface.Answer_inter;
import com.websystique.springmvc.CStable.Historylist_detail;
public interface Answer_inter {
	    @Insert(" insert into answer(aid,tid,username,longitude,latitude,location,picpath,time,answer) values(#{aid},#{tid},#{username},#{longitude},#{latitude},#{location},#{picpath},#{time},#{answer,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler})")
		public  Integer addAnswer(Answer answer);  
	    
	    @Update("update answer set longitude=#{longitude:DECIMAL},latitude=#{latitude:DECIMAL}, location=#{location:LONGVARCHAR},picpath=#{picpath:LONGVARCHAR},answer=#{answer,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler},time=#{time:TIMESTAMP}  where aid=#{aid} and username=#{username} and  tid=#{tid}")
	    public boolean updateAnswer(Answer answer);
	    
	    @Delete(" delete from answer where username=#{arg1} and tid=#{arg0}")
	    public  boolean deleteAnswer(Integer TID,String username);  
	    
	    @Delete(" delete from answer where username=#{arg1} and tid=#{arg0} and answer=#{arg2}")
	    public  boolean deleteAnswer1(Integer TID,String username,Object answer);
	    
	    @Select("select * from answer where aid=#{aid}")
	    public  Answer findAnswer_aid(Integer AID); 
	    
	    @Select("select * from answer where tid=#{tid}")
	    public  List<Answer> findAnswer_tid(Integer TID); 
	    
	    @Select("select * from answer where username=#{username}")
	    public  List<Answer> findAnswer_username(String username); 
	    
	    @Select("select * from answer where username=#{arg1} and tid=#{arg0}")
	    public  List<Answer> findAnswer(Integer TID,String username); 
	    
	    @Select("select count(*) from answer where tid=#{tid}")
	    public int countAnswer(Integer TID);  
	    
	    @Select("select * from answer")
	    public List<Answer> listAnswer(); 
	    
	    //历史纪录信息
	    @Select("select answer.answer,answer.picpath from task,answer where answer.username=#{username:VARCHAR} and answer.tid=task.tid")
	    public  List<Historylist> selectHis(String username);
	    
	    @Select("select answer.tid,answer.time,task.location,task.tasktype,answer.picpath,task.description from task,answer where  answer.username=#{username:VARCHAR} and answer.tid=task.tid ")
	    public List<Historylist_detail>  selectHis_detail(String username);
	 
}
