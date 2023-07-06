package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.vo.Book;
import com.kh.model.vo.Member;
import com.kh.model.vo.Rent;

public interface BookDAOTemplate {

	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement st, Connection conn) throws SQLException;
	void closeAll(ResultSet rs, PreparedStatement st, Connection conn) throws SQLException;
	
	// 전체 책 조회, 책 등록, 책 삭제
	ArrayList<Book> printBookAll() throws SQLException;
	int registerBook(Book book) throws SQLException;
	int sellBook(int no) throws SQLException;
	
	// 회원가입, 로그인, 회원탈퇴
	int registerMember(Member member) throws SQLException;
	Member login(String id, String password) throws SQLException;
	int deleteMember(String id, String password) throws SQLException;
	
	// 책 대여, 대여 취소, 내가 대여한 책 조회
	int rentBook(Rent rent) throws SQLException;
	int deleteRent(int no) throws SQLException;
	ArrayList<Rent> printRentBook(String id) throws SQLException;
	
}