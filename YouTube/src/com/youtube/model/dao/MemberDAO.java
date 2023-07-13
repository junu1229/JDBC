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

import com.youtube.model.vo.Category;
import com.youtube.model.vo.Channel;
import com.youtube.model.vo.Member;
import com.youtube.model.vo.Subscribe;

import config.ServerInfo;

public class MemberDAO implements MemberDAOTemplate {

	private Properties p = new Properties();
	
	public MemberDAO() {
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
	public int register(Member member) throws SQLException {
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("register"));
		st.setString(1, member.getMemberId());
		st.setString(2, member.getMemberPassword());
		st.setString(3, member.getMemberNickname());
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		return result;
	
	}

	@Override
	public Member login(String id, String password) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("login"));
		st.setString(1, id);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();
		Member member = null;
		if(rs.next()) {
			member = new Member();
			member.setMemberId(rs.getString("member_id"));
			member.setMemberPassword(rs.getString("member_password"));
			member.setMemberNickname(rs.getString("member_nickname"));
		}
		closeAll(rs, st, conn);
		return member;
	}

	@Override
	public int addSubscribe(Subscribe subscribe) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("addSubscribe"));
		
		st.setInt(1, subscribe.getChannel().getChannelCode());
		st.setString(2, subscribe.getMember().getMemberId());
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		return result;
	}

	@Override
	public int deleteSubscribe(Subscribe subscribe) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("deleteSubscribe"));
		
		st.setInt(1, subscribe.getChannel().getChannelCode());
		st.setString(2, subscribe.getMember().getMemberId());
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		return result;
	}

	@Override
	public ArrayList<Channel> mySubscribeList(String memberId) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("mySubscribeList"));
		
		st.setString(1, memberId);
		
		ResultSet rs = st.executeQuery();
		ArrayList<Channel> list = new ArrayList<>();
		while(rs.next()) {
			Channel channel = new Channel();
			channel.setChannelCode(rs.getInt("channel_code"));
			channel.setChannelName(rs.getString("channel_name"));
			channel.setChannelPhoto(rs.getString("channel_photo"));
			list.add(channel);
		}
		closeAll(rs, st, conn);
		return list;
	}

}
