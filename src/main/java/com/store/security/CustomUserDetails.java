package com.store.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails extends LoginUser implements UserDetails {

	private static final long serialVersionUID = -1022370295119655350L;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(String userId, String password, String userName, Collection<? extends GrantedAuthority> authorities) {
		// 부모 생성자를 호출해서 LoginUser의 id,encryptPasswrod, name에 사용자아이디, 비밀번호, 이름이 저장되게 한다.
		super(userId, password, userName);
		
		this.authorities = authorities;
	}

	@Override
	// 권한 정보를 반환
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	// 비밀번호를 반환
	public String getPassword() {
		return getEncryptPassword();
	}

	@Override
	// 사용자이름(사용자아이디, 이메일, 사원번호, 학생번호 애플리케이션에 따라서 다르다.)
	public String getUsername() {
		return getId();
	}

	@Override
	// 계정 만료여부
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	// 계정 잠금여부
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	// 비밀번호 만료여부
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	// 사용 계정 가능여부
	public boolean isEnabled() {
		return true;
	}
}
