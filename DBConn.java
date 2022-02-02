package jdbc_semi;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	String driver = "com.mysql.cj.jdbc.Driver"; // 고정된 상수(final)
	String path = "jdbc:mysql://localhost:3306/git";
	String user = "hong";
	String pwd = "1111";

	Connection conn;

	public DBConn() {
		try {
			Class.forName(driver); // JDBC 드라이버 load
			conn = DriverManager.getConnection(path, user, pwd); // Connection 객체 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() { // getter
		return conn;
	}
}
