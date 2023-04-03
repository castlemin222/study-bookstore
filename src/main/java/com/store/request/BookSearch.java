package com.store.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSearch {

	private String sort;
	private String opt;
	private String keyword;
}
