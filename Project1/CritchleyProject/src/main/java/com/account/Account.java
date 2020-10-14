package com.account;

public class Account {
	private int accountId; // primary key
	private int userID;		// foreign key for user
	private double balance;  // not null
	private AccountStatus status;
	private AccountType type;
	
	public Account() {
		
	}
	
	public Account(int accountID, int userID, double balance, int statusID, int typeID) {
		// TODO Auto-generated constructor stub
		this.accountId = accountID;
		this.setUserID(userID);
		this.balance = balance;
		status = new AccountStatus(statusID);
		type = new AccountType(typeID);
		
	}

	public int getAccountId() {
		return accountId;
	}
	
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	/*public void setStatus(int int1) {
		// TODO Auto-generated method stub
		this.status = new AccountStatus(int1);
	}*/

	/*public void setType(int int1) {
		// TODO Auto-generated method stub
		this.type = new AccountType(int1);
	}*/

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void withdrawAndDeposit(double balance) {
		this.balance += balance;
	}
}
