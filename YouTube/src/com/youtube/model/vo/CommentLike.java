package com.youtube.model.vo;

import java.util.Date;

public class CommentLike {

	private int commLikeCode;
	private Date commLikeDate;
	
	private VideoComment comment;
	private Member member;
	
	public CommentLike() {}

	public CommentLike(int commLikeCode, Date commLikeDate, VideoComment comment, Member member) {
		this.commLikeCode = commLikeCode;
		this.commLikeDate = commLikeDate;
		this.comment = comment;
		this.member = member;
	}

	public int getCommLikeCode() {
		return commLikeCode;
	}

	public void setCommLikeCode(int commLikeCode) {
		this.commLikeCode = commLikeCode;
	}

	public Date getCommLikeDate() {
		return commLikeDate;
	}

	public void setCommLikeDate(Date commLikeDate) {
		this.commLikeDate = commLikeDate;
	}

	public VideoComment getComment() {
		return comment;
	}

	public void setComment(VideoComment comment) {
		this.comment = comment;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "CommentLike [commLikeCode=" + commLikeCode + ", commLikeDate=" + commLikeDate + ", comment=" + comment
				+ ", member=" + member + "]";
	}

}
