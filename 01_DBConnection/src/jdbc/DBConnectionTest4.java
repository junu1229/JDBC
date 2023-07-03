package jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import config.Serverinfo;

public class DBConnectionTest4 {
	
	/*
	 * 디비 서버에 대한 정보가 프로그램상에 하드코딩 되어져 있음!
	 * --> 해결책 : 별도의 모듈에 디비서버에 대한 정보를 뽑아내서 별도 처리
	 * */

	public static void main(String[] args) {
		try {
			Properties p = new Properties();
			p.load(new FileInputStream("src/config/jdbc.properties"));
			// 1. 드라이버를 로딩
			Class.forName(Serverinfo.DRIVER_NAME);
			System.out.println("Driver loading...!!");
			// 2. 디비 연결
			Connection conn = DriverManager.getConnection(Serverinfo.URL, Serverinfo.USER, Serverinfo.PASSWORD);
			System.out.println("Connection");
			
			// 3. Statement 객체 생성 - DELETE
			String query = p.getProperty("jdbc.sql.delete");
			PreparedStatement st = conn.prepareStatement(query);
			// 4. 쿼리문 실행
			st.setInt(1, 1);
			int result = st.executeUpdate();
			System.out.println(result+"명 삭제!");
			// 결과가 잘 나오는지 확인 - SELECT
			String s_query = p.getProperty("jdbc.sql.select");
			PreparedStatement s_st = conn.prepareStatement(s_query);
			ResultSet rs = s_st.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("EMP_ID")+" / " + rs.getString("EMP_NAME") + " / "+ rs.getString("DEPT_TITLE") + " / "+rs.getString("HIRE_DATE"));
			}
			
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
