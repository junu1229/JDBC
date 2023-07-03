
package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
public class DBConnectionTest1 {
	/*
	 * JDBC(Java DataBase Connectivity)
	 * 	- 자바에서 표준화된 방법으로 다양한 데이터 베이스에 접속할 수 있도록 설계된 인터페이스
	 * */
	public static void main(String[] args) {
		// JDBC 작업 4단계
		
		try {
			// 1. 드라이버를 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading...!!"); // 파일 - build path - configure build path - 오라클 파일 불러오기
			
			//2. 데이터베이스와 연결
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kh","system");
			System.out.println("DB Connection...!");
				
			//3. Statement 객체 생성 - SELECT
			String query = "SELECT * FROM employee";
			PreparedStatement st = conn.prepareStatement(query); // "SELECT * FROM employee"이걸 jdbc에서 처리할 수 있는 객체를 만들기 위해 Statement를 상속 받은 것
			
			//4. 쿼리문 실행
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {	//rs 다음 것들이 있는지
				//어떤 컬럼들을 가져올지 명시
				String empId = rs.getString("emp_id");
				String empName = rs.getString("emp_name");
				String deptCode = rs.getString("dept_code");
				String jobCode = rs.getString("job_code");
				int salary = rs.getInt("salary");
				float bonus = rs.getFloat("bonus");
				Date hireDate = rs.getDate("hire_date");
				char entYn = rs.getString("ent_yn").charAt(0);
				
				System.out.println(empId + " / " + empName + " / " +  deptCode + " / " + jobCode + " / " + salary + " / " + bonus + " / " + hireDate + " / ");
			}
			
			} catch (SQLException e) {
				e.printStackTrace();
			
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}