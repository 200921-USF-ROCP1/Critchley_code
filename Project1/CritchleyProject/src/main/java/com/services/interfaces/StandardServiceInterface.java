package com.services.interfaces;

import java.util.List;

import com.account.Account;
import com.user.User;

public interface StandardServiceInterface {
	
	public User updateUserInfo(User user);
	
	public boolean withdraw(double withdrawAmount, int accountId);
	
	public boolean deposit(double depositAmount, int accountId);
	
	public boolean transfer(int sourceAccountId, int destinationAccountId, double transferAmount);
	
	public List<Account> accountsByStatus(int statusId);
}
