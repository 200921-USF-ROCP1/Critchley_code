package com.driver;

import java.util.Scanner;

import com.user.Role;
import com.user.User;
import com.user.types.Admin;
import com.user.types.Employee;

public class Driver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// prompt for login or create account
		int answer = 0;
	
		
		if (answer == 0) {
			createAccountScreen();
		} else if (answer == 1) {
			loginScreen();
		}
		
	}

	private static void createAccountScreen() {
		// TODO Auto-generated method stub
		// get user info
		int userId = 0;
		String username = "";
		String password = "";
		String firstname = ""; 
		String lastname = "";
		String email = "";
		Role role = new Role();
		LoginService service = new LoginService();
		service.createUser(userId, username, password, firstname, lastname, email, role);
		init();
	}

	private static void loginScreen() {
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
		LoginService service = new LoginService();
		service.login(username, password); // maybe return user?
		init();
		
		
	}

	private static void init() {
		// TODO Auto-generated method stub
		LoginService service = new LoginService();
		
		// get input 
		int answer = -1;
		int accountId;
		String roleType = service.getUser().getRole().getRole();
		
		switch(answer) {
	
			case 1:
				service.checkAccountBalance();
				break;
			case 2:
				// get account type
				service.openAccount(1);
				break;
			case 3:
				// prompt amount to withdraw
				int withdrawAmount = 0;
				accountId = 0;
				service.withdraw(withdrawAmount, accountId);
			case 4:
				// prompt amount to deposit
				int depositAmount = 0;
				accountId = 0;
				service.deposit(depositAmount, accountId);
			case 5:
				// prompt amount to transfer
				int transferAmount = 0;
				// prompt source account id
				int sourceAccountID = 0;
				
				// prompt destination account id
				int destinationAccountID = 0;
				// try to transfer funds
				service.transferFunds(sourceAccountID, destinationAccountID, transferAmount);
			case 6:
				// edit info
				// get user info? 
				// change user info
				service.updateInfo();
				
			case 7:
				if (roleType.equals("Employee") || roleType.equals("Admin")) {
					// view all or view specific?
					
				}
				break;
			case 8:
				if (roleType.equals("Admin")) {
					// modify customer info
				}
			case 9:
				logout();
				break;
		}
	}

	private static void logout() {
		// TODO Auto-generated method stub
		
	}

	private static void promptFailureLogin() {
		// TODO Auto-generated method stub
		
	}
	
}
