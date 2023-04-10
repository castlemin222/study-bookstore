package com.store.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.mapper.CartMapper;
import com.store.vo.Cart;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class CartService {

	private final CartMapper cartMapper;

	// 장바구니 목록 
	public List<Cart> cartList() {
		return null;
	}
	
	// 장바구니 추가
	public void addBook(String userId, int bookId, int quantity) {
		Cart cart = new Cart();
		cart.setUserId(userId);
		cart.setBookId(bookId);
		cart.setQuantity(quantity);
		
		cartMapper.addBook(cart);
	}
}
