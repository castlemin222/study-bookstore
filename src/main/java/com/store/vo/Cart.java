package com.store.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("Cart")
@Getter
@Setter
public class Cart {

	private int cartId;
	private int quantity;
	private Date createdDate;
	private Date updatedDate;
	private int bookId;
	private String userId;
}
