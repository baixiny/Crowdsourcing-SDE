package com.websystique.springmvc.CStable;

public class Worker {
	
    private String username;  
    private String password;  
    private String phoneno;  
   private  double reward;
   private String sex;   
   private String age;   
   private String area;  
   private String skill;  
    public double getReward() {  
        return reward;  
    }  
  
    public void setReward( double reward) {  
        this.reward = reward;  
    }  
    public String getSex() {  
        return sex;  
    }  
  
    public void setSex(String sex) {  
        this.sex = sex;  
    }
    public String getAge() {  
        return age;  
    }  
  
    public void setAge(String age) {  
        this.age = age;  
    }
    public String getArea() {  
        return area;  
    }  
  
    public void setArea(String area) {  
        this.area = area;  
    }
    public String getUsername() {  
        return username;  
    }  
  
    public void setSkill(String skill) {  
        this.skill = skill;  
    }
    public String getSkill() {  
        return skill;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    public String getPhoneno() {  
        return phoneno;  
    }  
  
    public void setPhoneno(String phoneno) {  
        this.phoneno = phoneno;  
    }

    @Override  
    public String toString() {  
        return "{\"username\":\"" + username + "\", \"password\":\""  
        		+ password + "\",\"reward\":\""+reward+"\", \"phoneno\"£º\"" 
        		+ phoneno + "\",sex\":\"" + sex + "\",\"age\":\"" + age + "\",\"area\":\"" + area + "\",\"skill\":\"" + skill + "\"}";  
    }  
  
}
