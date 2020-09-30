package com.user;

public class User {
	private int userId; // primary key
	private String username; // not null, unique
	private String password; // not null
	private String firstName; // not null
	private String lastName; // not null
	private String email; // not null
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
	
	public void setRole() {
		
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

	public void openAccount() {
		// TODO Auto-generated method stub
		
	}

	public void withdraw(int amount) {
		// TODO Auto-generated method stub
		
	}

	public void deposit(Object depositA) {
		// TODO Auto-generated method stub
		
	}

	public boolean hasAccount(int sourceAccountID) {
		// TODO Auto-generated method stub
		return false;
	}
}
