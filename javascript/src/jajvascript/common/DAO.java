package jajvascript.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	static Connection conn = null;

	public static Connection getConnect() {
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String usr = "hr";
		String passwd = "hr";
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, usr, passwd);
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}