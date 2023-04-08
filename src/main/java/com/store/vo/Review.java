package com.store.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Alias("Review")
@Getter
@Setter
public class Review {
	
	private int reviewId;
	private String content;
	private int score;
	private Date createdDate;
	private Date updatedDate;
	private int bookId;
	private String userId;
}
