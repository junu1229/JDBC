package com.youtube.model.vo;

import java.util.Date;

public class Channel {
	private int channelCode;
	private String channelName;
	private String channelPhoto;
	private String channelDesc;
	private Date channelDate;
	
	private Member member;

	public Channel() {}

	public Channel(int channelCode, String channelName, String channelPhoto, String channelDesc, Date channelDate,
			Member member) {
		this.channelCode = channelCode;
		this.channelName = channelName;
		this.channelPhoto = channelPhoto;
		this.channelDesc = channelDesc;
		this.channelDate = channelDate;
		this.member = member;
	}

	public int getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(int channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelPhoto() {
		return channelPhoto;
	}

	public void setChannelPhoto(String channelPhoto) {
		this.channelPhoto = channelPhoto;
	}

	public String getChannelDesc() {
		return channelDesc;
	}

	public void setChannelDesc(String channelDesc) {
		this.channelDesc = channelDesc;
	}

	public Date getChannelDate() {
		return channelDate;
	}

	public void setChannelDate(Date channelDate) {
		this.channelDate = channelDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Channel [channelCode=" + channelCode + ", channelName=" + channelName + ", channelPhoto=" + channelPhoto
				+ ", channelDesc=" + channelDesc + ", channelDate=" + channelDate + ", member=" + member + "]";
	}

}
