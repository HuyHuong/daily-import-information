package com.vn.vti.core02.model;

import java.sql.SQLException;
import java.util.List;

public class TransactionDao {
	UserTransaction userTran = new UserTransaction();

	public boolean isExisted(String phoneNumber) throws SQLException {
		if (userTran.getDataUser(phoneNumber) != null) {
			return true;
		} else {
			return false;
		}
	}

	public void checkUser(List<DataUsers> userList) throws SQLException {
		for (DataUsers user : userList) {
			if (isExisted(user.getPhoneNumber())) {
				if (userTran.getDataUser(user.getPhoneNumber()).getStatus() == 0) {
					System.out.println("User " + user.getPhoneNumber() + " is out of date!");
				} else {
					user.setTimeTransaction(userTran.lasestTransaction(user.getPhoneNumber()));
					userTran.updateUser(user);
				}
			} else {
				userTran.insertUser(user);
			}
		}
	}
}
