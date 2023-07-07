package com.youtube.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.youtube.model.vo.Channel;
import com.youtube.model.vo.Member;

import config.ServerInfo;

public class ChannelDAO implements ChannelDAOTemplate {

	private Properties p = new Properties();
	
	public ChannelDAO() {
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
	public int addChannel(Channel channel) throws SQLException {
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("addChannel"));
		
		st.setString(1, channel.getChannelName());
		st.setString(2, channel.getMember().getMemberId());
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		return result;
	}

	@Override
	public int updateChannel(Channel channel) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("updateChannel"));
		
		st.setString(1, channel.getChannelName());
		st.setInt(2, channel.getChannelCode());
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		return result;
	}

	@Override
	public int deleteChannel(int channelCode) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("deleteChannel"));
		
		st.setInt(1, channelCode);
		
		int result = st.executeUpdate();
		closeAll(st, conn);
		return result;
	}

	@Override
	public Channel myChannel(String memberId) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("myChannel"));
		
		st.setString(1, memberId);
		
		ResultSet rs = st.executeQuery();
		Channel channel = null;
		if(rs.next()) {
			channel = new Channel();
			channel.setChannelCode(rs.getInt("channel_code"));
			channel.setChannelName(rs.getString("channel_name"));
			Member member = new Member();
			member.setMemberNickname(rs.getString("member_nickname"));
			channel.setMember(member);
		}
		closeAll(st, conn);
		return channel;
	}

}
