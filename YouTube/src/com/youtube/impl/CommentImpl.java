package com.youtube.impl;

import com.youtube.model.Comment;

// 팀 과제는 이 방식!
public interface CommentImpl {
	public void addCommment(String id, String password, Comment comment);
	public Comment viewComment(int index);
	public void updateComment(int index, Comment comment);
	public void deleteComment(int index);
	
	
	
	
	
	
	
}
