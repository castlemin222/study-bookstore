package com.store.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.mapper.BookMapper;
import com.store.utils.Pagination;
import com.store.vo.Book;
import com.store.web.request.BookSearch;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookMapper bookMapper;
	
	// 전체 도서목록 조회
	public Map<String, Object> getAllBooks(int page, BookSearch bookSearch) {
		
		// 검색조건에 해당하는 프로그램 갯수 조회
		Map<String, Object> rows = new HashMap<>();
		rows.put("sort", bookSearch.getSort());
		rows.put("opt", bookSearch.getOpt());
		rows.put("keyword", bookSearch.getKeyword());
		int totalRows = bookMapper.getTotalRows(rows);
		
		// Pagination 객체 생성 
		Pagination pagination = new Pagination(page, totalRows, 12);
		
		// 페이징처리에 필요한 Map<String, Object>객체 생성
		Map<String, Object> param = new HashMap<>();
		// 페이징 처리
		param.put("begin", pagination.getBegin());
		param.put("end", pagination.getEnd());
		// 검색조건에 해당하는 도서목록
		param.put("sort", bookSearch.getSort());
		param.put("opt", bookSearch.getOpt());
		param.put("keyword", bookSearch.getKeyword());
		
		// 도서목록 정보와 페이징처리에 필요한 정보를 담는 객체 생성
		Map<String, Object> result = new HashMap<>();
		result.put("bookList", bookMapper.getAllBooks(param));
		result.put("pagination", pagination);
		
		return result;
	}
	
	// 도서아이디로 도서 정보 조회
	public Book getBookById(int id) {
		return bookMapper.getBookById(id);
	}
}
