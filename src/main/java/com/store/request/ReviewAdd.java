package com.store.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewAdd {

	private int bookId;
	private int score;
	private String content;
}
