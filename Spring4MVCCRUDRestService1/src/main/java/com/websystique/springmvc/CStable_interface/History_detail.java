package com.websystique.springmvc.CStable_interface;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.websystique.springmvc.CStable.Historylist;
import com.websystique.springmvc.CStable.Historylist_detail;
import com.websystique.springmvc.MybatisUtil.MybatisUtil;

public class History_detail {
	SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   
    SqlSession sqlSession = sqlSessionFactory.openSession();  
   Answer_inter answer_inter=sqlSession.getMapper(Answer_inter.class);
   public  List<Historylist_detail> hisDetail(String username){
	   List<Historylist_detail> his=answer_inter.selectHis_detail(username);
	   for(int i=0;i<his.size();i++){
	   JSONArray json1 = JSONArray.fromObject(his.get(i).getAnswer());
	   his.get(i).setAnswer(json1);
	    
   } return his;
	   }
   public List<Historylist> History(String username){
	   List<Historylist> his=answer_inter.selectHis(username);
	    return his;
   }
}
