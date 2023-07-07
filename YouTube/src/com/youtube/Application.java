package com.youtube;

import java.util.Scanner;

import com.youtube.controller.YouTubeController;
import com.youtube.model.vo.Channel;
import com.youtube.model.vo.Member;

public class Application {
	
	private Scanner sc = new Scanner(System.in);
	private YouTubeController yc = new YouTubeController();
	
	public static void main(String[] args) {
		Application app = new Application();
		//app.register();
		//app.login();
		app.addChannel();
		//app.myChannel();
		//app.updateChannel();
		//app.deleteChannel();
		app.addVideo();
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
		Channel channel = new Channel();
		channel.setChannelName(title);
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
		System.out.print("비디오 제목 : ");
		String title = sc.nextLine();
		System.out.print("비디오 URL : ");
		String url = sc.nextLine();
		System.out.print("비디오 썸네일 : ");
		String image = sc.nextLine();
	}

}






