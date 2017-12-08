package com.alibaba.dubbo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.bean.UUser;
import com.alibaba.dubbo.service.UUserService;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/user")
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UUserService uUserService;

	@RequestMapping("/login")
	@ResponseBody
	public String getUserById(@RequestParam(value="userName") String userName,@RequestParam(value="password") String password) {
		UUser user = uUserService.login(userName, password);
		return JSON.toJSONString(user);
	}
	
	
}
