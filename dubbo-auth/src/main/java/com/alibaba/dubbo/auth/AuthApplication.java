package com.alibaba.dubbo.auth;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@MapperScan("com.alibaba.dubbo.dao")  
@SpringBootApplication  
@ImportResource(locations={"META-INF/spring/dubbo-auth.xml"}) 
public class AuthApplication {
	    private static Logger logger = LoggerFactory.getLogger(AuthApplication.class);
	   
	    //DataSource配置  
	    @Bean  
	    @ConfigurationProperties(prefix="spring.datasource")  
	    public DataSource dataSource() {  
	        return new com.alibaba.druid.pool.DruidDataSource();  
	    }  
	   
	    //提供SqlSeesion  
	    @Bean  
	    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {  
	   
	        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();  
	        sqlSessionFactoryBean.setDataSource(dataSource());  
	   
	        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();  
	   
	        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("META-INF/mapper/*.xml"));  
	   
	        return sqlSessionFactoryBean.getObject();  
	    }  
	   
	    @Bean  
	    public PlatformTransactionManager transactionManager() {  
	        return new DataSourceTransactionManager(dataSource());  
	    }  
	      
	    /**  
	     * Main 方法启动项  
	     */  
	    public static void main(String[] args) {  
	        SpringApplication.run(AuthApplication.class, args);  
	        System.out.println("============= APP Start ON SpringBoot auth Success =============");  
	        synchronized (AuthApplication.class) {  
//	            while (running) {  
	                try {  
	                    AuthApplication.class.wait();  
	                } catch (Throwable e) {  
	                }  
//	            }  
	        }  
	    }
	    
}
