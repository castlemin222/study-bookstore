package com.store.service.user;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.exception.AlreadyRegisteredEmailException;
import com.store.exception.AlreadyRegisteredUserIdException;
import com.store.mapper.UserMapper;
import com.store.request.UserRegisterForm;
import com.store.vo.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
	
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	public void insertUser(UserRegisterForm form) {
		// 회원가입한 아이디로 사용자 정보를 조회한다.
		User savedUser = userMapper.getUserById(form.getId());
		// 조회한 사용자 정보가 있으면 예외를 던진다.
		if (savedUser != null) {
			throw new AlreadyRegisteredUserIdException("이미 등록된 아이디입니다.");
		}
		// 회원가입한 이메일로 사용자 정보를 조회한다.
		savedUser = userMapper.getUserByEmail(form.getEmail());
		// 조회한 사용자 정보가 있으면 예외를 던진다.
		if (savedUser != null) {
			throw new AlreadyRegisteredEmailException("이미 등록된 이메일입니다.");
		}
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		
		// 비밀번호 암호화
		user.setEncryptPassword(passwordEncoder.encode(form.getEncryptPassword()));
		
		userMapper.insertUser(user);
		userMapper.insertUserRole(user.getId());
	}
}
