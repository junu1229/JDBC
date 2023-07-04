package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.model.vo.Member;

import config.Serverinfo;

/*
 * 
 * DAO란?
 * Database Access Object의 약자로 디비에 접근하는 로직
 * (일명 비즈니스 로직)을 담고 있는 객체
 * 
 */

public class MemberDAO implements MemberDAOTemplate {
private Properties p = new Properties();
	
	public MemberDAO() {
		try {
			p.load(new FileInputStream("src/config/jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(Serverinfo.URL, Serverinfo.USER, Serverinfo.PASSWORD);
		return conn;
	}
	
	public void closeAll(PreparedStatement st, Connection conn) throws SQLException {
		if (st != null)
			st.close();
		if (conn != null)
			conn.close();
	}
	
	public void closeAll(ResultSet rs, PreparedStatement st, Connection conn) throws SQLException {
		closeAll(st, conn);
		if (rs != null)
			rs.close();
	}
	
	public void registerMember(Member vo) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("register"));
		st.setString(1, vo.getId());
		st.setString(2, vo.getPassword());
		st.setString(3, vo.getName());
		int result = st.executeUpdate();
		if(result != 1) {
			System.out.println("오류가 발생했습니다.");
		}
		closeAll(st, conn);
	}
	
	public void updatePassword(Member vo) throws SQLException { 
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("updatePW"));
		st.setString(1, vo.getPassword());
		st.setString(2, vo.getId());
		int result = st.executeUpdate();
		if(result != 1) {
			System.out.println("오류가 발생했습니다.");
		}
		closeAll(st, conn);
	}
	
	public void updateName(Member vo) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("updateName"));
		st.setString(1, vo.getName());
		st.setString(2, vo.getId());
		int result = st.executeUpdate();
		if(result != 1) {
			System.out.println("오류가 발생했습니다.");
		}
		closeAll(st, conn);
	}
	
	public Member getMember(String id) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("getMem"));
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			Member temp = new Member(rs.getString("id"), rs.getString("password"), rs.getString("name"));
			closeAll(rs, st, conn);
			return temp;
		}
		closeAll(rs, st, conn);
		return null;
	}
	
	public Member login(Member vo) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("login"));
		st.setString(1, vo.getId());
		st.setString(2, vo.getPassword());
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			Member temp = new Member(rs.getString("id"), rs.getString("password"), rs.getString("name"));
			closeAll(rs, st, conn);
			return temp;
		}
		closeAll(rs, st, conn);
		return null;
	}
	


}
