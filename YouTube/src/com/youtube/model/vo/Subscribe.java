package com.youtube.model.vo;

import java.util.Date;

public class Subscribe {

	private int subsCode;
	private Date subsDate;
	
	private Member member;
	private Channel channel;
	
	public Subscribe() {}
	public Subscribe(int subsCode, Date subsDate, Member member, Channel channel) {
		this.subsCode = subsCode;
		this.subsDate = subsDate;
		this.member = member;
		this.channel = channel;
	}
	
	public int getSubsCode() {
		return subsCode;
	}
	public void setSubsCode(int subsCode) {
		this.subsCode = subsCode;
	}
	public Date getSubsDate() {
		return subsDate;
	}
	public void setSubsDate(Date subsDate) {
		this.subsDate = subsDate;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	@Override
	public String toString() {
		return "Subscribe [subsCode=" + subsCode + ", subsDate=" + subsDate + ", member=" + member + ", channel="
				+ channel + "]";
	}
}
