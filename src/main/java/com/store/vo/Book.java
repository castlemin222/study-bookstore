package com.store.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("Book")
@Getter
@Setter
@ToString
public class Book {

	private int id;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private int discountPrice;
	private int stock;
	private String onSell;
	private int reviewCount;
	private int reviewScore;
	private Date createdDate;
	private Date updatedDate;
}
