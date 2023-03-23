package com.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.store.vo.User;
import com.store.vo.UserRole;

@Mapper
public interface UserMapper {

	// 사용자 아이디로 사용자 정보 조회
	public User getUserById(String userId);
	// 사용자 권한 조회
	public List<UserRole> getUserRoleById(String userId);
	// 사용자 정보 등록
	public void insertUser(User user);
	// 사용자 권한 등록
	public void insertUserRole(String userId);
}
