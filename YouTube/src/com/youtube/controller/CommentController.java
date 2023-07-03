package com.youtube.controller;

import java.util.ArrayList;
import java.util.List;

import com.youtube.model.Comment;

// 개인 과제는! 이 방식~~
public class CommentController {

	List<Comment> comments = new ArrayList<>();
	UserController uc = new UserController();
	
	// C : 댓글 작성 --> 로그인된 회원만 가능!
	public void addCommment(String id, String password, Comment comment) {
		
		if(uc.login(id, password)) {
			comments.add(comment);
		}
		
	}
	// R : 댓글 1개 보기
	public Comment viewComment(int index) {
		return comments.get(index);
	}
	
	// U : 댓글 수정 --> 로그인한 회원과 이 댓글을 작성한 작성자가 일치
	public void updateComment(int index, Comment comment) {
		comments.set(index, comment);
	}
	
	// D : 댓글 삭제 --> 로그인한 회원과 이 댓글을 작성한 작성자가 일치
	public void deleteComment(int index) {
		comments.remove(index);
	}
	
	
	
	
}



