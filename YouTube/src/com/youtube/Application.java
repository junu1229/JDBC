package com.youtube;

import java.util.ArrayList;
import java.util.Scanner;

import com.youtube.controller.YouTubeController;
import com.youtube.model.vo.Category;
import com.youtube.model.vo.Channel;
import com.youtube.model.vo.CommentLike;
import com.youtube.model.vo.Member;
import com.youtube.model.vo.Video;
import com.youtube.model.vo.VideoComment;

public class Application {
	
	private Scanner sc = new Scanner(System.in);
	private YouTubeController yc = new YouTubeController();
	
	public static void main(String[] args) {
		Application app = new Application();
		//app.register();
		//app.login();
		
		//app.addChannel();
		//app.myChannel();
		//app.updateChannel();
		//app.deleteChannel();
		
//		app.addVideo();
//		app.videoAllList();
//		app.channelVideoList();
//		app.updateVideo();
//		app.deleteVideo();
//		app.viewVideo();

//		app.addComment();
//		app.updateComment();
//		app.deleteComment();
		
//		app.addCommentLike();
//		app.deleteCommentLike();
//		app.addLike();
//		app.deleteLike();
		
//		app.addSubscribe();
//		app.mySubscribeList();
		app.deleteSubscribe();
	}
	
	// 회원가입
	public void register() {
		System.out.print("아이디 입력 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 입력 : ");
		String password = sc.nextLine();
		System.out.print("닉네임 입력 : ");
		String nickname = sc.nextLine();
		
		Member member = new Member();
		member.setMemberId(id);
		member.setMemberPassword(password);
		member.setMemberNickname(nickname);
		
		if(yc.register(member)) {
			System.out.println("회원가입에 성공하셨습니다~");
		} else {
			System.out.println("회원가입에 실패하였습니다 ㅠㅠ");
		}
	}
	
	// 로그인
	public void login() {
		System.out.print("아이디 입력 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 입력 : ");
		String password = sc.nextLine();
		Member member = yc.login(id, password);
		if(member!=null) {
			System.out.println("로그인 성공!");
		} else {
			System.out.println("로그인 실패 ㅠㅠ");
		}
	}
	
	// 채널 추가
	public void addChannel() {
		yc.login("user1", "1234");
		System.out.print("채널명 : ");
		String title = sc.nextLine();
		System.out.print("채널 사진 : ");
		String url = sc.nextLine();
		
		Channel channel = new Channel();
		channel.setChannelName(title);
		channel.setChannelPhoto(url);
		
		if(yc.addChannel(channel)) {
			System.out.println("채널이 추가되었습니다~");
		} else {
			System.out.println("채널 추가 실패 ㅠㅠㅠㅠ");
		}
	}
	
	// 채널 수정
	public void updateChannel() {
		yc.login("user1", "1234");
		System.out.print("변경할 채널명 : ");
		String updateTitle = sc.nextLine();
		Channel channel = new Channel();
		channel.setChannelName(updateTitle);
		if(yc.updateChannel(channel)) {
			System.out.println("채널 수정!");
		} else {
			System.out.println("채널 수정 실패 ㅠㅠ");
		}
	}
	
	// 채널 삭제
	public void deleteChannel() {
		yc.login("user1", "1234");
		if(yc.deleteChannel()) {
			System.out.println("채널 삭제 완료!");
		} else {
			System.out.println("채널 삭제 실패 ㅠㅠ");
		}
	}
	
	// 내 채널 보기
	public void myChannel() {
		yc.login("user1", "1234");
		Channel channel = yc.myChannel();
		System.out.println(channel.getChannelName() + " / " + channel.getMember().getMemberNickname());
	}
	
