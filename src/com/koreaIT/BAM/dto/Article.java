package com.koreaIT.BAM.dto;

public class Article {
	
	private int id;
	private String regDate;
	private String title;
	private String body;
	private int memberId;
	private int viewCnt;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	
	public Article(int id, String regDate, String title, String body, int memberId, int viewCnt) {
		this.viewCnt = viewCnt;
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body = body;
		this.memberId = memberId;
	}
	
	public void increaseViewCnt() {
		this.viewCnt++;
	}

}
