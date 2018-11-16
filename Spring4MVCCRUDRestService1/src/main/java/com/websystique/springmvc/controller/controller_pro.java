package com.websystique.springmvc.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Base64Utils;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.util.UriComponentsBuilder;




import com.websystique.springmvc.CStable.*;
import com.websystique.springmvc.CStable_interface.*;
import com.websystique.springmvc.MybatisUtil.MybatisUtil;

import com.websystique.springmvc.request.question;
import com.websystique.springmvc.request.*;
import com.websystique.springmvc.responseform.*;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;






@RestController
public class controller_pro {
	 int aid;
 
 //--------------vue端----------------------------------------------------
	//---获取所有的worker列表---
  @RequestMapping(value = "/worker/", method = RequestMethod.GET)
	public ResponseEntity<List<Worker>> listAllusers() {
		SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   
        SqlSession sqlSession = sqlSessionFactory.openSession();  
       Worker_inter worker_inter = sqlSession.getMapper(Worker_inter.class);  
		List<Worker> workers=worker_inter.listWorker();
		if(workers.isEmpty()){
			return new ResponseEntity<List<Worker>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Worker>>(workers, HttpStatus.OK);
	} 
  @RequestMapping(value = "/worker/personinfo", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getUser(@RequestBody Worker user) {
		SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
    SqlSession sqlSession = sqlSessionFactory.openSession();  //从工厂中获取sqlSession，也就是获取cof1.xml中的映射内容
   Worker_inter worker_inter = sqlSession.getMapper(Worker_inter.class); 
   Worker worker=worker_inter.findWorker(user.getUsername());
   Result result=new Result();
  if (worker == null) {
			System.out.println("用户" +user.getUsername() + " 不存在");
			result.setState(2);
			result.setMessage(null);
			result.setError("not found");
			return new ResponseEntity<Result>(result,HttpStatus.OK);
		}
  	result.setState(1);
  	JSONArray json = JSONArray.fromObject(worker); 
  	result.setMessage(json);
  	return new ResponseEntity<Result>(result, HttpStatus.OK);
		//找到个人信息并且返回
	
  }
//---登录---
	
		@RequestMapping(value = "/worker/login", method = RequestMethod.PUT)
		public ResponseEntity<Result> register(@RequestBody Worker user, 	UriComponentsBuilder ucBuilder) {
			System.out.println("正在查找" + user.getUsername());
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
	        SqlSession sqlSession = sqlSessionFactory.openSession();  //从工厂中获取sqlSession，也就是获取cof1.xml中的映射内容
			Worker_inter worker_inter = sqlSession.getMapper(Worker_inter.class);
			Worker worker=worker_inter.findWorker(user.getUsername());
			Result result=new Result();
			if (worker==null) {
				System.out.println( "用户"+user.getUsername() + "不存在,登录失败");
				result.setState(2);
				result.setError("该用户名不存在");
				return new ResponseEntity<Result>(result, HttpStatus.OK);
			}
			String password=user.getPassword();
			System.out.println(password);
			if(worker.getPassword().equals(password)) {
				   System.out.println("登录成功，得到用户信息:"+worker);
				   result.setState(1);
				   JSONArray json = JSONArray.fromObject(worker); 
				   result.setMessage(json);
				   return new ResponseEntity<Result>(result, HttpStatus.OK);
			   }
			else {
			 result.setState(3);
			 result.setError("wrong password");
			System.out.println("密码错误，登录失败");
			return new ResponseEntity<Result>(result, HttpStatus.OK);
			}
		}
		
		
		//-------------------注册--------------------------------------------------------
		
	@RequestMapping(value = "/worker/register", method = RequestMethod.POST)
		public ResponseEntity<Result> createUser(@RequestBody Worker user, 	UriComponentsBuilder ucBuilder) {
			System.out.println("正在查找" + user.getUsername());
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
	        SqlSession sqlSession = sqlSessionFactory.openSession();  
			Worker_inter worker_inter = sqlSession.getMapper(Worker_inter.class);
	       Worker worker=worker_inter.findWorker(user.getUsername());
	       Result result=new Result();
			if (worker!=null) {
				System.out.println("用户 " + user.getUsername() + "已经存在");
				result.setState(2);
				result.setError("该用户已经存在");
				return new ResponseEntity<Result>(result, HttpStatus.OK);
			}
			
			Worker worker2 = new Worker();
		    worker2.setUsername(user.getUsername());  
		    worker2.setPassword(user.getPassword());  
		    worker2.setPhoneno(user.getPhoneno());
		    worker2.setReward(user.getReward());
		    System.out.println(worker2);
		    Integer addworker = worker_inter.addWorker(worker2);
		    result.setState(1);
			   JSONArray json = JSONArray.fromObject(user); 
			   result.setMessage(json);
		   
			
		    System.out.println("添加用户:" + worker2);   
		
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/worker/{username}").buildAndExpand(user.getUsername()).toUri());
			sqlSession.commit();  
			sqlSession.close();
			if(addworker==1) 
				System.out.println("注册成功");
				return new ResponseEntity<Result>(result, HttpStatus.OK);
	
			}
	//---获取所有任务列表----
		@RequestMapping(value = "/task/", method = RequestMethod.GET)
		public ResponseEntity<JSONArray> listtasks() {
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   
	        SqlSession sqlSession = sqlSessionFactory.openSession();  
	       Newtask_inter worker_inter = sqlSession.getMapper(Newtask_inter.class);  
			List<Newtask> workers=worker_inter.listTask();
			JSONArray json= JSONArray.fromObject(workers);
		 
			return new ResponseEntity<JSONArray>(json,HttpStatus.OK);
		} 
			
//----history 1--
		@RequestMapping(value = "/history/sketch", method = RequestMethod.PUT)
		public ResponseEntity<Result_history> historysketch(@RequestBody Answer user,HttpServletResponse response) {
			System.out.println("正在根据用户名查询个人历史记录");
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   
	        SqlSession sqlSession = sqlSessionFactory.openSession();  
	        Answer_inter answer_inter=sqlSession.getMapper(Answer_inter.class);
	        Task_inter task_inter=sqlSession.getMapper(Task_inter.class);
	        List<Historylist> history= answer_inter.selectHis(user.getUsername());
	        List<Answer> answer=answer_inter.findAnswer_username(user.getUsername());
			Result_history result=new Result_history();
			if(history==null) {
				result.setState(0);
				result.setError("该用户不存在");
				 
				return new ResponseEntity<Result_history>(result, HttpStatus.OK);
			}
			result.setState(1);
			List<h_msg> lh=new ArrayList<h_msg>();
			for(int i=0;i<answer.size();i++)
			{	
				Task task=task_inter.findTask(answer.get(i).getTid());
				System.out.println(task);
				h_msg m=new h_msg();
				m.setTid(answer.get(i).getTid());
				
				m.setLocation(answer.get(i).getLocation());
				DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String tsStr = sdf.format(answer.get(i).getTime()); 
				m.setTime(tsStr);
				m.setTitle(task.getDescription());
				m.setIcon("http://211.87.239.31:8080/Spring4MVCCRUDRestService/image/"+user.getUsername()+"/"+answer.get(i).getTid()+"/"+answer.get(i).getAid()+"/"+0);
				lh.add(m);
				  
			}
		System.out.println("输出的结果是"+lh);
			result.setMessage(lh);
			 
			return new ResponseEntity<Result_history>(result, HttpStatus.OK);
		} 
			
//---history2
		@RequestMapping(value = "/history/detail", method = RequestMethod.PUT)
		public ResponseEntity<history_sketch> historydetail(@RequestBody Answer user) {
			System.out.println("这里开始的是任务详情页面");
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   
	        SqlSession sqlSession = sqlSessionFactory.openSession();  
	        Answer_inter answer_inter=sqlSession.getMapper(Answer_inter.class);
	        List<Historylist_detail> hd=answer_inter.selectHis_detail(user.getUsername());
	        List<Answer> ha=answer_inter.findAnswer(user.getTid(), user.getUsername());
			history_sketch result=new history_sketch();
			System.out.println(ha);
			
		try {	if(ha==null) {
				result.setState(0);
				result.setError("该用户不存在");
				 
				return new ResponseEntity<history_sketch>(result, HttpStatus.OK);
		}
			result.setState(1);
		
				List<List<Map<String, Object>>>l= new ArrayList<List<Map<String, Object>>>();
			List<Map<String, Object>> lableinfo= new ArrayList<Map<String, Object>>();
			List<Map<String,Object>> lablepic=new ArrayList<Map<String,Object>>();
			 
			  for(int i=0;i<ha.size();i++) {
				  JSONArray json=JSONArray.fromObject(ha.get(i).getAnswer());
				  for(int j=0;j<json.size();j++)
				  {
					  JSONObject ob=json.getJSONObject(j);
					  Map<String,Object> lable=new HashMap<String,Object>();
					  lable.put("description", ob.getString("description"));
					  lable.put("labeldescription", ob.getString("labeldescription"));
					  lableinfo.add(lable);
				  }  
			  }
			  for(int i=0;i<ha.size();i++)
				{
				  JSONArray photojson=JSONArray.fromObject(ha.get(i).getPicpath());
				  for(int j=0;j<photojson.size();j++) {
					  Map<String,Object> lb=new HashMap<String,Object>();
					  lb.put("icon", "http://211.87.239.31:8080/Spring4MVCCRUDRestService/image/"+user.getUsername()+"/"+user.getTid()+"/"+ha.get(i).getAid()+"/"+j); 
					  lablepic.add(lb);
				  } 
				}
				 
				l.add(lableinfo);
				l.add(lablepic);
			result.setMessage(l); 
			return new ResponseEntity<history_sketch>(result, HttpStatus.OK);
			}catch(Exception e){
			List<List<Map<String, Object>>>l= new ArrayList<List<Map<String, Object>>>();
		List<Map<String, Object>> lableinfo= new ArrayList<Map<String, Object>>();
		List<Map<String,Object>> lablepic=new ArrayList<Map<String,Object>>();
	
		for(int i=0;i<ha.size();i++) {
			String str=ha.get(i).getAnswer().toString().replaceAll("\\\\", "");
			String abc=str.replaceFirst("\"", "");
			
			int a=abc.length();
			String str1=abc.substring(0, a-1);
			System.out.println(str1);
		JSONArray json=JSONArray.fromObject(str1);
		
			  for(int j=0;j<json.size();j++)
			  {
				  JSONObject ob=json.getJSONObject(j);
				  Map<String,Object> lable=new HashMap<String,Object>();
				  lable.put("description", ob.getString("description"));
				  lable.put("labeldescription", ob.getString("labeldescription"));
				  lableinfo.add(lable);
				  System.out.println("从这里开始检查"+ob);
			  }  
		}
		 
		 
		  for(int i=0;i<ha.size();i++)
			{
			  JSONArray photojson=JSONArray.fromObject(ha.get(i).getPicpath());
			  for(int j=0;j<photojson.size();j++) {
				  Map<String,Object> lb=new HashMap<String,Object>();
				  lb.put("icon", "http://211.87.239.31:8080/Spring4MVCCRUDRestService/image/"+user.getUsername()+"/"+user.getTid()+"/"+ha.get(i).getAid()+"/"+j); 
				  lablepic.add(lb);
			  } 
			}
			l.add(lableinfo);
			l.add(lablepic);
		
		
		
		result.setMessage(l);
			result.setState(1);
			
			 
			return new ResponseEntity<history_sketch>(result, HttpStatus.OK);
		}
		} 
//------返回详细答案信息------
		@RequestMapping(value = "/answer/detail", method = RequestMethod.PUT)
		public ResponseEntity<JSONObject> getanswerdetail(@RequestBody JSONObject json) {
			SqlSessionFactory sqlSessionFactory =MybatisUtil.getInstance();
			SqlSession sqlSession=sqlSessionFactory.openSession();
			Answer_inter answer_inter=sqlSession.getMapper(Answer_inter.class);
			Task_inter task_inter=sqlSession.getMapper(Task_inter.class);
			List<Answer> answer=	answer_inter.findAnswer(json.getInt("tid"), json.getString("username"));
			System.out.println(answer);
			Task task= task_inter.findTask(json.getInt("tid"));
			JSONObject result=new JSONObject();
			if(answer==null) {
				result.put("status", 1);
				result.put("error", "no answer");
				return new ResponseEntity<JSONObject>(result,HttpStatus.OK);
			}
			JSONArray jsonarray=new JSONArray();
			for(int i=0;i<answer.size();i++) {
				JSONObject jb=new JSONObject();
				jb.put("aid", answer.get(i).getAid());
				jb.put("tid", answer.get(i).getTid());
				jb.put("answer_longitude", answer.get(i).getLongitude());
				jb.put("answer_latitude", answer.get(i).getLatitude());
				jb.put("answer_location", answer.get(i).getLocation());
				try {
					JSONArray photo=JSONArray.fromObject(answer.get(i).getPicpath());
					JSONArray pic=new JSONArray();
					for(int j=0;j<photo.size();j++) {
						pic.add("http://211.87.239.31:8080/Spring4MVCCRUDRestService/image/"+json.getString("username")+"/"+json.getInt("tid")+"/"+answer.get(i).getAid()+"/"+j);
					}
					jb.put("picpath",pic.toString());
				}catch(Exception e1) {
					jb.put("picpath", "http://211.87.239.31:8080/Spring4MVCCRUDRestService/image/"+json.getString("username")+"/"+json.getInt("tid")+"/"+answer.get(i).getAid()+"/"+0);
				}
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				jb.put("answertime",sdf.format(answer.get(i).getTime()) );
				jb.put("question", task.getQuestion());
				jb.put("task_description", task.getDescription());
				jb.put("task_location", task.getLocation());
				jb.put("task_longitude", task.getLongitude());
				jb.put("task_latitude", task.getLatitude());
				jb.put("task_deadline", sdf.format(task.getDeadline()));
				jb.put("task_publictime", sdf.format(task.getPublictime()));
				jb.put("task_status", task.getStatus());
				jb.put("task_tasktype", task.getTasktype());
				jb.put("task_answercount", task.getAnswercount());
				
				try {
					String str=answer.get(i).getAnswer().toString();
					String abc=str.replaceAll("\\\\", "");
					String str1=abc.substring(1, abc.length()-1);
					JSONArray ja=JSONArray.fromObject(str1);
					jb.put("answer", ja);
				}catch(Exception e) {
					String str=answer.get(i).getAnswer().toString();
					str.replaceAll("\\\\", "");
					
					JSONArray ja=JSONArray.fromObject(str);
					jb.put("answer", ja);
				}
				
				
				jsonarray.add(jb);
			
			}
			result.put("status", 0);
			result.put("message", jsonarray);
			return new ResponseEntity<JSONObject>(result,HttpStatus.OK);
			
			
		} 
//----用来手动添加 答案信息
		@RequestMapping(value = "/answer/submit", method = RequestMethod.POST)
		public ResponseEntity<Result_answer_one> submitanswer(@RequestBody Answer answer, UriComponentsBuilder ucBuilder) {
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
	        SqlSession sqlSession = sqlSessionFactory.openSession();  
			Answer_inter worker_inter = sqlSession.getMapper(Answer_inter.class);
			int i=worker_inter.addAnswer(answer);
			
			
			Result_answer_one result=new Result_answer_one();
			if(i==1) {
				sqlSession.commit();
				sqlSession.close();
				result.setState(1);
				
		    	result.setMessage(answer);
				
		    	return new ResponseEntity<Result_answer_one>(result, HttpStatus.OK);
			}
			result.setState(2);
			result.setError("提交答案失败");
			return new ResponseEntity<Result_answer_one>(result, HttpStatus.OK);
			}
			
//---更新
		@RequestMapping(value = "/worker/update", method = RequestMethod.POST)
		public ResponseEntity<Result> updateUser(@RequestBody Worker user, 	UriComponentsBuilder ucBuilder) {
			System.out.println("正在查找" + user.getUsername());
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
	        SqlSession sqlSession = sqlSessionFactory.openSession();  
			Worker_inter worker_inter = sqlSession.getMapper(Worker_inter.class);
	       Worker worker=worker_inter.findWorker(user.getUsername());
	       Result result=new Result();
			if (worker==null) {
				System.out.println("用户 " + user.getUsername() + "不存在，不可以进行更新");
				result.setState(2);
				result.setError("用户不存在，更新失败");
				return new ResponseEntity<Result>(result, HttpStatus.OK);
			}
			result.setState(1);
		    worker.setUsername(user.getUsername());  
		    worker.setPassword(user.getPassword());  
		    worker.setPhoneno(user.getPhoneno());
		    worker.setReward(user.getReward());
		    worker.setSex(user.getSex());
		    worker.setAge(user.getAge());
		    worker.setArea(user.getArea());
		    worker.setSkill(user.getSkill());
		    System.out.println(worker);
		    Boolean addworker = worker_inter.updateWorker(worker);
		    JSONArray json = JSONArray.fromObject(worker); 
			   result.setMessage(json);
		    System.out.println("更新用户信息:" + worker);   
		
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/worker/register").buildAndExpand(user.getUsername()).toUri());
			
			if(addworker)
				{
				sqlSession.commit();  
				sqlSession.close();
				System.out.println("更新成功");
				return new ResponseEntity<Result>(result, HttpStatus.OK);
				}
			result.setState(3);
			result.setError("输入格式错误，更新失败");
			result.setMessage(null);
			return new ResponseEntity<Result>(result, HttpStatus.OK);
	
			}
//----------修正版本3-28
	//----根据经纬度返回任务列表的详细信息---
				@RequestMapping(value="/task/query", method = RequestMethod.PUT)
				public ResponseEntity<Result_task> gettasks(@RequestBody Location location,UriComponentsBuilder ucBuilder){
					
			    List<Task> t=location.findtask(location.getLongitude(), location.getLatitude());
			    Result_task result=new Result_task();    
			    if(t.isEmpty()) {
						System.out.println("未找到任务");
						result.setState(2);
						result.setError("未找到任务");
						return new ResponseEntity<Result_task>(result,HttpStatus.OK);
					}
				    System.out.println("找到任务："+t);
				    result.setState(1);
				    result.setMessage(t);
				    return new ResponseEntity<Result_task>(result,HttpStatus.OK);
				} 
		//----根据经纬度或者信息返回任务列表的详细信息---杨倩师姐
				@RequestMapping(value="/task/querytask", method = RequestMethod.PUT)
				public ResponseEntity<Result_task_other> gettask1(@RequestBody res_task res,UriComponentsBuilder ucBuilder){
					Result_task_other result=new Result_task_other();
					if(res.getFlag()==1) {
					
						double x=res.getMsg().getLongitude();
						double y=res.getMsg().getAltitude();
						
						Location location=new Location();
						List<Task> t=location.findtask(x,y); 
						    
					    if(t.isEmpty()) {
							System.out.println("未找到任务");
							result.setState(2);
							result.setError("未找到任务");
							return new ResponseEntity<Result_task_other>(result,HttpStatus.OK);
						}
					    System.out.println("找到任务："+t);
					    result.setState(1);
					    result.setMessage(result.getmessage(t,x,y));
					    
					    return new ResponseEntity<Result_task_other>(result,HttpStatus.OK);
					}
					
					String l=res.getMsg().getLocation();
					SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
			        SqlSession sqlSession = sqlSessionFactory.openSession();  
					Task_inter task_inter = sqlSession.getMapper(Task_inter.class);
					List<Task> t=task_inter.findTask1(l); 
					    
				    if(t.isEmpty()) {
						System.out.println("未找到任务");
						result.setState(2);
						result.setError("未找到任务");
						return new ResponseEntity<Result_task_other>(result,HttpStatus.OK);
					}
				    System.out.println("找到任务："+t);
				    result.setState(1);
				    result.setMessage(result.getmessage(t));
					return new ResponseEntity<Result_task_other>(result,HttpStatus.OK);
			    
				}
//---经纬度返回任务
				//----根据经纬度返回任务列表的概要---杨倩师姐
				@RequestMapping(value="/task/gettask", method = RequestMethod.PUT)
				public ResponseEntity<Result_task_other> gettask2(@RequestBody task_req res,UriComponentsBuilder ucBuilder){
					 SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
				     SqlSession sqlSession = sqlSessionFactory.openSession();  
					 Answer_inter answer_inter = sqlSession.getMapper(Answer_inter.class);
					 Task_inter task_inter = sqlSession.getMapper(Task_inter.class);
					List<Task> task = task_inter.listTask();
					try{
						for(int i=0;i<task.size();i++)
						{	
							if(task.get(i).getStatus().equals("正在执行"));
							{
								DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
								String tsStr = sdf.format(task.get(i).getDeadline());  
							  
								Date nowtime = new Date();
								long ntime = (long) (nowtime.getTime()); //把当前得到的时间用date.getTime()的方法写成时
								long dtime = (long) (task.get(i).getDeadline().getTime());
								if(ntime>=dtime){
									task.get(i).setStatus("已完成");
									//System.out.println(String.valueOf(task.get(i).getId())+"任务已完成");
								}
								else{
									task.get(i).setStatus("正在执行");
									//System.out.println(String.valueOf(task.get(i).getId())+"任务未完成");
								}
								task_inter.changeStatus(task.get(i));
							}							
						}						 
						  sqlSession.commit();
						  sqlSession.close();
					}
					catch (IllegalStateException e) {
				          
			        	e.printStackTrace();
			        }
					
					Result_task_other result=new Result_task_other();
						double x=res.getLongitude();
						double y=res.getLatitude();
						System.out.println("receive"+x);
						System.out.println("receive"+y);
						
						Location location=new Location();
						List<Task> t=location.findtask(x,y); 
						  
					    if(t.isEmpty()) {
							System.out.println("未找到任务");
							result.setState(2);
							result.setError("未找到任务");
							return new ResponseEntity<Result_task_other>(result,HttpStatus.OK);
						}
					    System.out.println("找到任务："+t);
					    result.setState(1);
					   
					    result.setMessage(result.getmessage(t,x,y));
					    
					    return new ResponseEntity<Result_task_other>(result,HttpStatus.OK);
					}
					
				 		
//---------接受任务第一阶段请求------返回任务信息-----曹
				@RequestMapping(value="/task/accepttask", method = RequestMethod.PUT)
				public ResponseEntity<Result_cao> accepttask(@RequestBody res_tid tid,UriComponentsBuilder ucBuilder){
					System.out.println("现在执行选取任务操作");
					SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
			        SqlSession sqlSession = sqlSessionFactory.openSession();  
					Task_inter task_inter = sqlSession.getMapper(Task_inter.class);
					Task task=task_inter.findTask(tid.getTid());
					System.out.println(tid.getTid());
					System.out.println(task);
					Result_cao result =new Result_cao();
					if(task==null) {
						result.setState(2);
						result.setError("查找过程出错");
						return new ResponseEntity<Result_cao>(result,HttpStatus.OK);
					}
					result.setState(1);
					//result.setMessage(task.getDescription(), task.getQuestion())
				msg_cao msg=new msg_cao();
				msg.setDescription(task.getDescription());
				msg.setQuestion(task.getQuestion());
				result.setMessage(msg);
				System.out.println(msg);
					return new ResponseEntity<Result_cao>(result,HttpStatus.OK);
					
				}
				
//---第三阶段文件上传dataform方式----
				    @RequestMapping(value = "/savephoto", method = RequestMethod.POST)
				    public void savephoto(@RequestParam(value="username")String username,
				    		@RequestParam(value="tid")int tid,
				    		@RequestParam(value="file",required=false) CommonsMultipartFile[] file,
				    		HttpServletRequest request,HttpServletResponse response) throws IOException {
				    		SqlSessionFactory sqlSessionFactory =MybatisUtil.getInstance();
				    		SqlSession sqlSession =sqlSessionFactory.openSession();
				    		Task_inter task_inter = sqlSession.getMapper(Task_inter.class);
				    		Task task=task_inter.findTask(tid);
				    	System.out.println(username);
				    	
				    	
				    	JSONArray jsonpath=new JSONArray();
				           String resMsg = "";
				        try {
				        	long  startTime=System.currentTimeMillis();         
				            for(int i=0;i<file.length;i++)
				            {
				            	String path="";
				            	System.out.println("fileName："+file[i].getOriginalFilename());
				            	path="E:/photofile/newfile/"+username+System.currentTimeMillis()+file[i].getOriginalFilename();
				            	jsonpath.add(path);
				            	System.out.println("path:" + path);//地址采用 用户名+时间+原文件名的格式存储
				            	File newFile=new File(path);
				            //通过CommonsMultipartFile的方法直接写文件
				            file[i].transferTo(newFile);
				            long  endTime=System.currentTimeMillis();
				            System.out.println("运行时间："+String.valueOf(endTime-startTime)+"ms");
				            resMsg = "1";
				            } 
					       Answer_inter answer_inter = sqlSession.getMapper(Answer_inter.class);
					       Answer answer=answer_inter.findAnswer_aid(answer_inter.listAnswer().size());
					       answer.setPicpath(jsonpath.toString());
					       answer_inter.updateAnswer(answer);
					       sqlSession.commit();
						   sqlSession.close();
				        }catch (IllegalStateException e) {
				          
				        	e.printStackTrace();
				            resMsg = "0";
				        }
				              response.getWriter().write(resMsg);

				          }
	//---第二阶段答案上传---	
			    @RequestMapping(value="/answer/add", method = RequestMethod.POST)	    
					public ResponseEntity<result_task_add> addanswer(@RequestBody res_answeradd res,UriComponentsBuilder ucBuilder){
						result_task_add result=new result_task_add();
						System.out.println("进行答案上传阶段");
			try {
						SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance(); 
				        SqlSession sqlSession = sqlSessionFactory.openSession();  
				        
				        List<question> ql=new ArrayList<question>();
				        for(int i=0;i<res.getQuestion().size();i++)
				        {
				        	question q=new question();
				        	q.setQid(res.getQuestion().get(i).getQid());
				        	q.setDescription(res.getQuestion().get(i).getDescription());
				        	q.setLabelid(res.getQuestion().get(i).getLabelid());
				        	q.setLabeldescription(res.getQuestion().get(i).getLabeldescription());
						    ql.add(q);
				        }
				      Answer_inter answer_inter = sqlSession.getMapper(Answer_inter.class);
				      Task_inter task_inter = sqlSession.getMapper(Task_inter.class);
				      Task task=task_inter.findTask(res.getTid());
				      if(task.getStatus().equals("已完成"))
				      {
				    	  result.setState(2);
				    	  System.out.println("任务TID"+String.valueOf(res.getTid()));
				      }
				      else{
				    	  int count=  answer_inter.listAnswer().size();
					       Answer answer=new Answer();
					       answer.setUsername(res.getUsername());
					       answer.setLatitude(res.getLatitude());
					       answer.setLongitude(res.getLongitude());
					       answer.setLocation(res.getLocation());
					       answer.setTime(new Timestamp(System.currentTimeMillis()));
					       answer.setAnswer(ql);
					       answer.setAid(count+1);
					       aid=count;
					       answer.setTid(res.getTid());
					       System.out.println(answer);
					       answer_inter.addAnswer(answer);
					       task.setAnswercount(answer_inter.countAnswer(res.getTid()));
					       task_inter.answerCount(task);
					       sqlSession.commit();  
					       sqlSession.close(); 
					       result.setState(1);
					       System.out.println("else running"+String.valueOf(res.getTid()));
				      }
				      return new ResponseEntity<result_task_add>(result,HttpStatus.OK);
						}catch(Exception e) {
						result.setState(0);
					result.setError("error");
					return new ResponseEntity<result_task_add>(result,HttpStatus.OK);
					}
					}
//-------查询是否有活跃的调查问卷------
		@RequestMapping(value="/question/ask",method=RequestMethod.PUT)
		public ResponseEntity<Res_Questionnaire> getQusetionnaire(@RequestBody JSONObject json){
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
	        SqlSession sqlSession = sqlSessionFactory.openSession();  
			Questionnaire_inter q_inter = sqlSession.getMapper(Questionnaire_inter.class);
			Qnaireanswer_inter answer_inter=sqlSession.getMapper(Qnaireanswer_inter.class);
			List<Questionnaire> message=new ArrayList<Questionnaire>();
			Res_Questionnaire result=new Res_Questionnaire();
			message=q_inter.findNonLinkedIsShow();
			List<Qnaireanswer> msg_a=new ArrayList<Qnaireanswer>();
			msg_a=answer_inter.findAnswer_username(json.getString("username"));
			int[] a=new int[message.size()];
			for(int i=0;i<message.size();i++) 
				a[i]=message.get(i).getQnaireid();
			for(int i=0;i<message.size();i++) {
				for(int j=0;j<msg_a.size();j++)
					if(message.get(i).getQnaireid()==msg_a.get(j).getTid()) {
						a[i]=0;
						break;
					}
			}
			List<Questionnaire> message1=new ArrayList<Questionnaire>();
		
			for(int i=0;i<message.size();i++) {
				if(a[i]!=0) {
					message1.add(message.get(i));
					
				}
			}
			if(message1.size()==0) {

				result.setState(1);
				result.setError("没有查到调查问卷");
				return new ResponseEntity<Res_Questionnaire>(result,HttpStatus.OK);
			
			}
			result.setState(0);
			result.setMessage(result.getQuestionnaire(message1));
			return new ResponseEntity<Res_Questionnaire>(result,HttpStatus.OK);
		
			
		}
//-----按照任务id查询调查问卷-------
				@RequestMapping(value="/question/askone",method=RequestMethod.PUT)
				public ResponseEntity<Res_Questionnaire> askQusetionnaire(@RequestBody JSONObject json){
					SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
			        SqlSession sqlSession = sqlSessionFactory.openSession();  
					Questionnaire_inter q_inter = sqlSession.getMapper(Questionnaire_inter.class);
					Qnaireanswer_inter answer_inter=sqlSession.getMapper(Qnaireanswer_inter.class);
					List<Questionnaire> message=new ArrayList<Questionnaire>();
					Res_Questionnaire result=new Res_Questionnaire();
					message=q_inter.findLinkedIsShow(json.getInt("linkedtaskid"));
					List<Qnaireanswer> msg_a=new ArrayList<Qnaireanswer>();
					msg_a=answer_inter.findAnswer(json.getInt("linkedtaskid"),json.getString("username"));
					int[] a=new int[message.size()];
					for(int i=0;i<message.size();i++) 
						a[i]=message.get(i).getQnaireid();
					for(int i=0;i<message.size();i++) {
						for(int j=0;j<msg_a.size();j++)
							if(message.get(i).getQnaireid()==msg_a.get(j).getTid()) {
								a[i]=0;
								break;
							}
					}
					List<Questionnaire> message1=new ArrayList<Questionnaire>();
				
					for(int i=0;i<message.size();i++) {
						if(a[i]!=0) {
							message1.add(message.get(i));
							
						}
					}
				try {
					if(message1.size()==0) {

						result.setState(1);
						result.setError("没有查到调查问卷");
						return new ResponseEntity<Res_Questionnaire>(result,HttpStatus.OK);
					
					}
					result.setState(0);
					result.setMessage(result.getQuestionnaire(message1));
					return new ResponseEntity<Res_Questionnaire>(result,HttpStatus.OK);
				
				}catch(Exception e){
					result.setState(1);
					result.setError("发生错误");
					return new ResponseEntity<Res_Questionnaire>(result,HttpStatus.OK);
				}
				}
//------添加调查问卷------
		@RequestMapping(value="/question/submit",method=RequestMethod.POST)
		public ResponseEntity<JSONObject> addQuestionnaire(@RequestBody Res_question json){
			JSONObject result=new JSONObject();
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();
			SqlSession sqlSession=sqlSessionFactory.openSession();
			Questionnaire_inter q_inter=sqlSession.getMapper(Questionnaire_inter.class);
			Questionnaire question=new Questionnaire();
			if(json==null) {
				result.put("status", 0);
				result.put("error", "fail");
				return new ResponseEntity<JSONObject>(result,HttpStatus.OK);
			}
			question.setAnswercount(Integer.valueOf(json.getAnswerslength()));
			JSONArray jsonarray=json.getQuestion();
			JSONArray content=new JSONArray();
			for(int i=0;i<jsonarray.size();i++) {
				JSONObject jb=new JSONObject();
				jb.put("qid", "q"+(i+1));
				jb.put("description", jsonarray.getJSONObject(i).getString("value"));
				jb.put("label",jsonarray.getJSONObject(i).getJSONArray("options"));
				content.add(jb);
			}
			question.setContent(content);
			question.setDeadline(Timestamp.valueOf(json.getCompleteDate()));
			question.setDescription(json.getQuestionnaireDataDesc());
			if(json.getQuestionnaireDataShow().equals("no")) {
				question.setIsShow(0);
			}else
				{
				question.setIsShow(1);
				question.setLinkedtaskid(Integer.valueOf(json.getQuestionnaireTaskNum()));
				}
			JSONObject jb=new JSONObject();
			jb=JSONObject.fromObject(json.getQuestionnaireDataAddr());
			question.setLocation(jb.getString("location"));
			question.setLongitude(jb.getDouble("longitude"));
			question.setLatitude(jb.getDouble("latitude"));
			question.setQnaireid(Integer.valueOf(json.getQuestionnaireDataNum()));
			question.setStatus("正在执行");
			q_inter.addQuestionnaire(question);
			System.out.println(question);
			result.put("status", 1);
				
			return new ResponseEntity<JSONObject>(result,HttpStatus.OK);
		}
//-------添加问卷-----
		//-----添加问卷-------
		@RequestMapping(value="/question/addone",method=RequestMethod.POST)
		public ResponseEntity<Result_task_one> addQusetionnaire(@RequestBody JSONObject questionnaireData, UriComponentsBuilder ucBuilder){
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
	        SqlSession sqlSession = sqlSessionFactory.openSession();  
			Questionnaire_inter q_inter = sqlSession.getMapper(Questionnaire_inter.class);
			
			System.out.println(questionnaireData);
			
			Questionnaire qnaire=new Questionnaire();
			int c=q_inter.listQuestionnaire().size();
			qnaire.setQnaireid(++c);
			qnaire.setLinkedtaskid(Integer.parseInt(questionnaireData.getJSONObject("data").getString("questionnaireTaskNum")));
			qnaire.setDescription(questionnaireData.getJSONObject("data").getString("questionnaireDataDesc"));
			qnaire.setLongitude(Double.valueOf(JSONObject.fromObject(questionnaireData.getJSONObject("data").getString("questionnaireDataAddr")).getString("longitude")));
			qnaire.setLatitude(Double.valueOf(JSONObject.fromObject(questionnaireData.getJSONObject("data").getString("questionnaireDataAddr")).getString("latitude")));
			qnaire.setLocation(JSONObject.fromObject(questionnaireData.getJSONObject("data").getString("questionnaireDataAddr")).getString("location"));
			String isshow=questionnaireData.getJSONObject("data").getString("questionnaireDataShow");
			if(isshow.equals("yes")){
				qnaire.setIsShow(1);
			}else{
				qnaire.setIsShow(0);
			}
			qnaire.setPublictime(Timestamp.valueOf(questionnaireData.getJSONObject("data").getString("publishDate")));
			qnaire.setDeadline(Timestamp.valueOf(questionnaireData.getJSONObject("data").getString("completeDate")));
			qnaire.setAnswercount(questionnaireData.getJSONObject("data").getInt("answersLength"));
			JSONArray question=new JSONArray();
			int questionsize=questionnaireData.getJSONObject("data").getJSONArray("questions").size();
			for(int i=0;i<questionsize;i++) {
				JSONObject jb=questionnaireData.getJSONObject("data").getJSONArray("questions").getJSONObject(i);
				JSONObject que=new JSONObject();
				
				que.put("description", jb.getString("value"));
				
			//	System.out.println(jba);
				List<String> options=(List<String>)(List)(jb.get("options"));
				JSONArray lable=new JSONArray();
				for(int m=0;m<options.size();m++) {
					JSONObject a=new JSONObject();
					a.put("labledescription", options.get(m));
					a.put("lableid", (m+1));
					lable.add(a);
				}
				que.put("label", lable);
				que.put("qid", "q"+(i+1));
				question.add(que);
			}
			qnaire.setContent(question);
			int j=q_inter.addQuestionnaire(qnaire);
			Result_task_one result=new Result_task_one();
			if(j==1) {
				sqlSession.commit();
				sqlSession.close();
				result.setStatus(0);
				
		    	result.setMessage("success");
		    	System.out.println("添加问卷成功");
		    	result.setLength(1);
				
		    	return new ResponseEntity<Result_task_one>(result, HttpStatus.OK);
			}
			result.setStatus(2);
			result.setError("error desc");
			result.setLength(0);
			return new ResponseEntity<Result_task_one>(result, HttpStatus.OK);
			}

				
//----提交问卷答案----
		
	    @RequestMapping(value="/question/answer", method = RequestMethod.POST)	    
			public ResponseEntity<result_task_add> submitQueAnswer(@RequestBody JSONObject res,UriComponentsBuilder ucBuilder){
				result_task_add result=new result_task_add();
				System.out.println("进行答案上传阶段");
				SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
		        SqlSession sqlSession = sqlSessionFactory.openSession(); 
		        Questionnaire_inter q_inter=sqlSession.getMapper(Questionnaire_inter.class);
		        Qnaireanswer_inter answer_inter=sqlSession.getMapper(Qnaireanswer_inter.class);
		        JSONArray ql=new JSONArray();

		        for(int i=0;i<res.getJSONArray("answer").size();i++)
		        {
		        	JSONObject q=res.getJSONArray("answer").getJSONObject(i);
		      /*  	question q=new question();
		        	q.setQid("q"+(i+1));
		        	q.setDescription(res.getJSONArray("answer").getJSONObject(i).getString("description"));
		        	q.setLabelid(res.getJSONArray("answer").getJSONObject(i).getInt("labelid"));
		        	q.setLabeldescription(res.getJSONArray("answer").getJSONObject(i).getString("labeldescription"));
			*/	    ql.add(q);
		        }
		        System.out.println(ql);
		      
		   int count=  answer_inter.listAnswer().size();
		   
		        Qnaireanswer answer=new Qnaireanswer();
		       answer.setUsername(res.getString("username"));
		       answer.setLatitude(res.getDouble("latitude"));
		       answer.setLongitude(res.getDouble("longitude"));
		       answer.setLocation(res.getString("location"));
		       answer.setTime(new Timestamp(System.currentTimeMillis()));
		       answer.setQnanswer(ql);
		       answer.setAid(count+1);
		       answer.setTid(res.getInt("qnaireid"));
		       System.out.println(answer);
		       answer_inter.addQnireanswer(answer);
		        sqlSession.commit();  
				sqlSession.close(); 
				result.setState(0);
				    return new ResponseEntity<result_task_add>(result,HttpStatus.OK);
				}
//-----显示调查问卷的答案---
			@RequestMapping(value="/question/answer",method =RequestMethod.PUT)
			public ResponseEntity<JSONObject> getanswer(@RequestBody JSONObject jsonobject){
				JSONObject jb=new JSONObject();
				List<Qnaireanswer> result =new ArrayList<Qnaireanswer>();
				SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
		        SqlSession sqlSession = sqlSessionFactory.openSession(); 
		        Questionnaire_inter q_inter=sqlSession.getMapper(Questionnaire_inter.class);
		        Qnaireanswer_inter answer_inter=sqlSession.getMapper(Qnaireanswer_inter.class);
		        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		        List<String> time=new ArrayList<String>();
		        try {
		        	result =answer_inter.findAnswer(jsonobject.getInt("tid"), jsonobject.getString("username"));
		        	
		        	if(result==null) {
		        		jb.put("state", 0);
		        		jb.put("error", "答案是空的");
		        	
		        	}else {
		        		jb.put("state", 1);
		        		for(int i=0;i<result.size();i++)
		        		{	
		        			
		        			result.get(i).setQnanswer(JSONObject.fromObject(result.get(i).getQnanswer()).getJSONArray("value"));
		        			time.add( sdf.format(result.get(i).getTime()));
		        	
		        		}
		        		jb.put("time", time);
			        	jb.put("message", result);
			        	
			        	
		        	}
		        	return new ResponseEntity<JSONObject>(jb,HttpStatus.OK);
		        }catch(Exception e) {
		        	result=answer_inter.findAnswer_username(jsonobject.getString("username"));
		        	if(result==null) {
		        		jb.put("state", 0);
		        		jb.put("error", "答案是空的");
		        	
		        	}else {
		        		jb.put("state", 1);
		        		for(int i=0;i<result.size();i++)
		        		{
		        			
		        			result.get(i).setQnanswer(JSONArray.fromObject(JSONObject.fromObject(result.get(i).getQnanswer()).getString("value")));
		        			time.add( sdf.format(result.get(i).getTime()));
		        		}
		      
		        		jb.put("time", time);
			        	jb.put("message", result);
			        	
		        	}
		        	return new ResponseEntity<JSONObject>(jb,HttpStatus.OK);	
		        }
			}
		        
				
		

//----------------下面是管理系统端-------------------------------------------
//-------------------登陆--------------------------------------------------------
		
		@RequestMapping(value = "/auth/login", method = RequestMethod.PUT)
			public ResponseEntity<Responsejson> login(@RequestBody Administrator user) {
			Responsejson res=new Responsejson();	
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
		        SqlSession sqlSession = sqlSessionFactory.openSession();  
				Administrator_inter administrator_inter = sqlSession.getMapper(Administrator_inter.class);
		       Administrator worker=administrator_inter.findAdministrator(user.getUsername());
		       
				if (worker==null) {
					System.out.println("用户 " + user.getUsername() + "不存在");
					res.setStatus(1);
					res.setUsername(null);
					res.setError(1);
					return new ResponseEntity<Responsejson>(res, HttpStatus.OK);
				}
				if(user.getPassword().equals(worker.getPassword())) {
					System.out.println("登陆成功");
					res.setStatus(0);
					res.setUsername(user.getUsername());
					res.setError(0);
					return new ResponseEntity<Responsejson>(res, HttpStatus.OK);
				}	
			System.out.println("登录失败");
				res.setStatus(1);
				res.setError(1);
				
				return new ResponseEntity<Responsejson>(res, HttpStatus.OK);
		}
//--------注册一个管理员---------
		@RequestMapping(value = "/admin/registor", method = RequestMethod.POST)
		public ResponseEntity<res_registor> Administratorregister(@RequestBody Administrator user, 	UriComponentsBuilder ucBuilder) {
			System.out.println("正在查找" + user.getUsername());
			SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
	        SqlSession sqlSession = sqlSessionFactory.openSession();  
			Administrator_inter worker_inter = sqlSession.getMapper(Administrator_inter.class);
	       Administrator worker=worker_inter.findAdministrator(user.getUsername());
	       System.out.println(worker);
	       res_registor result=new res_registor();
			if (worker!=null) {
				System.out.println("用户 " + user.getUsername() + "已经存在");
				result.setStatus(1);
				result.setError("该用户已经存在");
				return new ResponseEntity<res_registor>(result, HttpStatus.OK);
			}
			
		    System.out.println(user);
		    Administrator worker2=new Administrator();
		    worker2.setUsername(user.getUsername());
		    worker2.setPassword(user.getPassword());
		    worker2.setPrivilege(user.getPrivilege());
		    worker_inter.addAdministrator(worker2);
		    result.setStatus(0);
			sqlSession.commit();  
			sqlSession.close();
			System.out.println("注册成功");
				return new ResponseEntity<res_registor>(result, HttpStatus.OK);
			
		}
		
//-------查询所有的answer	-----
		@RequestMapping(value = "/answer/all", method = RequestMethod.GET)
			public ResponseEntity<Result_answer> allanswer() {
				SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   
		        SqlSession sqlSession = sqlSessionFactory.openSession();  
		       Answer_inter answer_inter = sqlSession.getMapper(Answer_inter.class);  
		       Task_inter task_inter=sqlSession.getMapper(Task_inter.class);
		       List<Answer> answer=answer_inter.listAnswer();
				Result_answer result =new Result_answer();
				System.out.println("查找所有的answer");
				if(answer.isEmpty()){
					result.setStatus(1);
					result.setError("error");
					return new ResponseEntity<Result_answer>(result,HttpStatus.OK);//You many decide to return HttpStatus.NOT_FOUND
				}
				
				List<Answer_data> dataList=new ArrayList<Answer_data>();
				for(int i=0;i<answer.size();i++)
				{	
					Answer a=answer.get(i);
					if(a.getAnswer()==null) {
						continue;
					}
					try {
					int m=answer.get(i).getTid();//获取到任务id
					Task task=task_inter.findTask(m);
					Answer_data data=new Answer_data();
					String s1=Integer.toString(m);//把任务id准换成字符串
					data.setT_id(s1);
					data.setU_id(answer.get(i).getUsername());
					data.setQuestion(task.getDescription());
					data.setAnswer_desc("选项型");
					data.setTask_type(task.getTasktype());
					data.setPublish_place(answer.get(i).getLocation());
					data.setPic("http://211.87.239.31:8080/Spring4MVCCRUDRestService/image/"+answer.get(i).getUsername()+"/"+answer.get(i).getTid());
					DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					String tsStr = sdf.format(task.getDeadline());  
					data.setAnswer_time(tsStr);
					
					data.setAnswer(JSONArray.fromObject(answer.get(i).getAnswer()));
					System.out.println(data);
					
					dataList.add(data);
					}
					catch(Exception e) {
						System.out.println("exception");
						continue;
					}
					
				}
				result.setStatus(0);
				result.setData(dataList);
				result.setMessage("success");
				return new ResponseEntity<Result_answer>(result, HttpStatus.OK);
			} 
//----添加任务------
			SimpleDateFormat pubtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			@RequestMapping(value = "/task/addone", method = RequestMethod.POST)
			public ResponseEntity<Result_task_one> submittask(@RequestBody res ret, UriComponentsBuilder ucBuilder) {
				SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   //建立sqlSession的工厂，sqlSessionFactory中是对cof1.xml文件进行处理的
		        SqlSession sqlSession = sqlSessionFactory.openSession();  
				Task_inter worker_inter = sqlSession.getMapper(Task_inter.class);
				Task task=new Task();
				System.out.println(ret);
				int c=worker_inter.listTask().size();
				task.setId(++c);
				task.setDescription(ret.getData().getTaskDesc());
				task.setStatus("正在执行");
				task.setAnswercount(ret.getData().getAnswersLength());
				task.setTasktype(ret.getData().getTaskName());
				task.setDescription(ret.getData().getTaskDesc());
				task.setAnswersnum(ret.getData().getAnswersNum());
				JSONObject lo=JSONObject.fromObject(ret.getData().getTaskAddr());
				String [] loc = lo.getString("location").split(",");
				task.setLocation(lo.getString("name"));
				task.setLatitude(Double.parseDouble(loc[1]) );
				task.setLongitude(Double.parseDouble(loc[0]));
				task.setPublictime(Timestamp.valueOf(pubtime.format(new Date())) );
				
				Date dtime = new Date();
				System.out.println(dtime.toString());
				try {
					long deadtime = (long) (dtime.getTime() + Integer.parseInt(ret.getData().getTaskTime()) * 60 * 60 * 1000); //把当前得到的时间用date.getTime()的方法写成时
					dtime = new Date(deadtime);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Timestamp deadline = Timestamp.valueOf(pubtime.format(dtime));
				task.setDeadline(deadline);
				JSONArray question=new JSONArray();
				
				for(int i=0;i<ret.getData().getQuestions().size();i++) {
					JSONObject jb=new JSONObject();
					jb=ret.getData().getQuestions().getJSONObject(i);
					JSONObject que=new JSONObject();
					
					que.put("description", jb.getString("value"));
					List<String> jba=new ArrayList<String>();
					jba=(List<String>)jb.get("options");
				
				//	System.out.println(jba);
					JSONArray lable=new JSONArray();
					for(int m=0;m<jba.size();m++) {
						JSONObject a=new JSONObject();
						a.put("labledescription", jba.get(m));
						a.put("lableid", (m+1));
						lable.add(a);
					}
					que.put("label", lable);
					que.put("qid", "q"+(i+1));
					question.add(que);
				}
				
				task.setQuestion(question);
				System.out.println(task);
				int j=worker_inter.addTask(task);
				
				Result_task_one result=new Result_task_one();
				if(j==1) {
					sqlSession.commit();
					sqlSession.close();
					result.setStatus(0);
					
			    	result.setMessage("success");
			    	System.out.println("添加任务成功");
			    	result.setLength(1);
					
			    	return new ResponseEntity<Result_task_one>(result, HttpStatus.OK);
				}
				result.setStatus(2);
				result.setError("error desc");
				result.setLength(0);
				return new ResponseEntity<Result_task_one>(result, HttpStatus.OK);
				}
//---查询所有任务----
			
			@RequestMapping(value = "/task/all", method = RequestMethod.GET)
			public ResponseEntity<Result_data_task> listalltask() {
				SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   
		        SqlSession sqlSession = sqlSessionFactory.openSession();  
		       Task_inter task_inter = sqlSession.getMapper(Task_inter.class);  
				List<Task> tasks=task_inter.listTask();
				 Answer_inter answer_inter = sqlSession.getMapper(Answer_inter.class);  
					
			    Result_data_task result=new Result_data_task();
			    if(tasks==null) {
			    	result.setState(1);
			    	result.setError("error desc");
			    }
			    result.setState(0);
			    List<data_task> datalist=new ArrayList<data_task>();
			    for(int i=0;i<tasks.size();i++)
			    {
			    	List<Answer> answer=answer_inter.findAnswer_tid(tasks.get(i).getId());
			    	data_task data=new data_task();
			    	data.setT_id(Integer.toString(tasks.get(i).getId()));
			    	data.setTask_name(tasks.get(i).getTasktype());
			    	data.setDesc(tasks.get(i).getDescription());
			    	
			    	
			    	data.setExpect_answer_count(Integer.toString(tasks.get(i).getAnswercount()));
			    	data.setActual_answer_count(Integer.toString(answer.size()));
			    	DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					String time = sdf.format(tasks.get(i).getDeadline()); 
			    	data.setEnd_time(time);
			    	double x=answer.size();
			    	double u=tasks.get(i).getAnswercount();
			    	data.setTask_progress(x/u*100+"%");
			    	Timestamp t = new Timestamp(System.currentTimeMillis());
			    	
			    	if(x/u==1||(t.after(tasks.get(i).getDeadline()))) {
			    		data.setTask_status("已完成");
			    	}else {
			    	data.setTask_status("正在执行");
			    	}
			    	data.setPublish_time(sdf.format(tasks.get(i).getPublictime()));
			    	data.setTask_question(JSONArray.fromObject(tasks.get(i).getQuestion()));
			    	datalist.add(data);
			    }
			    result.setData(datalist);
			    result.setMessage("success");
				return new ResponseEntity<Result_data_task>(result,HttpStatus.OK);
			} 
//---查询所有用户---
			 //---获取所有的worker列表---
			  @RequestMapping(value = "/user/all", method = RequestMethod.GET)
				public ResponseEntity<Result_worker_list> listuserall() {
					SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   
			        SqlSession sqlSession = sqlSessionFactory.openSession();  
			       Worker_inter worker_inter = sqlSession.getMapper(Worker_inter.class);  
					List<Worker> workers=worker_inter.listWorker();
					Result_worker_list result=new Result_worker_list();
					System.out.println("执行查询所有用户");
					if(workers.isEmpty()){
						result.setStatus(1);
					
						result.setError("error");
						
						return new ResponseEntity<Result_worker_list>(HttpStatus.OK);//You many decide to return HttpStatus.NOT_FOUND
					}
					List<User_data> datalist=new ArrayList<User_data>();
					for(int i=0;i<workers.size();i++)
					{
						User_data data=new User_data();
						data.setU_id(Integer.toString(i));
						data.setU_name(workers.get(i).getUsername());
						String p=Integer.toString((int)workers.get(i).getReward()+1000); //这里是在原有基础上加了1000，这样显得报酬更合理
						data.setPrice(p);
						data.setPhone_number(workers.get(i).getPhoneno());
						datalist.add(data);
					}
					result.setStatus(0);
					result.setData(datalist);
					result.setMessage("success");
					
					return new ResponseEntity<Result_worker_list>(result, HttpStatus.OK);
				} 
				
//----显示答案详情----用来查看answer表结构----
				@RequestMapping(value = "/answer/list", method = RequestMethod.GET)
				public ResponseEntity<JSONArray> answer() {
					SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   
			        SqlSession sqlSession = sqlSessionFactory.openSession();  
			       Answer_inter answer_inter = sqlSession.getMapper(Answer_inter.class);  
					List<Answer> answer=answer_inter.listAnswer();
					
				
					
					return new ResponseEntity<JSONArray>(JSONArray.fromObject(answer), HttpStatus.OK);
				} 
//----显示图片----
				@RequestMapping(value = "/image/{username}/{id}/{aid}/{num}", method = RequestMethod.GET)
				 public String photoview(@PathVariable String username,@PathVariable int id,@PathVariable int aid,@PathVariable int num,HttpServletRequest request,  
				            HttpServletResponse response, Model model) {  
		
					
					SqlSessionFactory sqlSessionFactory= MybatisUtil.getInstance();   
			        SqlSession sqlSession = sqlSessionFactory.openSession(); 
			        Answer_inter answer_inter=sqlSession.getMapper(Answer_inter.class);
			        Answer answer=answer_inter.findAnswer_aid(aid);
			      
			try {        JSONArray json=JSONArray.fromObject(answer.getPicpath());
				List<String> list=new ArrayList<String>();
			        String path="";
			        int flag=0;
			       
			        for(int i=0;i<json.size();i++) {
			        	list.add(json.getString(i));
			        	flag=1;
			        	if(flag==0) {
				        	return "fail";
				        }
			        	path=json.getString(num);
							FileInputStream fis = null;  
					        OutputStream os = null;  
					        try {  
					            fis = new FileInputStream(path); 
					        
					           
					            os = response.getOutputStream();  
					            int count = 0;
					            byte[] buffer = new byte[1024 * 8];  
					            while ((count = fis.read(buffer)) != -1) {  
					                os.write(buffer, 0, count);  
					                os.flush();  
					            }  
					        } catch (Exception e) {  
					            e.printStackTrace();  
					        }  
					        try {  
					            fis.close();  
					            os.close();  
					        } catch (IOException e) {  
					            e.printStackTrace();  
					        }  
			        	
			        }
			        }catch(Exception e) {
			        	 String path="";
					        int flag=0;
					      if(answer.getPicpath()!=null)
					        	flag=1;
					   
					        	if(flag==0) {
						        	return "fail";
						        }
					        path=answer.getPicpath();
									FileInputStream fis = null;  
							        OutputStream os = null;  
							        try {  
							            fis = new FileInputStream(path); 
							        
							           
							            os = response.getOutputStream();  
							            int count = 0;
							            byte[] buffer = new byte[1024 * 8];  
							            while ((count = fis.read(buffer)) != -1) {  
							                os.write(buffer, 0, count);  
							                os.flush();  
							            }  
							        } catch (Exception e1) {  
							            e1.printStackTrace();  
							        }  
							        try {  
							            fis.close();  
							            os.close();  
							        } catch (IOException e2) {  
							            e2.printStackTrace();  
							        }  
					        	
			        	
			        }

	
				        return "ok";  
				    }  
}
