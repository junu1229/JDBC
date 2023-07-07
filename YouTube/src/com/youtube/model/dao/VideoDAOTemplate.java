package com.youtube.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.youtube.model.vo.Category;
import com.youtube.model.vo.Video;

public interface VideoDAOTemplate {

	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement st, Connection conn) throws SQLException;
	void closeAll(ResultSet rs, PreparedStatement st, Connection conn) throws SQLException;
	
	// 비디오 추가, 비디오 수정, 비디오 삭제
	int addVideo(Video video) throws SQLException;
	int updateVideo(Video video) throws SQLException;
	int deleteVideo(int videoCode) throws SQLException;
	
	// 비디오 전체 목록보기, 채널별 목록보기
	ArrayList<Video> videoAllList() throws SQLException;
	ArrayList<Video> channelVideoList(int channelCode) throws SQLException;
	
	// 비디오 1개 보기
	Video viewVideo(int videoCode) throws SQLException;
	
	// 카테고리 보기
	ArrayList<Category> categoryList() throws SQLException;
}




