package com.store.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.store.dto.ReviewDto;
import com.store.vo.Book;
import com.store.vo.Review;

@Mapper
public interface BookMapper {

	// 전체 도서목록 조회
	List<Book> getAllBooks(Map<String, Object> param);
	// 검색조건에 해당하는 도서목록 갯수 조회
	int getTotalRows(Map<String, Object> rows);
	// 도서아이디로 도서 정보 조회
	Book getBookById(int id);
	// 도서아이디에 해당하는 리뷰 조회
	List<ReviewDto> getAllReview(int bookId);
	// 리뷰 추가
	void insertReview(Review review);
}
