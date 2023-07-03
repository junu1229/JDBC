package transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.Serverinfo;

public class TransactionTest2 {

	public static void main(String[] args) {
		
		try {
			Class.forName(Serverinfo.DRIVER_NAME);
			Connection conn = DriverManager.getConnection(Serverinfo.URL, Serverinfo.USER, Serverinfo.PASSWORD);
			System.out.println("Driver loading...");
			
			PreparedStatement st1 = conn.prepareStatement("SELECT * FROM bank");
			ResultSet rs = st1.executeQuery();
			System.out.println("===========은행 조회==========");
			while(rs.next()) {
				System.out.println(rs.getString("name")+ " / "+rs.getString("bankname")+" / "+rs.getString("balance"));
			}
			System.out.println("============================");
			
			/*
			 * 민소 -> 도경 : 50만원씩 이체
			 * 이 관련 모든 쿼리를 하나로 묶는다... 하나의 단일 트랜잭션
			 * setAutocommit(), commit(), rollback().. 등등 사용을 해서 민소님의 잔액이 마이너스가 되면 이체 취소!
			 */
			conn.setAutoCommit(false);
			
			PreparedStatement st2 = conn.prepareStatement("SELECT balance FROM bank WHERE name = ? AND balance >= 0");
			PreparedStatement st3 = conn.prepareStatement("UPDATE bank SET balance = balance-? WHERE name=?");
			PreparedStatement st4 = conn.prepareStatement("UPDATE bank SET balance = balance+? WHERE name=?");
			st3.setInt(1, 500000);
			st3.setString(2, "김민소");
			st4.setInt(1, 500000);
			st4.setString(2, "김도경");
			
			int result1 = st3.executeUpdate();
			int result2 = st4.executeUpdate();
			
			st2.setString(1, "김민소");
			ResultSet rs2 = st2.executeQuery();
			if(rs2.next()) {
				conn.commit();
				System.out.println("이체 성공!!");
			} else {
				conn.rollback();
				System.out.println("잔액이 부족합니다!!");
			}

			conn.setAutoCommit(true);
			st1.close();
			st2.close();
			st3.close();
			st4.close();
			rs.close();
			rs2.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