	// 비디오 추가
	public void addVideo() {
		yc.login("user1", "1234");
		System.out.print("비디오 제목 : ");
		String title = sc.nextLine();
		System.out.print("비디오 URL : ");
		String url = sc.nextLine();
		System.out.print("비디오 썸네일 : ");
		String image = sc.nextLine();
		for(Category category : yc.categoryList()) {
			System.out.println(category);
		}
		int categoryNo = 2;
		Video video = new Video();
		video.setVideoTitle(title);
		video.setVideoUrl(url);
		video.setVideoPhoto(image);
		Category category = new Category();
		category.setCategoryCode(categoryNo);
		video.setCategory(category);

		if(yc.addVideo(video)) {
			System.out.println("비디오 추가 성공!");
		} else {
			System.out.println("비디오 추가 실패 ㅠㅠ");
		}
	}
	
	// 비디오 전체 목록보기
	public void videoAllList() {
		for(Video video : yc.videoAllList()) {
			System.out.println(video.getVideoCode() + " / " + video.getVideoPhoto() + " / " + video.getVideoTitle() + " / " + video.getChannel().getChannelPhoto()
								+ " / " + video.getChannel().getChannelName() + " / " + video.getVideoViews());
		}
	}
	
	// 내 채널에 있는 비디오 목록 보기 -> 나중에 웹에서는 채널 페이지로 들어가니 그때는 채널별 목록 보기가 될 것!
	public void channelVideoList() {
		yc.login("user1", "1234");
		for(Video video : yc.channelVideoList()) {
			System.out.println(video.getVideoCode() + " / " + video.getVideoPhoto() + " / " + video.getVideoTitle() + " / " + video.getChannel().getChannelPhoto()
					+ " / " + video.getChannel().getChannelName() + " / " + video.getVideoViews());
		}
	}
	
	// 비디오 수정
	public void updateVideo() {
		
		channelVideoList();
		
		System.out.print("수정하고자 하는 비디오 선택 : ");
		int videoCode = Integer.parseInt(sc.nextLine());
		System.out.print("비디오 수정할 제목 : ");
		String updateTitle = sc.nextLine();
		Video video = new Video();
		video.setVideoCode(videoCode);
		video.setVideoTitle(updateTitle);
		if(yc.updateVideo(video)) {
			System.out.println("비디오 수정 성공!");
		} else {
			System.out.println("비디오 수정 실패 ㅠㅠ");
		}
		
	}

	// 비디오 삭제
	public void deleteVideo() {
		
		channelVideoList();
		
		System.out.print("삭제하고자 하는 비디오 선택 : ");
		int videoCode = Integer.parseInt(sc.nextLine());
		
		if(yc.deleteVideo(videoCode)) {
			System.out.println("비디오 삭제 성공!");
		} else {
			System.out.println("비디오 삭제 실패 ㅠㅠ");
		}
		
	}
	
	// 비디오 1개 보기 + 댓글들 보기 (좋아요 포함)
	public Video viewVideo() {
		
		videoAllList();
		
		System.out.print("비디오 선택 : ");
		int videoCode = Integer.parseInt(sc.nextLine());
		
		Video video = yc.viewVideo(videoCode);
		System.out.println(video.getVideoCode() + " / " + video.getVideoTitle() + " / " + video.getVideoDate() + " / " + video.getVideoViews() + " / " + video.getVideoUrl());
		System.out.println(video.getChannel().getChannelCode() + " / " + video.getChannel().getChannelName() + " / " + video.getChannel().getChannelPhoto());
		
		System.out.println("============================");
		
		for(CommentLike like : yc.videoCommentList(videoCode)) {
			System.out.println(like.getCommLikeCode() + " / " + like.getComment().getCommentCode() + " / " + like.getMember().getMemberNickname() + " / " + like.getComment().getCommentDesc());
		}
		
		return video;
		
	}
	
	// 댓글 추가
	public void addComment() {
		
		yc.login("user1", "1234");
		Video video = viewVideo();
		
		System.out.print("댓글 작성 : ");
		String commment = sc.nextLine();
		
		VideoComment comment = new VideoComment();
		comment.setCommentDesc(commment);
		comment.setVideo(video);
		if(yc.addComment(comment)) {
			System.out.println("댓글이 달렸습니다.");
		} else {
			System.out.println("댓글 작성하는데 에러 ㅠㅠ");
		}
	}
	
