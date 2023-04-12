package com.store.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("CartDto")
@Getter
@Setter
public class CartDto {

	// cart
	private int cartId;
	private int quantity;
	private int bookId;
	
	// book
	private String title;
	private int price;
	private int discountPrice;
	private int stock;
}
