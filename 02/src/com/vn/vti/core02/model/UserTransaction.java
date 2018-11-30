package com.vn.vti.core02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserTransaction {

	PreparedStatement preStmt = null;
	ResultSet rs = null;
	private Connection con;

	public UserTransaction() {
		this.con = ConnectionDB.getConnection();
	}

	public void insertUser(DataUsers user) throws SQLException {
		String queryInsertUser = "insert into transaction(phone_number, amount, time_transaction, status) values(?, ?, ?, ?)";
		try {
			preStmt = con.prepareStatement(queryInsertUser);
			preStmt.setString(1, user.getPhoneNumber());
			preStmt.setInt(2, user.getAmount());
			preStmt.setTimestamp(3, user.getTimeTransaction());
			preStmt.setInt(4, user.getStatus());
			preStmt.executeUpdate();
		} finally {
			con.close();
		}
	}

	public void updateUser(DataUsers user) throws SQLException {
		String queryUpdateUser = "update transaction set amount=?, time_transaction=?, status=? where phone_number=?";
		preStmt = con.prepareStatement(queryUpdateUser);
		preStmt.setInt(1, user.getAmount());
		preStmt.setTimestamp(2, user.getTimeTransaction());
		preStmt.setInt(3, user.getStatus());
		preStmt.setString(4, user.getPhoneNumber());
		preStmt.executeUpdate();
	}

	public Timestamp lasestTransaction(String phoneNumber) throws SQLException {
		Timestamp lastestTime = null;
		String queryLasestTransaction = "select max(time_transaction) from history_transaction where phone_number=?";
		preStmt = con.prepareStatement(queryLasestTransaction);
		preStmt.setString(1, phoneNumber);
		rs = preStmt.executeQuery();
		while (rs.next()) {
			lastestTime = rs.getTimestamp("max(time_transaction)");
		}
		return lastestTime;
	}

	public DataUsers getDataUser(String phoneNumber) throws SQLException {
		String queryGetByID = "select * from transaction where phone_number=?";
		DataUsers user = null;
		preStmt = con.prepareStatement(queryGetByID);
		preStmt.setString(1, phoneNumber);
		rs = preStmt.executeQuery();
		while (rs.next()) {
			user = new DataUsers(rs.getString("phone_number"), rs.getInt("amount"), rs.getTimestamp("time_transaction"),
					rs.getInt("status"));
		}
		return user;
	}
}
