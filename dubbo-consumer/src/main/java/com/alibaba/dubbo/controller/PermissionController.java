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
import com.alibaba.dubbo.service.PermissionService;
import com.alibaba.dubbo.service.UUserService;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/permission")
public class PermissionController {
	private static Logger logger = LoggerFactory.getLogger(PermissionController.class);
	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/getPermission")
	@ResponseBody
	public String getPermission(@RequestParam(value="userId")  Long userId) {
		//根据用户ID查询权限（permission），放入到Authorization里。
		Set<String> permissions = permissionService.findPermissionByUserId(userId);
		return JSON.toJSONString(permissions);
	}
}
