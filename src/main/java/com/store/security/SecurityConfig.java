package com.store.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	
			.authorizeRequests()	
			.antMatchers("/", "/register", "/login", "/success", "/book/**").permitAll()	
			.antMatchers("/user/**", "/cart/**", "/order/**").hasRole("USER")	
			.antMatchers("/admin/**").hasRole("ADMIN")	
			.anyRequest().authenticated()				
		.and()	
			.formLogin()						
			.loginPage("/login")				
			.loginProcessingUrl("/login")		
			.usernameParameter("id")		
			.passwordParameter("password")
			.defaultSuccessUrl("/")	
			.failureUrl("/login?error=fail")
			.permitAll()
		.and()
			.logout()							
			.logoutUrl("/logout")				
			.logoutSuccessUrl("/")					
	    	.permitAll()		
		.and()
			.exceptionHandling()				
			.accessDeniedPage("/access-denied");
	}
	
	// 이미지, 스타일시트, 자바스크립트소스와 같은 정적 컨텐츠는 인증/인가 작업을 수행하지 않도록 설정한다.
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/favicon.ico");
	}
	
	// 사용자정의 UserDetailsService객체와 이 애플리케이션에서 사용하는 비밀번호 인코더를 AuthenticationManager에 등록시킨다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}
}
