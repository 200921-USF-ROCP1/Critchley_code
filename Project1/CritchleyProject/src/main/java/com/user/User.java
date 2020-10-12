package com.user;

import java.util.ArrayList;
import java.util.List;

import com.account.*;

public class User {
	private int userId; // primary key
	private String username; // not null, unique
	private String password; // not null
	private String firstName; // not null
	private String lastName; // not null
	private String email; // not null
	ArrayList<Account> accounts;
	private Role role;
	
	public User() {
		
	}
	
	public User(int userId, String username, String password,
				String firstName, String lastName, String email, Role role) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		accounts = new ArrayList<Account>();
	}

	public User(int userId2, String username2, String password2, String firstName2, String lastName2, String email2,
			String role2) {
		// TODO Auto-generated constructor stub
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setRole(int roleId, String role) {
		this.role = new Role(roleId, role);
	}
	
	public Role getRole() {
		return role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void checkAccountBalance(int accountId) {
		// TODO Auto-generated method stub
		Account acc = getAccount(accountId);
		if (acc == null) {
			
		}
		
	}
	
/*
	public Account openAccount(String type) {
		// TODO Auto-generated method stub
		Account acc = new Account();
		accounts.add(acc);
		return acc;
	}
*/
	
	public Account withdrawAndDeposit(double amount, int accountId) {
		Account acc = getAccount(accountId);
		if (acc == null) {
			// prompt Failure
			return null;
		} else if (acc.getBalance() + amount < 0) {
			// prompt failure
			return null;
		} else {
			acc.setBalance(acc.getBalance() + amount);
		}
		return acc;
	}

	public Account getAccount(int accountId) {
		// TODO Auto-generated method stub
		try {
			Account acc = null;
			int i = 0;
			do {
				acc = this.accounts.get(i);
				i++;
			} while (acc.getAccountId() != accountId);
			
			return acc;
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public Account openAccount(int accountID, int userID, double balance, int statusID, int typeID) {
		// TODO Auto-generated method stub
		Account acc = new Account(accountID, userID, balance, statusID, typeID);
		accounts.add(acc);
		return acc;
	}
	
	public void setRole(int role_id) {
		// TODO Auto-generated method stub
		if (role_id == 1) {
			this.role = new Role(1, "Admin");
		} else if (role_id == 2) {
			this.role = new Role(2, "Employee");
		} else if (role_id == 3) {
			this.role = new Role(3, "Standard");
		} else if (role_id == 4) {
			this.role = new Role(4, "Premium");
		}
		
		
	}

	public void setAccount(Account acc) {
		// TODO Auto-generated method stub
		accounts.add(acc);
		
	}
}
