package com.alibaba.dubbo.controller;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.bean.UUser;
import com.alibaba.dubbo.service.RoleService;
import com.alibaba.fastjson.JSON;

@RestController
@RequestMapping("/role")
public class RoleController {
	private static Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;

	@RequestMapping("/getRole")
	@ResponseBody
	public String getRole(@RequestParam(value="userId") Long userId) {
		Set<String> roles = roleService.findRoleByUserId(userId);
		return JSON.toJSONString(roles);
	}
	
}
