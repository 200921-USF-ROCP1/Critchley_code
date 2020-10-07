package com.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.account.Account;
import com.dao.impl.*;
import com.services.interfaces.StandardServiceInterface;
import com.user.*;

public class StandardService implements StandardServiceInterface {
	
	static User currentUser;
	private UserDAOImpl userDAO;
	private AccountDAOImpl accDAO;
	private Connection connection;
	
	public StandardService() {
		// TODO Auto-generated constructor stub
		connection = ConnectionService.getConnection();
		userDAO = new UserDAOImpl(connection);
		accDAO = new AccountDAOImpl();
	}
	
	

	public void updateCurrentUserInfo(String username, String password, String firstName, String lastName, String email) {
		// TODO Auto-generated method stub
		User temp = null;
		if ((temp = userDAO.getByString("username", username)) != null) {
			// username taken
		} else if((temp = userDAO.getByString("email", email)) != null) {
			// email taken
		}
		currentUser = temp;
		
		currentUser.setUsername(username);
		currentUser.setPassword(password);
		currentUser.setEmail(email);
		currentUser.setFirstName(firstName);
		currentUser.setLastName(lastName);
		
		userDAO.update(currentUser);
		
		
	}
	
	public void withdrawal(double withdrawalAmount, int accountId) {
		
		if (currentUser.getAccount(accountId) == null) {
			// TODO no access to this account
		}
		
		if (withdrawalAmount < currentUser.getAccount(accountId).getBalance()) {
			withdrawalAmount *= -1;
			deposit(withdrawalAmount, accountId);
		}
		// TODO else not available
	}

	public void deposit(double depositAmount, int accountId) {
		// TODO Auto-generated method stub
		Account acc = currentUser.withdrawAndDeposit(depositAmount, accountId);
		if (acc == null) {
			// TODO not available 
		} else {
			accDAO.update(acc);
		}
		
	}

	public void transfer(int sourceAccountId, int destinationAccountId, double transferAmount) {
		Account srcTemp = null;
		Account destTemp = null;
		if ((srcTemp = currentUser.getAccount(sourceAccountId)) == null) {
			// TODO transfer funds fail 
		} else if ((destTemp = accountExists(destinationAccountId)) == null) {
			// TODO transfer funds fail 
		} else if (transferAmount > srcTemp.getBalance()) {
			// TODO transfer funds fail
		} else {
			withdrawal(transferAmount, sourceAccountId);
			destTemp.setBalance(transferAmount + destTemp.getBalance());
			accDAO.update(destTemp);
		}
	}
	
	private Account accountExists(int accountId) {
		// check if account exists,
		// maybe return type Account if so?
		Account temp = null;
		temp = accDAO.get(accountId);
		return temp;
	}

}
