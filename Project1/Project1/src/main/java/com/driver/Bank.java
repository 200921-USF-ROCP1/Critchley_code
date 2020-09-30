package com.driver;
import java.util.ArrayList;
import java.util.Scanner;

import com.user.User;
import com.user.types.*;

public class Bank {
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
		int answer = -1;
		switch(answer) {
			case 1:
				currentUser.checkAccountBalance();
				break;
			case 2:
				currentUser.openAccount();
				break;
			case 3:
				// prompt amount to withdraw
				int withdrawAmount = 0;
				currentUser.withdraw(withdrawAmount);
			case 4:
				// prompt amount to deposit
				int depositAmount = 0;
				currentUser.deposit(depositAmount);
			case 5:
				// prompt amount to transfer
				int transferAmount = 0;
				// prompt source account id
				int sourceAccountID = 0;
				if (!currentUser.hasAccount(sourceAccountID)) {
					// prompt error
					break;
				}
				// prompt destination account id
				int destinationAccountID = 0;
				if (!accountExits(destinationAccountID)) {
					// prompt error
					break;
				}
			case 6:
				// edit info
			case 7:
				if (currentUser instanceof Employee) {
					// view all or view specific?
					
				}
				break;
			case 8:
				if (currentUser instanceof Admin) {
					// modify customer info
				}
			case 9:
				logout();
				break;
		}
		
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
		String username;
		String password;
		Scanner scan = new Scanner(System.in); // temporary input (??)
		// prompt for username
		System.out.println("ENTER USERNAME");
		username = scan.nextLine();
		// prompt for password
		System.out.println("ENTER PASSWORD");
		// fetch username and password
		password = scan.nextLine();
		//	- if exists
		if (exists(username,password)) {
			currentUser = new User();
			init();
		} else {
			promptFailureLogin();
		}
		
		
		
	}

	private void promptFailureLogin() {
		// TODO Auto-generated method stub
		
	}

	private boolean exists(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
