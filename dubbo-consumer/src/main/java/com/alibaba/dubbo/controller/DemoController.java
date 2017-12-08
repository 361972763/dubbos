package com.alibaba.dubbo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.bean.User;
import com.alibaba.dubbo.service.UserService;

@RestController
@RequestMapping("/user")
public class DemoController {
	private static Logger logger = LoggerFactory.getLogger(DemoController.class);
	@Autowired
	private UserService userService;

	@RequestMapping("/getUserById")
	public Object getUserById(@RequestParam("id") Integer id) {
		User user = userService.getById(id);
		if (user != null) {
			System.out.println("user.getName():" + user.getName());
			logger.info("user.getAge():" + user.getAge());
		}
		return user;
	}
}
