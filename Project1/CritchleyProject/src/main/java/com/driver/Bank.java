package com.driver;
import java.util.ArrayList;
import java.util.Scanner;

import com.user.User;
import com.user.types.*;

public class Bank {
	
	// database
	ArrayList<Standard> standards = new ArrayList<Standard>();
	ArrayList<Employee> employees = new ArrayList<Employee>();
	ArrayList<Admin> admins = new ArrayList<Admin>();
		
	User currentUser; 
	
	public Bank() {
		createAdmins();
		createEmployees();
		createStandards();
		
	}

	private void createStandards() {
		// TODO Auto-generated method stub
		
	}

	private void createEmployees() {
		// TODO Auto-generated method stub
		
	}

	private void createAdmins() {
		// TODO Auto-generated method stub
		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		
	}

	private boolean accountExits(int destinationAccountID) {
		// TODO Auto-generated method stub
		return false;
	}

	private void logout() {
		// TODO Auto-generated method stub
		
	}

	public void createAccount() {
		// TODO Auto-generated method stub
		// prompt firstname and lastname
		// prompt username 
		// prompt password
		// prompt email
		// prompt account type or create both
		// generate account id
		// add to DB ?
		
		currentUser = new User();
		init();
	}

	public void login() {
		// TODO Auto-generated method stub
		
		
		
	}

	private void promptFailureLogin() {
		// TODO Auto-generated method stub
		
	}

	private boolean exists(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
