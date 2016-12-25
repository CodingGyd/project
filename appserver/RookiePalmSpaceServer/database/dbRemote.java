package db;

import java.sql.*;

public class db {
	String databaseName = "jsplxslyxlglxns";
	String host = "mysql55.rdsm6x3r7hsk8v9.rds.bj.baidubce.com";
	String port = "3306";
	String username = "guoyading"; // 用户AK
	String password = "123456"; // 用户SK
	String driverName = "com.mysql.jdbc.Driver";
	String dbUrl = "jdbc:mysql://";
	String serverName = host + ":" + port + "/";
	String connName = dbUrl + serverName + databaseName;

	public Connection connect = null;
	public ResultSet rs = null;

	public db() {
		try {

			Class.forName(driverName);
			connect = DriverManager.getConnection(connName, username,
					password);

		} catch (Exception ex) {
			System.out.println("cccccccccc"+ex.getMessage());
		}
	}

	public ResultSet executeQuery(String sql) {
		System.out.println("====connect:" + connect);
		try {

			Statement stmt = connect.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return rs;
	}

	public void executeUpdate(String sql) {
		System.out.println("====connect:" + connect);
		Statement stmt = null;
		rs = null;
		try {

			stmt = connect.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException ex) {
			System.err.println(ex.getMessage());

		}

	}

}
