package com.driver;

import com.account.Account;
import com.user.*;

public class LoginService {
	
	static User currentUser;
	
	public LoginService() {
		// TODO Auto-generated constructor stub
		
	}

/*
 * 
 * --- LOGIN SERVICES ---
 * 
 */
	
	// Create account and create user
	// 	- maybe return User
	public void createUser(int userId, String username, String password,
			String firstName, String lastName, String email, Role role) {
		// check if username or email is taken in DB
		if (isTaken(username, email)) {
			promptCreateAccountFailure();
		} else {
			currentUser = new User(userId, username, password, firstName, lastName, email, role);
		}
	}
	
	// Access DB checking for any existing usernames or emails
	private boolean isTaken(String username, String email) {
		// TODO Auto-generated method stub
		// check DB for username and email
		return false;
	}

	

	// login and create User
	// 	- maybe return User
	public void login(String username, String password) {
		if (userExists(username, password)) {
			currentUser = new User(); // fill user from DB
			
		} else {
			promptLoginFailure();
		}
	}
	
	// check DB for username and password
	private boolean userExists(String username, String password) {
		// TODO Auto-generated method stub
		// check DB for username as key, check for matching password
		return false;
	}
	
	private boolean accountExists(int destinationAccountID) {
		// check if account exists,
		// maybe return type Account if so?
		return false;
	}

	// on login wrong username or password
	private void promptLoginFailure() {
		// TODO Auto-generated method stub
		// somehow return login failure?		
	}
	
	// username or email was taken
	private void promptCreateAccountFailure() {
		// TODO Auto-generated method stub
		
	}

/*
 * 
 * --- IN BANK METHODS ---
 * 	
 */
	
	public void checkAccountBalance() {
		// TODO Auto-generated method stub
		double balance = currentUser.getAccount().getBalance();
		
	}

	public void openAccount(int typeID) {
		// TODO Auto-generated method stub
		// generate AccountID
		int accountID = 0;
		// get status ID, most likely PENDING = 1, OPEN = 2
		int statusID = 1;
		int userID = currentUser.getUserId();
		double balance = 0;
		
		Account acc = currentUser.openAccount(accountID, userID, balance, statusID, typeID);
		// send to DB
		
	}

	public void withdraw(int withdrawAmount, int accountId) {
		// TODO Auto-generated method stub
		withdrawAmount *= -1;
		currentUser.withdrawAndDeposit(withdrawAmount, accountId);
		
		// update DB
		
	}

	public void deposit(int depositAmount, int accountId) {
		// TODO Auto-generated method stub
		currentUser.withdrawAndDeposit(depositAmount, accountId);
		
		// update DB, account ID
		
	}

	public void transferFunds(int sourceAccountID, int destinationAccountID, int transferAmount) {
		// TODO Auto-generated method stub
		if (!currentUser.hasAccount(sourceAccountID)) {
			// transfer funds fail 
		}
		
		if (!accountExists(destinationAccountID)) {
			// transfer funds fail 
		}
		
		if (transferAmount > currentUser.getAccount().getBalance()) {
			// transfer funds fail
		}
		
		withdraw(transferAmount, sourceAccountID);
		
		// update DB
		
	}

	public void updateInfo() {
		// TODO Auto-generated method stub
		// call setters for current user
		// update DB
	}

	public User getUser() {
		// TODO Auto-generated method stub
		return currentUser;
	}
	
	
	
}
