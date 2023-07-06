package com.kh.model.vo;


public class Book {
	
	private int bkNo;
	private String bkTitle;
	private String bkAuthor;
	
	public Book(int bkNo, String bkTitle, String bkAuthor) {
		this.bkNo = bkNo;
		this.bkTitle = bkTitle;
		this.bkAuthor = bkAuthor;
	}
	

	public Book(int bkNo) {
		this.bkNo = bkNo;
	}


	public Book(String title, String author) {
		this.bkTitle = title;
		this.bkAuthor = author;
	}

	public int getBkNo() {
		return bkNo;
	}

	public void setBkNo(int bkNo) {
		this.bkNo = bkNo;
	}

	public String getBkTitle() {
		return bkTitle;
	}

	public void setBkTitle(String bkTitle) {
		this.bkTitle = bkTitle;
	}

	public String getBkAuthor() {
		return bkAuthor;
	}

	public void setBkAuthor(String bkAuthor) {
		this.bkAuthor = bkAuthor;
	}


	@Override
	public String toString() {
		return "Book [bkNo=" + bkNo + ", bkTitle=" + bkTitle + ", bkAuthor=" + bkAuthor + "]";
	}
	
	
}