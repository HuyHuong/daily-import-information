package com.vn.vti.core02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HistoryTransaction {

	PreparedStatement preStmt = null;
	private Connection con;

	public HistoryTransaction() {
		this.con = ConnectionDB.getConnection();
	}

	public void insertHistoryTransaction(List<DataUsers> transactionList) throws SQLException {
		String queryInsertTransaction = "insert into history_transaction (phone_number, amount, time_transaction, status) values(?, ?, ?, ?)";
		preStmt = con.prepareStatement(queryInsertTransaction);
		for (DataUsers user : transactionList) {
			preStmt.setString(1, user.getPhoneNumber());
			preStmt.setInt(2, user.getAmount());
			preStmt.setTimestamp(3, user.getTimeTransaction());
			preStmt.setInt(4, user.getStatus());
			preStmt.addBatch();
		}
		preStmt.executeBatch();
	}
}
