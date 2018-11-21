package com.websystique.springmvc.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.websystique.springmvc.CStable.*;
import com.websystique.springmvc.CStable_interface.*;
import com.websystique.springmvc.CStable_interface.Answer_inter;

import com.websystique.springmvc.MybatisUtil.*;
import com.websystique.springmvc.handler.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.math.*;

public class test {
	/** 
     * 获得MyBatis SqlSessionFactory， SqlSessionFactory负责创建SqlSession， 
     * 一旦创建成功，就可以用SqlSession实例来执行映射语句，commit，rollback，close等方法 
     */  
    public static void main(String[] args) throws Exception { 
    	SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
        SqlSession sqlSession = sqlSessionFactory.openSession();  
    Worker_inter worker_inter = sqlSession.getMapper(Worker_inter.class);
    
      
       Administrator_inter administrator_inter=sqlSession.getMapper(Administrator_inter.class);
       Answer_inter answer_inter=sqlSession.getMapper(Answer_inter.class);
       Task_inter task_inter=sqlSession.getMapper(Task_inter.class);
      
  
      answer_inter.deleteAnswer(10, "yang");


      // List<Historylist_detail> hd=answer_inter.selectHis_detail("cao");
       //List<Historylist> history= answer_inter.selectHis("cao");
     
       Answer answer =new Answer();
       answer.setTid(2);
       answer.setUsername("abc");
       answer.setLongitude(110.1);
       answer.setLatitude(88.3);
   //  answer_inter.deleteAnswer(12, "cao");
     
     
      
    
    //   answer_inter.addAnswer(answer);
      
 /*     Worker worker = new Worker();
     
       worker.setUsername("abcd33");  
      worker.setPassword("12345");  
      worker.setPhoneno("8888885");
      Integer addworker = worker_inter.addWorker(worker);  
      System.out.println("addWorker, addworker:" + addworker); 
   

       Worker found = worker_inter.findWorker(worker.getUsername());  
        System.out.println("findWorker:" + found);  */
       /* found.setPassword("123456");  
       boolean updateworker = worker_inter.updateWorker(found);  
        System.out.println("updateWorker, updateworker:" + updateworker);  
       Worker found2 = worker_inter.findWorker( worker.getUsername());  
        System.out.println("findWorker:" + found2);  
  /*判断用户数量
        int count = worker_inter.countWorker();  
        System.out.println("countWorker, count:" + count);  
  将所有条目列出
       List< Worker> list = worker_inter.listWorker();  
       System.out.println("listWorker, list:" + list);  
  删除用户
        boolean deleteworker = worker_inter.deleteWorker(worker.getId());  
       System.out.println("deleteWorker:" + deleteworker);  
        
 添加一个管理员
        Administrator administrator= new Administrator();
        administrator.setUsername("zhang");
        administrator.setPassword("1234");
        administrator.setPrivilege("管理员");
        Integer addAdministrator=administrator_inter.addAdministrator(administrator);
        System.out.println("addAdministrator,addAdministrator:"+addAdministrator);
  /*添加一个答案*/   
  /*    String date = "2008-01-29T12:05:10"; // <input type="datetime-local"> 输入参数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); 
        
        Answer answer=new Answer();
        answer.setId(1);
     
        answer.setUsername("1234" );
        answer.setLocation("丁豪广场");
        answer.setPicpath("");
        answer.setAltitude(123.123);
        answer.setLongitude(98.36);
        Map<String, Object> address = new HashMap<String, Object>();
        address.put("country", "china");
        address.put("province", "guangdong");
        address.put("city", "shenzhen");
        
        answer.setAnswer(address);
        try {
        	Date dt = sdf.parse(date);
        	 answer.setTime(new Timestamp(dt.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        Integer addAnswer=answer_inter.addAnswer(answer); 
        //System.out.println("addAnswer, addanswer:" + addAnswer); 
        
        Task task = new Task();
        task.setId(123);
        task.setDescription("asldgjsdok");
        task.setTasktype("空闲教室");
        task.setStatus("正在执行");
        Map<String, Object> address1 = new HashMap<String, Object>();
        address1.put("country", "china");
        address1.put("province", "guangdong");
        address1.put("city", "shenzhen");
        
       
      
        //System.out.println(answer_inter.listAnswer()); 
      
      //  Historylist history=answer_inter.selectHis("1234"); 
        
        double a1=110,a2=110;
        double b1=110,b2=111;
        Location location=new Location();
        
        Task task2 = new Task();
        task2.setId(6);
        task2.setDescription("洪楼广场的打折活动2");
        task2.setTasktype("衣服打折");
        task2.setStatus("正在执行");
        task2.setLocation("洪楼广场");
        task2.setAnswercount(100);
        task2.setLongitude(117.073);
        task2.setAltitude(36.69);
        //将question以json的格式存入数据库
      Map<String, Object> question1 = new HashMap<String, Object>();
         question1.put("qid","q1" );
         question1.put("description", "打折商品的种类");
      List<Map<String, Object>> lable= new ArrayList<Map<String, Object>>();
      Map<String,Object> lable1=new HashMap <String,Object>(); 
      Map<String,Object> lable2=new HashMap <String,Object>(); 
      Map<String,Object> lable3=new HashMap <String,Object>(); 
      	lable1.put("labledescription", "种类多");
      	lable1.put("lableid", 1);
        lable2.put("labledescription", "种类一般");
        lable2.put("lableid", 2);
        lable3.put("labledescription", "种类少");
        lable3.put("lableid", 3);
        lable.add(lable1);
        lable.add(lable2);
        lable.add(lable3);
        question1.put("label", lable);
        Map<String, Object> question2 = new HashMap<String, Object>();
        question2.put("qid","q2" );
        question2.put("description", "打折的力度如何");
        List<Map<String, Object>> lablee= new ArrayList<Map<String, Object>>();
        Map<String,Object> lablee1=new HashMap <String,Object>(); 
        Map<String,Object> lablee2=new HashMap <String,Object>(); 
        Map<String,Object> lablee3=new HashMap <String,Object>();
    
     lablee1.put("labledescription", "力度很大");
     lablee1.put("lableid", 1);
     lablee2.put("labledescription", "力度一般");
     lablee2.put("lableid", 2);
     lablee3.put("labledescription", "力度小");
     lablee3.put("lableid", 3);
     lablee.add(lablee1);
     lablee.add(lablee2);
     lablee.add(lablee3);
       question2.put("label", lablee);
       List<Map<String, Object>> questionlist=new ArrayList<Map<String, Object>>();
       questionlist.add(question1);
       questionlist.add(question2);
        task2.setQuestion(questionlist); */
 // task_inter.addTask(task2);
   
        
     //task_inter.deleteTask(1);
              
        //System.out.println(worker_inter.listWorker());   
        
   sqlSession.commit();
	sqlSession.close();
    }  
}
