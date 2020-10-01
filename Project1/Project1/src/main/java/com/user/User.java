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
	List<Account> accounts;
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

	public void checkAccountBalance() {
		// TODO Auto-generated method stub
		
	}
	
/*
	public Account openAccount(String type) {
		// TODO Auto-generated method stub
		Account acc = new Account();
		accounts.add(acc);
		return acc;
	}
*/
	
	public void withdrawAndDeposit(int amount, int accountId) {
		
	}
/*
	public void withdraw(int amount) {
		// TODO Auto-generated method stub
		
			
	}

	public void deposit(int depositA) {
		// TODO Auto-generated method stub
		
	}
*/

	public boolean hasAccount(int sourceAccountID) {
		// TODO Auto-generated method stub
		return false;
	}

	public Account getAccount() {
		// TODO Auto-generated method stub
		
		return null;
	}

	public Account openAccount(int accountID, int userID, double balance, int statusID, int typeID) {
		// TODO Auto-generated method stub
		Account acc = new Account(accountID, userID, balance, statusID, typeID);
		accounts.add(acc);
		return acc;
	}
}
