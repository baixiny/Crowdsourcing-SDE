package com.websystique.springmvc.CStable_interface;

import java.util.List;

import com.websystique.springmvc.CStable.Newtask;
import com.websystique.springmvc.CStable.Task;

import net.sf.json.JSONObject;

import org.apache.ibatis.annotations.Delete;  
import org.apache.ibatis.annotations.Insert;  
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;  
import org.apache.ibatis.annotations.Update; 
public interface Newtask_inter {
	
	//添加一个任务
	@Insert("insert into newtask(tid,longitude,altitude,location,description,publictime,question,status,answercount,tasktype,deadline) values(#{tid},#{longitude},#{altitude},#{location},#{description},#{publictime},#{question,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler_note},#{status},#{answercount},#{tasktype},#{deadline})")
	public  Integer addTask(Newtask task);
	
	//更新一个任务
	@Update(" update newtask set tasktype=#{tasktype:VARCHAR} , longitude=#{longitude:DECIMAL},altitude=#{altitude:DECIMAL}, location=#{location:VARCHAR}, status=#{status:VARCHAR},answercount=#{answercount:INTEGER},description=#{description:VARCHAR},publictime=#{publictime:TIMESTAMP}, deadline=#{deadline:TIMESTAMP}, question=#{question,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler_note} where tid=#{tid:INTEGER}") 
    public boolean updateTask(Newtask task);  
	
	//删除一个任务
    @Delete(" delete from newtask where tid= #{tid:INTEGER}")
    public  boolean deleteTask(Integer TID);
    
    //查找一个任务
    @Select("select * from newtask where tid = #{tid:INTEGER} ")
    public  Newtask findTask(Integer TID);
    
    //返回任务个数
    @Select("select count(*) from newtask")
    public int countTask();
    
    //返回所有任务
    @Select("select * from newtask ")
    public List<Newtask> listTask(); 
    
    //返回某个地址的任务
    @Select("select * from newtask where location like '%${location}%'")
    public List<Newtask> findTask_location(@Param(value="location")String location);//by ygj ，用于通过匹配location字符返回一个task的列表。
    
}
