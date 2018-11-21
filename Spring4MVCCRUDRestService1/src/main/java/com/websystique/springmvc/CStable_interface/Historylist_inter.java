package com.websystique.springmvc.CStable_interface;

import java.util.List;

import org.apache.ibatis.annotations.Delete;  
import org.apache.ibatis.annotations.Insert;  
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;  
import org.apache.ibatis.annotations.Update;

import com.websystique.springmvc.CStable.Historylist;
import com.websystique.springmvc.CStable.Historylist_detail;

public interface Historylist_inter {
	@Select("select newanswer.tid,newanswer.time,newtask.location,newtask.tasktype,newanswer.picpath,newtask.description from newtask,newanswer where newanswer.tid=newtask.tid and newanswer.username=#{username:VARCHAR}")
   /*{@Result(property = "tid",column = "tid"),
            @Result(property = "username",column = "username"),
            @Result(property = "answer",javaType = List.class,column ="tid",
                    many = @Many(select = "com.websystique.springmvc.CStable_interface.Historylist_inter.selecHis"))}*/
    List<Historylist> selectHis(String username);
	@Select("select newanswer.answer,newanswer.picpath from newtask,newanswer where  newanswer.username=#{username:VARCHAR} and newanswer.tid=newtask.tid ")
	 List<Historylist_detail> selectHis_detail(String username);
    /*@Select("select s.name from RELATION r,STUDENT s where s.id = r.sid and r.tid = #{tid}")
    List<Student> findStudents(Long tid);*/
}
