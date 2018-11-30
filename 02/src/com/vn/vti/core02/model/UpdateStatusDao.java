package com.vn.vti.core02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class UpdateStatusDao {

	private static Logger logger = Logger.getLogger(UpdateStatusDao.class);

	public void updateStatus() throws SQLException {
		Connection con = ConnectionDB.getConnection();
		PreparedStatement preStmt = null;
		String queryCheckChargeTime = "update transaction set status = 0 where time_transaction < ((NOW() - INTERVAL 30 DAY))";
		try {
			preStmt = con.prepareStatement(queryCheckChargeTime);
			preStmt.executeUpdate();
		} finally {
			if (preStmt != null) {
				try {
					preStmt.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}
}
