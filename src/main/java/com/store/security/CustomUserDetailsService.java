package com.store.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.store.mapper.UserMapper;
import com.store.vo.User;
import com.store.vo.UserRole;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// 사용자 아이디로 사용자 정보 조회
		User user = userMapper.getUserById(userId);
		if (user == null) {
			throw new UsernameNotFoundException("사용자 정보가 없습니다.");
		}
		if ("Y".equals(user.getDisabled())) {
			throw new UsernameNotFoundException("탈퇴한 사용자입니다.");
		}
		// 사용자 권한 조회
		List<UserRole> userRoles = userMapper.getUserRoleById(userId);
		// 조회된 권한정보로 GrantedAuthority객체를 생성한다.
		Collection<? extends GrantedAuthority> authorities = this.getAuthorities(userRoles);
		
		return new CustomUserDetails(
				user.getId(), 				
				user.getEncryptPassword(), 	
				user.getName(), 			
				authorities);				
		}
		
		// 사용자 권한정보 목록을 전달받아서 GrantedAuthority객체의 집합으로 반환한다.
		private Collection<? extends GrantedAuthority> getAuthorities(List<UserRole> userRoles) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			for (UserRole userRole : userRoles) {
				// 인터페이스 GrantedAuthority를 구현한 객체 SimpleGrantedAuthority 생성
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.getName());
				authorities.add(authority);
			}
			
			return authorities;
		}

}
