package com.store.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("UserRole")
@Getter
@Setter
@ToString
public class UserRole {

	private String id;
	private String name;
}
