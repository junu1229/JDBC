package com.youtube.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.youtube.model.dao.ChannelDAO;
import com.youtube.model.dao.CommentLikeDAO;
import com.youtube.model.dao.MemberDAO;
import com.youtube.model.dao.VideoDAO;
import com.youtube.model.vo.Category;
import com.youtube.model.vo.Channel;
import com.youtube.model.vo.CommentLike;
import com.youtube.model.vo.Member;
import com.youtube.model.vo.Subscribe;
import com.youtube.model.vo.Video;
import com.youtube.model.vo.VideoComment;
import com.youtube.model.vo.VideoLike;

public class YouTubeController {

	private Member member = new Member();
	private Channel channel = new Channel();
	
	private MemberDAO memberDao = new MemberDAO();
	private ChannelDAO channelDao = new ChannelDAO();
	private VideoDAO videoDao = new VideoDAO();
	private CommentLikeDAO commentDao = new CommentLikeDAO();
	
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
		myChannel();
		video.setChannel(this.channel);
		video.setMember(this.member);
		try {
			if(videoDao.addVideo(video)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Category> categoryList() {
		try {
			return videoDao.categoryList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Video> videoAllList() {
		try {
			return videoDao.videoAllList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Video> channelVideoList() {
		myChannel();
		try {
			return videoDao.channelVideoList(this.channel.getChannelCode());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateVideo(Video video) {
		try {
			if(videoDao.updateVideo(video)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteVideo(int videoCode) {
		try {
			if(videoDao.deleteVideo(videoCode)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	public Video viewVideo(int videoCode) {
		try {
			return videoDao.viewVideo(videoCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addComment(VideoComment comment) {
		try {
			comment.setMember(member);
			if(commentDao.addComment(comment)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<CommentLike> videoCommentList(int video_code) {
		try {
			return commentDao.videoCommentList(video_code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateComment(VideoComment comment) {
		try {
			if(commentDao.updateComment(comment)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteComment(int commentCode) {
		try {
			if(commentDao.deleteComment(commentCode)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addCommetLike(int commentCode) {
		try {
			CommentLike like = new CommentLike();
			like.setMember(member);
			VideoComment comment = new VideoComment();
			comment.setCommentCode(commentCode);
			like.setComment(comment);
			if(commentDao.addCommentLike(like)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteCommentLike(int commentCode) {
		try {
			if(commentDao.deleteCommentLike(commentCode)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addLike(int videoCode) {
		VideoLike like = new VideoLike();
		Video video = new Video();
		video.setVideoCode(videoCode);
		like.setVideo(video);
		like.setMember(member);
		try {
			if(commentDao.addLike(like)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteLike(int videoCode) {
		VideoLike like = new VideoLike();
		Video video = new Video();
		video.setVideoCode(videoCode);
		like.setVideo(video);
		like.setMember(member);
		try {
			if(commentDao.deleteLike(like)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addSubscribe(int channelCode) {
		
		Subscribe subs = new Subscribe();
		subs.setMember(member);
		
		Channel channel = new Channel();
		channel.setChannelCode(channelCode);
		subs.setChannel(channel);
		
		try {
			if(memberDao.addSubscribe(subs)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Channel> mySubscribeList() {
		try {
			return memberDao.mySubscribeList(member.getMemberId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteSubscribe(int channelCode) {
		
		Subscribe subs = new Subscribe();
		subs.setMember(member);
		
		Channel channel = new Channel();
		channel.setChannelCode(channelCode);
		subs.setChannel(channel);
		
		try {
			if(memberDao.deleteSubscribe(subs)==1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}






