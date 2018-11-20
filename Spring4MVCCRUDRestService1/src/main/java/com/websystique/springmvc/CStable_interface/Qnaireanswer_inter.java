package com.websystique.springmvc.CStable_interface;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.websystique.springmvc.CStable.Qnaireanswer;

public interface Qnaireanswer_inter {

		
		   //添加一个答案
		   @Insert(" insert into qnaireanswer(aid,tid,username,longitude,latitude,location,picpath,time,qnanswer) values(#{aid},#{tid},#{username},#{longitude},#{latitude},#{location},#{picpath},#{time},#{qnanswer,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler_note})")
			public  Integer addQnireanswer(Qnaireanswer answer); 
		    
		    //根据回答id更新一个答案
		    @Update("update qnaireanswer set longitude=#{longitude:DECIMAL},latitude=#{latitude:DECIMAL}, location=#{location:LONGVARCHAR},picpath=#{picpath:LONGVARCHAR},time=#{time:TIMESTAMP},qnanswer=#{qnanswer,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler_note} where  aid=#{aid} ")
		    public boolean updateAnswer(Qnaireanswer answer);
		    
		    //删除一个用户的某个任务的答案
		    @Delete(" delete from qnaireanswer where username=#{arg1} and tid=#{arg0}")
		    public  boolean deleteAnswer(Integer TID,String username);
		    
		    //查找一个任务的所有用户的答案
		    @Select("select * from qnaireanswer where tid=#{tid}")
		    public  List<Qnaireanswer> findAnswer_tid(Integer TID);
		    
		    //查找某个用户的所有任务的回答
		    @Select("select * from qnaireanswer where username=#{username}")
		    public  List<Qnaireanswer> findAnswer_username(String username);
		    
		    //查找某个用户的某个任务的回答
		    @Select("select * from qnaireanswer where username=#{arg1}and tid=#{arg0}")
		    public  List<Qnaireanswer> findAnswer(Integer TID,String username);
		    
		    //返回某个任务的回答个数
		    @Select("select count(*) from qnaireanswer where tid=#{tid}")
		    public int countAnswer(Integer TID);
		    
		    //返回所有答案
		    @Select("select * from qnaireanswer")
		    public List<Qnaireanswer> listAnswer(); 
		    
		   //查找某个用户对于某个任务的回答的标签内容
		  @Select("select  row_to_json(t)->'qnanswer'#>>'{${arg2},label}' as label,username from  qnaireanswer as t where tid=#{arg0} and username=#{arg1}")
		  public String getAnswer_label(Integer tid,String username,Integer index); 
		  
		  //查找某个用户对于某个任务的回答的回答编号
		  @Select("select  row_to_json(t)->'qnanswer'#>>'{${arg2},qid}' as qid,username from  qnaireanswer as t where tid=#{arg0} and username=#{arg1}")
		  public String getAnswer_qid(Integer tid,String username,Integer index); 
		  
		  //查找某个用户对于某个任务的回答
		  @Select("select  row_to_json(t)->>'qnanswer' from  qnaireanswer as t where tid=#{arg0} and username=#{arg1}")
		  public String getAnswer(Integer tid,String username);
	
}
