package com.alibaba.dubbo.consumer;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(scanBasePackages="com.alibaba.dubbo")  
@ImportResource(locations = {"META-INF/spring/dubbo-consumer.xml"})  
public class WebConsumerStartAapp{  
     private static Logger logger = LoggerFactory.getLogger(WebConsumerStartAapp.class);  
     /**  
     * Main Start  
     */  
    public static void main(String[] args) {  
        SpringApplication.run(WebConsumerStartAapp.class, args);  
        System.out.println("============= SpringBoot consumer Start Success =============");  
    }
    
    
    //DataSource配置  ---待去掉
    @Bean  
    @ConfigurationProperties(prefix="spring.datasource")  
    public DataSource dataSource() {  
        return new com.alibaba.druid.pool.DruidDataSource();  
    }  
}  
