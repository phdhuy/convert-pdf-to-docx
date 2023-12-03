package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		String dbURL = "jdbc:mysql://127.0.0.1:3306/file_converter";
		String username = "root";
		String password = "";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = (Connection) DriverManager.getConnection(dbURL, username, password);
		if (conn != null) {
			System.out.println("Kết nối thành công");
			return conn;
		}
		return null;
	}
}
