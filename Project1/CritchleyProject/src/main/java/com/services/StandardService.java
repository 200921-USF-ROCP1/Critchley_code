package com.services;

import java.util.ArrayList;
import java.util.List;

import com.account.Account;
import com.dao.impl.*;
import com.services.interfaces.StandardServiceInterface;
import com.user.*;

public class StandardService implements StandardServiceInterface {
	
	private UserDAOImpl userDAO;
	private AccountDAOImpl accDAO;
	
	public StandardService() {
		// TODO Auto-generated constructor stub
		userDAO = new UserDAOImpl();
		accDAO = new AccountDAOImpl();
	}
	
	

	public User updateUserInfo(User user) {
		
		if (userDAO.getByString("username", user.getUsername()) != null) {
			// username taken
			return null;
		} else if(userDAO.getByString("email", user.getEmail()) != null) {
			// email taken
			return null;
		}
		
		return userDAO.update(user);
		
		
	}
	
	public boolean withdraw(double withdrawAmount, int accountId) {
		
		// change to do something withn sessions
		//if (currentUser.getAccount(accountId) == null) {
		//	return false;
		//}
		// not enough in source account
		if (withdrawAmount < accDAO.get(accountId).getBalance()) {
			withdrawAmount *= -1;
			return deposit(withdrawAmount, accountId);
		}
		return false;
		
	}

	public boolean deposit(double depositAmount, int accountId) {
		
		Account acc = accDAO.get(accountId);
		if (acc == null) {
			return false;
		} else {
			acc.withdrawAndDeposit(depositAmount);
			accDAO.update(acc);
			return true;
		}
		
	}

	public boolean transfer(int sourceAccountId, int destinationAccountId, double transferAmount) {
		Account srcTemp = null;
		Account destTemp = null;
		
		// or srcTemp does not belong to current standard user
		if ((srcTemp = accDAO.get(sourceAccountId)) == null) {
			return false;
		} else if ((destTemp = accDAO.get(destinationAccountId)) == null) {
			return false;
		} else if (transferAmount > srcTemp.getBalance()) {
			return false;
		} else {
			withdraw(transferAmount, sourceAccountId);
			destTemp.setBalance(transferAmount + destTemp.getBalance());
			accDAO.update(destTemp);
			return true;
		}
	}

	public List<Account> accountsByStatus(int statusId) {
		// TODO Auto-generated method stub
		List<Account> accounts = accDAO.getAll();
		List<Account> retAccs = new ArrayList<Account>();
		for (Account a: accounts) {
			if (a.getStatus().getStatusId() == statusId)
				retAccs.add(a);
		}
		return retAccs;
	}
	

}
