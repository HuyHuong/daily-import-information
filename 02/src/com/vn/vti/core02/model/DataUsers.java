package com.vn.vti.core02.model;

import java.sql.Timestamp;

public class DataUsers {
	private String phoneNumber;
	private int amount;
	private Timestamp timeTransaction;
	private int status;

	public DataUsers() {
	}

	public DataUsers(String phoneNumber, int amount, Timestamp timeTransaction, int status) {
		super();
		this.phoneNumber = phoneNumber;
		this.amount = amount;
		this.timeTransaction = timeTransaction;
		this.status = status;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getTimeTransaction() {
		return timeTransaction;
	}

	public void setTimeTransaction(Timestamp timeTransaction) {
		this.timeTransaction = timeTransaction;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
