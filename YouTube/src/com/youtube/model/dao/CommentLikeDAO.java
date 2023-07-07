package com.youtube.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.youtube.model.vo.CommentLike;
import com.youtube.model.vo.VideoComment;
import com.youtube.model.vo.VideoLike;

import config.ServerInfo;

public class CommentLikeDAO implements CommentLikeDAOTemplate {

	private Properties p = new Properties();
	
	public CommentLikeDAO() {
		try {
			p.load(new FileInputStream("src/config/jdbc.properties"));
			Class.forName(ServerInfo.DRIVER_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Connection getConnect() throws SQLException {
		return DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
	}

	@Override
	public void closeAll(PreparedStatement st, Connection conn) throws SQLException {
		st.close();
		conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement st, Connection conn) throws SQLException {
		rs.close();
		closeAll(st, conn);
	}

	@Override
	public int addLike(VideoLike like) throws SQLException {
		return 0;
	}

	@Override
	public int deleteLike(int likeCode) throws SQLException {
		return 0;
	}

	@Override
	public int addComment(VideoComment comment) throws SQLException {
		return 0;
	}

	@Override
	public int updateComment(VideoComment comment) throws SQLException {
		return 0;
	}

	@Override
	public int deleteComment(int commentCode) throws SQLException {
		return 0;
	}

	@Override
	public ArrayList<VideoComment> videoCommentList(int videoCode) throws SQLException {
		return null;
	}

	@Override
	public int addCommentLike(CommentLike like) throws SQLException {
		return 0;
	}

	@Override
	public int deleteCommentLike(int likeCode) throws SQLException {
		return 0;
	}

}
