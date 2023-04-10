package com.store.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.store.vo.Cart;

@Mapper
public interface CartMapper {

	// 장바구니 추가
	void addBook(Cart cart);

}
