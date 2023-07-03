package transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import config.Serverinfo;

public class TransactionTest {

	public static void main(String[] args) {
		
		try {
			// 1. 드라이버 로딩
			Class.forName(Serverinfo.DRIVER_NAME);
			
			// 2. 데이터 베이스연결
			Connection conn = DriverManager.getConnection(Serverinfo.URL, Serverinfo.USER, Serverinfo.PASSWORD);
			System.out.println("DB Connection...");
			conn.setAutoCommit(false);
			
			// 3. PreparedStatement
			String query1 = "INSERT INTO customer(name, age, address) VALUES(?, ?, ?)";
			PreparedStatement st1 = conn.prepareStatement(query1);
			
			// 4. 쿼리문 실행
			st1.setString(1, "김경미");
			st1.setInt(2, 16);
			st1.setString(3, "서울 강남구");
			int result = st1.executeUpdate();
			System.out.println(result + "명 추가!");
			
			Savepoint savepoint =conn.setSavepoint("A");
			String query2 = "SELECT * FROM customer WHERE name=?";
			PreparedStatement st2 = conn.prepareStatement(query2);
			st2.setString(1, "홍수민");
			
			ResultSet rs = st2.executeQuery();
			if(rs.next()) {
				conn.rollback(savepoint);
				System.out.println("회원 정보가 있으므로 Rollback!!");
			} else {
				conn.commit();
				System.out.println("회원 정보가 없으므로 commit!!");
			}
			// 트랜잭션 처리를 다시 원래대로 돌려놓음
//			conn.setAutoCommit(true);
			
			st1.close();
			rs.close();
			st2.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
