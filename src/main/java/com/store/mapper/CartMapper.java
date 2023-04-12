package com.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.store.dto.CartDto;
import com.store.vo.Cart;

@Mapper
public interface CartMapper {

	// 장바구니 목록
	List<CartDto> getAllCart(String userId);
	// 장바구니 추가
	void addBook(Cart cart);
	// 장바구니 목록 삭제
	void deleteByBookId(int bookId);

}
