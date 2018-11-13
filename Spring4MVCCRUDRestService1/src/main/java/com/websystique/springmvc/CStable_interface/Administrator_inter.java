package com.websystique.springmvc.CStable_interface;

import com.websystique.springmvc.CStable.Administrator;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
public interface Administrator_inter {
	@Insert ("insert into administrator(username,password,privilege) values(#{username},#{password},#{privilege}")
	public int addAdministrator(Administrator user);  
	  
	@Update ("update administrator set username=#{username:VARCHAR},password=#{password:VARCHAR}, previlege=#{privilege:VARCHAR}") 
    public boolean updateAdministrator(Administrator user);  
  
    @Delete ("delete from administrator where username=#{username:VARCHAR}")
    public  boolean deleteAdministrator(String username);  
  
    @Select ("select * from administrator where username=#{username:VARCHAR}")
    public  Administrator findAdministrator(String username);  
  
    @Select("select count(*) from administrator")
    public int countAdministrator();  
  
    @Select("select * from administrator")
    public List<Administrator> listAdministrator(); 
}
