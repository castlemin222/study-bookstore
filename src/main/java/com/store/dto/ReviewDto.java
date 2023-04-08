package com.store.dto;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("ReviewDto")
@Getter
@Setter
@ToString
public class ReviewDto {

	// review
	private int reviewId;
	private String content;
	private int score;
	private Date createdDate;
	private Date updatedDate;
	private int bookId;
	private String userId;
	
	// user
	private String userName;
}