	// 댓글 수정 
	public void updateComment() {
		
		viewVideo();
		
		System.out.print("수정할 댓글 선택 : ");
		int commentNo = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정할 댓글 : ");
		String updateComment = sc.nextLine();
		
		VideoComment comment = new VideoComment();
		comment.setCommentDesc(updateComment);
		comment.setCommentCode(commentNo);
		
		if(yc.updateComment(comment)) {
			System.out.println("댓글이 수정되었습니다.");
		} else {
			System.out.println("댓글 수정하는데 에러 ㅠㅠ");
		}
		
	}
	
	// 댓글 삭제
	public void deleteComment() {
		
		viewVideo();
		
		System.out.print("삭제할 댓글 선택 : ");
		int commentNo = Integer.parseInt(sc.nextLine());
		
		if(yc.deleteComment(commentNo)) {
			System.out.println("댓글이 삭제되었습니다.");
		} else {
			System.out.println("댓글 삭제하는데 에러 ㅠㅠ");
		}
		
	}
	
	// 댓글 좋아요 추가
	public void addCommentLike() {
		
		viewVideo();
		
		System.out.print("좋아요할 댓글 선택 : ");
		int like = Integer.parseInt(sc.nextLine());
		
		if(yc.addCommetLike(like)) {
			System.out.println("댓글 좋아요 추가~");
		} else {
			System.out.println("댓글 좋아요 취소 ㅠㅠ");
		}
	}
	
	// 댓글 좋아요 취소
	public void deleteCommentLike() {
		
		viewVideo();
		
		System.out.print("좋아요할 댓글 취소 : ");
		int like = Integer.parseInt(sc.nextLine());
		
		if(yc.deleteCommentLike(like)) {
			System.out.println("댓글 좋아요 취소~");
		} else {
			System.out.println("댓글 좋아요 취소가 안돼 ㅠㅠ");
		}
	}
	
	// 좋아요 추가
	public void addLike() {
		
		yc.login("user1", "1234");
		Video video = viewVideo();
		
		if(yc.addLike(video.getVideoCode())) {
			System.out.println("좋아요~");
		} else {
			System.out.println("좋아요가 안돼 ㅠㅠ");
		}
	}
	
	// 좋아요 취소
	public void deleteLike() {
		
		yc.login("user1", "1234");
		Video video = viewVideo();
		
		if(yc.deleteLike(video.getVideoCode())) {
			System.out.println("좋아요 취소!");
		} else {
			System.out.println("좋아요 취소가 안돼 ㅠㅠ");
		}
	}
	
	// 채널 구독
	public void addSubscribe() {
		yc.login("user1", "1234");
		viewVideo();
		
		System.out.print("구독할 채널 선택 : ");
		int select = Integer.parseInt(sc.nextLine());

		if(yc.addSubscribe(select)) {
			System.out.println("구독!");
		} else {
			System.out.println("구독이 안돼 ㅠㅠ");
		}
	}
	
	// 내가 구독한 채널 목록 보기
	public void mySubscribeList() {
		yc.login("user1", "1234");
		
		for(Channel channel : yc.mySubscribeList()) {
			System.out.println(channel.getChannelCode() + " / " + channel.getChannelName() + " / " + channel.getChannelPhoto());
		}
	}
	
	// 채널 구독 취소
	public void deleteSubscribe() {
		mySubscribeList();
		
		System.out.print("구독 취소할 채널 선택 : ");
		int select = Integer.parseInt(sc.nextLine());

		if(yc.deleteSubscribe(select)) {
			System.out.println("구독 취소");
		} else {
			System.out.println("구독이 안돼 ㅠㅠ");
		}
	}
}






