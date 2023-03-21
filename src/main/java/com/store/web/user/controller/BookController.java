package com.store.web.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.user.service.BookService;
import com.store.vo.Book;
import com.store.web.request.BookSearch;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;

	// 전체 도서목록 조회
	@GetMapping("/list")
	public String list(@RequestParam(name = "page", required = false, defaultValue = "1") int page, 
						BookSearch bookSearch, Model model) {
		Map<String, Object> result = bookService.getAllBooks(page, bookSearch);
		// 도서 목록
		model.addAttribute("bookList", result.get("bookList"));
		// 페이징 처리
		model.addAttribute("pagination", result.get("pagination"));

		return "book/list";
	}
	
}
