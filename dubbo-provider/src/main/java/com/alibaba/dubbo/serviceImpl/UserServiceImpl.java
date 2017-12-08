package com.alibaba.dubbo.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.bean.User;
import com.alibaba.dubbo.bean.UserMapper;
import com.alibaba.dubbo.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
  @Resource  
  private UserMapper userMapper;  
  
	@Override
	public User getById(Integer id) {
		 return userMapper.selectByPrimaryKey(id);  
	}

}
