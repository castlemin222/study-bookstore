package com.store.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.mapper.BookMapper;
import com.store.utils.Pagination;
import com.store.web.request.BookSearch;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookMapper bookMapper;
	
	// 전체 도서목록 조회
	public Map<String, Object> getAllBooks(int page, BookSearch bookSearch) {
		
		// 총 프로그램 갯수 조회
		int totalRows = bookMapper.getTotalRows();
		Pagination pagination = new Pagination(page, totalRows);
		
		// 페이징처리에 필요한 Map<String, Object>객체 생성
		Map<String, Object> param = new HashMap<>();
		param.put("sort", bookSearch.getSort());
		param.put("opt", bookSearch.getOpt());
		param.put("keyword", bookSearch.getKeyword());
		
		// 도서목록 정보와 페이징처리에 필요한 정보를 담는 객체 생성
		Map<String, Object> result = new HashMap<>();
		result.put("bookList", bookMapper.getAllBooks(param));
		result.put("pagination", pagination);
		
		return result;
	}
}
