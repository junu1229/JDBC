package com.youtube.model.vo;

import java.util.Date;

public class VideoLike {
	
	private int vLikeCode;
	private Date vLikeDate;
	
	private Video video;
	private Member member;

	public VideoLike() {}
	public VideoLike(int vLikeCode, Date vLikeDate, Video video, Member member) {
		this.vLikeCode = vLikeCode;
		this.vLikeDate = vLikeDate;
		this.video = video;
		this.member = member;
	}

	public int getvLikeCode() {
		return vLikeCode;
	}

	public void setvLikeCode(int vLikeCode) {
		this.vLikeCode = vLikeCode;
	}

	public Date getvLikeDate() {
		return vLikeDate;
	}

	public void setvLikeDate(Date vLikeDate) {
		this.vLikeDate = vLikeDate;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "VideoLike [vLikeCode=" + vLikeCode + ", vLikeDate=" + vLikeDate + ", video=" + video + ", member="
				+ member + "]";
	}
}
