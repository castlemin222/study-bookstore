package com.store.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterForm {

	private String id;
	private String encryptPassword;
	private String passwordConfirm;
	private String name;
	private String email;
	private String tel;
}
