package com.user.types;

import com.account.*;
import com.user.*;

public class Standard extends User {

	public Standard() {
		// TODO Auto-generated constructor stub
	}
	
	public Standard(int userId, String username, String password,
			String firstName, String lastName, String email, Role role) {
		super(userId, username, password, firstName, lastName, email, role);
	}
	
	
	public void displayAccountInfo() {
		
	}
	
	public void withdraw(int amount) {
		
	}
	
	public void deposit(int amount) {
		
	}
	
	public void transfer(int amount, Account src, Account dest) {
		
	}

}
