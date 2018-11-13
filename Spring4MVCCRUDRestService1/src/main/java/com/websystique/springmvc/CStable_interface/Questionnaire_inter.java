package com.websystique.springmvc.CStable_interface;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.websystique.springmvc.CStable.Questionnaire;

public interface Questionnaire_inter {

	//添加一个问卷
	@Insert("insert into questionnaire(isshow,linkedtaskid,qnaireid,longitude,latitude,location,description,publictime,content,status,answercount,deadline) values(#{isshow},#{linkedtaskid},#{qnaireid},#{longitude},#{latitude},#{location},#{description},#{publictime},#{content,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler_note},#{status},#{answercount},#{deadline})")
	public  Integer addQuestionnaire(Questionnaire questionnaire);
	
	//更新一个问卷
	@Update("update questionnaire set isshow=#{isshow:INTEGER} , longitude=#{longitude:DECIMAL},latitude=#{latitude:DECIMAL}, location=#{location:VARCHAR}, status=#{status:VARCHAR},answercount=#{answercount:INTEGER},description=#{description:VARCHAR},publictime=#{publictime:TIMESTAMP}, deadline=#{deadline:TIMESTAMP}, content=#{content,typeHandler=com.websystique.springmvc.handler.JsonTypeHandler_note},linkedtaskid=#{linkedtaskid:INTEGER} where qnaireid=#{qnaireid:INTEGER}") 
    public boolean updateQuestionnaire(Questionnaire questionnaire);  
	
	//更新问卷的isShow状态,发布过的问卷状态改为0
	@Update("update questionnaire set isshow=0 where isshow=1")
	 public boolean updateIsShow(); 
	
	//删除一个问卷
    @Delete(" delete from questionnaire where qnaireid= #{qnaireid:INTEGER}")
    public  boolean deleteQuestionnaire(Integer qnaireid);
  
    
    //查找无关联任务但可以显示的问卷
    @Select("select * from questionnaire where isshow=1 and linkedtaskid is null")
    public List<Questionnaire> findNonLinkedIsShow();
    
  //查找有关联任务并且可以显示的问卷
    @Select("select * from questionnaire where isshow=1 and linkedtaskid=#{linkedtaskid:INTEGER}")
    public  List<Questionnaire> findLinkedIsShow(Integer linkedtaskid);
    
    //查询某任务所对应的问卷id
    @Select("select qnaireid from questionnaire where  linkedtaskid=#{linkedtaskid:INTEGER}")
    public List<Integer> findLinkedqn(Integer linkedtaskid);
    //根据问卷id查询问卷
    @Select("select * from questionnaire where  qnaireid= #{qnaireid:INTEGER}")
    public Questionnaire findqn_id(Integer qnaireid);
    //查询用户没有做过的活跃的问卷（无关联）
    @Select("select qnaireid from questionnaire where  qnaireid not in(select tid from qnaireanswer where username=#{arg1} and isshow=1)")
   public List<Integer> findQuestionnonlinked(String unsername );
    
    //查询用户没有做过的活跃的问卷（有关联）
    @Select("select qnaireid from questionnaire where  linkedtaskid=#{arg0} and qnaireid not in(select tid from qnaireanswer where username=#{arg1} and isshow=1)")
   public List<Integer> findQuestionlinked(int linkedtaskid,String unsername );
   
    //返回任务问卷
    @Select("select count(*) from questionnaire")
    public int countQuestionnaire();
    
    //返回所有问卷
    @Select("select * from questionnaire ")
    public List<Questionnaire> listQuestionnaire(); 
    
    //返回某个地址的问卷
   // @Select("select * from questionnaire where location like '%${location}%'")
   // public List<Questionnaire> findQuestionnaire_location(@Param(value="location")String location);//by ygj ，用于通过匹配location字符返回一个task的列表。	
}
