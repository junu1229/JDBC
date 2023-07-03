package person;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import config.Serverinfo;

public class PersonTest {

	private Properties p = new Properties();

	public PersonTest() {
		try {
			p.load(new FileInputStream("src/config/jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 고정적인 반복 -- 디비연결, 자원 반납
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(Serverinfo.URL, Serverinfo.USER, Serverinfo.PASSWORD);
		return conn;
	}

	public void closeAll(Connection conn, PreparedStatement st) throws SQLException {
		if (st != null)
			st.close();
		if (conn != null)
			conn.close();
	}

	public void closeAll(Connection conn, PreparedStatement st, ResultSet rs) throws SQLException {
		closeAll(conn, st);
		if (rs != null)
			rs.close();
	}

	// 변동적인 반복... 비즈니스 로직.. DAO(Database Access Object)
	public void addPerson(String name, String adress) throws SQLException {

		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("jdbc.sql.insert"));
		st.setString(1, name);
		st.setString(2, adress);
		int result = st.executeUpdate();
		if(result == 1) {
			System.out.println(name + "님, 추가!");
		}
		closeAll(conn, st);

	}

	public void removePerson(int id) throws SQLException {

		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("jdbc.sql.delete"));
		st.setInt(1, id);
		int result = st.executeUpdate();
		System.out.println(result + "명 삭제!");
		closeAll(conn, st);

	}

	public void updatePerson(int id, String adress) throws SQLException {

		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("jdbc.sql.update"));
		st.setString(1, adress);
		st.setInt(2, id);
		int result = st.executeUpdate();
		System.out.println(result + "명 수정!");
		closeAll(conn, st);

	}

	public void searchAllPerson() throws SQLException {

		Connection conn = getConnect();
		PreparedStatement s_st = conn.prepareStatement(p.getProperty("jdbc.sql.select"));
		ResultSet rs = s_st.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("ID") + " / " + rs.getString("name") + " / " + rs.getString("address"));
		}
		closeAll(conn, s_st, rs);

	}

	public void viewPerson(int id) throws SQLException {

//		Connection conn = getConnect();
//		PreparedStatement s_st = conn.prepareStatement(p.getProperty("jdbc.sql.select"));
//		ResultSet rs = s_st.executeQuery();
//		while (rs.next()) {
//			if (rs.getInt("ID") == (id)) {
//				System.out.println(rs.getString("ID") + " / " + rs.getString("name") + " / " + rs.getString("address"));
//			}
//		}
//		closeAll(conn, s_st, rs);
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("jdbc.sql.viewperson"));
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		
		if(rs.next()) {
			System.out.println("이름 : " + rs.getString("name"));
		}

	}

	public static void main(String[] args) {

		PersonTest pt = new PersonTest();
		try {
			Class.forName(Serverinfo.DRIVER_NAME);
			System.out.println("Driver loading...!!");
			pt.addPerson("김강우", "서울");
			pt.addPerson("고아라", "제주도");
			pt.addPerson("강태주", "경기도");
			pt.searchAllPerson();
			pt.removePerson(4); // 강태주 삭제
			pt.updatePerson(1, "제주도");
			pt.viewPerson(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}