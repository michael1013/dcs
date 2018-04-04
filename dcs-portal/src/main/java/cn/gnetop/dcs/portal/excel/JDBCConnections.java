package cn.gnetop.dcs.portal.excel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnections {
	public static Connection getConnection() {
		String url = "jdbc:mysql://114.112.58.126:3306/dcsdb?useUnicode=true&amp;characterEncoding=UTF-8&useSSL=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull";
		String username = "dcsdb";
		String password = "Dcsdb2017";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
