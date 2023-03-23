package com.store.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void insertUser(UserRegisterForm form) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		// 비밀번호 암호화
		user.setEncryptPassword(passwordEncoder.encode(form.getEncryptPassword()));
		
		userMapper.insertUser(user);
		userMapper.insertUserRole(user.getId());
	}
}
