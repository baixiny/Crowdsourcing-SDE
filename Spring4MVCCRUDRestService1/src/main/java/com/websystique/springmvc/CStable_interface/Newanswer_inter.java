package com.websystique.springmvc.CStable_interface;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Delete;  
import org.apache.ibatis.annotations.Insert;  
import org.apache.ibatis.annotations.Select;  
import org.apache.ibatis.annotations.Update;  

import com.websystique.springmvc.CStable.Historylist;
import com.websystique.springmvc.CStable.Newanswer;
import com.websystique.springmvc.CStable.Historylist_detail;
public interface Newanswer_inter {
	
	   //添加一个答案
	   @Insert(" insert into newanswer(aid,tid,username,longitude,altitude,location,picpath,time,answer) values(#{aid},#{tid},#{username},#{longitude},#{altitude},#{location},#{picpath},#{time},#{answer,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler_note})")
		public  Integer addAnswer(Newanswer answer); 
	    
	    //根据回答id更新一个答案
	    @Update("update newanswer set longitude=#{longitude:DECIMAL},latitude=#{latitude:DECIMAL}, location=#{location:LONGVARCHAR},picpath=#{picpath:LONGVARCHAR},time=#{time:TIMESTAMP},answer=#{answer,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler_note} where  aid=#{aid} ")
	    public boolean updateAnswer(Newanswer answer);
	    
	    //删除一个用户的某个任务的答案
	    @Delete(" delete from newanswer where username=#{arg1} and tid=#{arg0}")
	    public  boolean deleteAnswer(Integer TID,String username);
	    
	    //查找一个任务的所有用户的答案
	    @Select("select * from newanswer where tid=#{tid}")
	    public  List<Newanswer> findAnswer_tid(Integer TID);
	    
	    //查找某个用户的所有任务的回答
	    @Select("select * from newanswer where username=#{username}")
	    public  List<Newanswer> findAnswer_username(String username);
	    
	    //查找某个用户的某个任务的回答
	    @Select("select * from newanswer where username=#{username}and tid=#{tid}")
	    public  List<Newanswer> findAnswer(Integer TID,String username);
	    
	    //返回某个任务的回答个数
	    @Select("select count(*) from newanswer where tid=#{tid}")
	    public int countAnswer(Integer TID);
	    
	    //返回所有答案
	    @Select("select * from newanswer")
	    public List<Newanswer> listAnswer(); 
	    
	   //查找某个用户对于某个任务的回答的标签内容
	  @Select("select  row_to_json(t)->'answer'#>>'{${arg2},label}' as label,username from  newanswer as t where tid=#{arg0} and username=#{arg1}")
	  public String getAnswer_label(Integer tid,String username,Integer index); 
	  
	  //查找某个用户对于某个任务的回答的回答编号
	  @Select("select  row_to_json(t)->'answer'#>>'{${arg2},qid}' as qid,username from  newanswer as t where tid=#{arg0} and username=#{arg1}")
	  public String getAnswer_qid(Integer tid,String username,Integer index); 
	  
	  //查找某个用户对于某个任务的回答
	  @Select("select  row_to_json(t)->>'answer' from  newanswer as t where tid=#{arg0} and username=#{arg1}")
	  public String getAnswer(Integer tid,String username);
}
