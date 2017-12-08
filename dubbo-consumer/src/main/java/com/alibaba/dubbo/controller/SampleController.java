package com.alibaba.dubbo.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.demo.DemoService;

	
	@Controller
	@EnableAutoConfiguration
	public class SampleController {

	    @RequestMapping("/")
	    @ResponseBody
	    String home() {
	        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-consumer.xml"});
	        context.start();

	        DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
	        String hello = demoService.sayHello("world"); // 执行远程方法

	        System.out.println(hello); // 显示调用结果
	        return "Hello World!";
	    }

	    public static void main(String[] args) throws Exception {
	        SpringApplication.run(SampleController.class, args);
	    }
	}
