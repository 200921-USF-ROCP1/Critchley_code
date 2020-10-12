package com.services.interfaces;

import java.util.List;

import com.account.Account;
import com.user.User;

public interface EmployeeServiceInterface extends StandardServiceInterface {
	
	public List<User> viewAllUsers();
	
	public User viewUser(int userId);
	
	public Account viewAccount(int accountId);
	
	public List<Account> viewAllAccounts();
	
	public List<Account> accountsByStatus(int statusId);
	
	public List<Account> accountsByUser(int userId);
}
