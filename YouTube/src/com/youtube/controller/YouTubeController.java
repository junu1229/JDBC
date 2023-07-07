package com.youtube.controller;

import java.sql.SQLException;

import com.youtube.model.dao.ChannelDAO;
import com.youtube.model.dao.MemberDAO;
import com.youtube.model.dao.VideoDAO;
import com.youtube.model.vo.Channel;
import com.youtube.model.vo.Member;
import com.youtube.model.vo.Video;

public class YouTubeController {

	private Member member = new Member();
	private Channel channel = new Channel();
	
	private MemberDAO memberDao = new MemberDAO();
	private ChannelDAO channelDao = new ChannelDAO();
	private VideoDAO videoDao = new VideoDAO();
	
	public boolean register(Member member) {
		try {
			if(memberDao.register(member)==1) return true;
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
	
	public Member login(String id, String password) {
		try {
			member = memberDao.login(id, password);
			if(member!=null) return member;
			else return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addChannel(Channel channel) {
		try {
			channel.setMember(member);
			if(channelDao.addChannel(channel)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateChannel(Channel channel) {
		myChannel();
		try {
			channel.setChannelCode(this.channel.getChannelCode());
			if(channelDao.updateChannel(channel)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteChannel() {
		myChannel();
		try {
			if(channelDao.deleteChannel(this.channel.getChannelCode())==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Channel myChannel() {
		try {
			channel = channelDao.myChannel(this.member.getMemberId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return channel;
	}
	
	public boolean addVideo(Video video) {
		try {
			if(videoDao.addVideo(video)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}






