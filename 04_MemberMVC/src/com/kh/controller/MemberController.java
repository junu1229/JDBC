package com.kh.controller;


import java.sql.SQLException;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

public class MemberController {

	private MemberDAO dao = new MemberDAO();
	
	public boolean joinMembership(Member m) {
		try {
			if(dao.getMember(m.getId())==null) {
				dao.registerMember(m);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// id가 없다면 회원가입 후 true 반환
		// 없다면 false 값 반환
		
		return false;

	}
	
	public String login(String id, String password) {
		try {
			Member temp1 = new Member(id, password, null);
			Member temp =dao.login(temp1);
			if(temp != null) {
				return temp.getName();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 로그인 성공하면 이름 반환
		// 실패하면 null 반환
		
		return null;
	}
	
	public boolean changePassword(String id, String oldPw, String newPw) {
		try {
			Member oldM = new Member(id, oldPw, null);
			Member newM = new Member(id, newPw, null);
			Member temp =dao.login(oldM);
			if(temp != null) {
				dao.updatePassword(newM);
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 로그인 했을 때 null이 아닌 경우
		// 비밀번호 변경 후 true 반환, 아니라면 false 반환
		
		return false;
	}
	
	public void changeName(String id, String name) {

		try {
			Member oldM = new Member(id, null, name);
			dao.updateName(oldM);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 이름 변경!
		
	}


}