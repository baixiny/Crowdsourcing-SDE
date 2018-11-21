package com.websystique.springmvc.MybatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisUtil {
	 private static SqlSessionFactory sqlSessionFactory;
	    private static Reader reader;

	    static {
	        try {
	            //mybatis的配置文件
	            String resource = "conf1.xml";
	            //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
	            //InputStream is = MybatisUtil.class.getClassLoader().getResourceAsStream(resource);
	            reader = Resources.getResourceAsReader(resource);

	            //构建sqlSession的工厂
	            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);  

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    public static SqlSessionFactory getInstance() {
	        return sqlSessionFactory;
	    }
	    
}
