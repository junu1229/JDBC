package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.model.vo.Member;

public interface MemberDAOTemplate {
	
	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement st, Connection conn) throws SQLException;
	void closeAll(ResultSet rs,PreparedStatement st, Connection conn) throws SQLException;
	void registerMember(Member vo) throws SQLException;
	void updatePassword(Member vo) throws SQLException;
	void updateName(Member vo) throws SQLException;
	Member getMember(String id) throws SQLException;
	Member login(Member vo) throws SQLException;

}
