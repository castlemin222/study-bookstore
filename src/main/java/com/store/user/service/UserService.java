package com.store.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.mapper.UserMapper;
import com.store.vo.User;
import com.store.web.request.UserRegisterForm;


@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserMapper userMapper;

	public void insertUser(UserRegisterForm form) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		userMapper.insertUser(user);		
	}
}
