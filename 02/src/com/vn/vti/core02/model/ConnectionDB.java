package com.vn.vti.core02.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDB {
	private static final String URL = "jdbc:mysql://localhost:3306/core02?autoReconnect=true&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "12345";

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("connect successfully!");
		} catch(Exception e) {
			System.out.println("connect failure!");
			e.printStackTrace();
		}
		return conn;
	}

}
