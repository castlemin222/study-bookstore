package com.store.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.dto.CartDto;
import com.store.security.AuthenticatedUser;
import com.store.security.LoginUser;
import com.store.service.user.CartService;
import com.store.vo.Cart;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cart")
public class CartController {
	
	private final CartService cartService;

	// 장바구니 목록 화면 요청
	@GetMapping("/list")
	public String list(@AuthenticatedUser LoginUser loginUser, Model model) {
		List<CartDto> cartList = cartService.getAllCart(loginUser.getId());
		model.addAttribute("cartList", cartList);
		return "cart/list";
	}
	
	// 장바구니 추가
	@GetMapping("/add")
	public String add(@AuthenticatedUser LoginUser loginUser, 
					 @RequestParam(name = "bookId") int bookId, 
					 @RequestParam(name = "quantity") int quantity) {
		cartService.addBook(loginUser.getId(), bookId, quantity);
		return "redirect:list";
	}
	
	// 장바구니 목록 삭제
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "bookId") int bookId) {
		cartService.deleteByBookId(bookId);
		return "redirect:list";
	}
}
