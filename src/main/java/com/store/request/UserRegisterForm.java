package com.store.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterForm {

	@NotBlank
	private String id;
	
	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]{8,20}", message = "유효한 비밀번호형식이 아닙니다.")
	private String encryptPassword;
	
	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]{8,20}", message = "유효한 비밀번호형식이 아닙니다.")
	private String passwordConfirm;
	
	@NotBlank
	@Pattern(regexp = "[가-힣]{2,}", message = "이름은 한글만 가능하며, 2글자 이상입니다.")
	private String name;
	
	@NotBlank
	@Email(message = "유효한 이메일형식이 아닙니다.")
	private String email;
	
	@NotBlank
	@Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}", message = "유효한 전화번호 형식이 아닙니다.")
	private String tel;
}
