package com.store.controller.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.store.dto.ReviewDto;
import com.store.request.BookSearch;
import com.store.request.ReviewAdd;
import com.store.security.AuthenticatedUser;
import com.store.security.LoginUser;
import com.store.service.user.BookService;
import com.store.vo.Book;
import com.store.vo.Review;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {
	
	private final BookService bookService;

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
	
	// 도서 상세 페이지
	@GetMapping("/detail")
	public String detail(@RequestParam(name = "id") int id, Model model) {
		// 도서 아이디로 도서 정보 조회
		Book book = bookService.getBookById(id);
		// 도서아이디에 해당하는 리뷰 조회
		List<ReviewDto> reviewList = bookService.getAllReview(id);
		System.out.println("############## 별점상세" + reviewList);
		model.addAttribute("book", book);
		model.addAttribute("reviewList", reviewList);
		return "book/detail";
	}
	
	// 리뷰 처리
	@PostMapping("/review/add")
	public String review(@AuthenticatedUser LoginUser loginUser, ReviewAdd reviewAdd) {
		bookService.insertReview(loginUser.getId(), reviewAdd);
		return "redirect:/book/detail?id="+reviewAdd.getBookId();
	}
	
}
