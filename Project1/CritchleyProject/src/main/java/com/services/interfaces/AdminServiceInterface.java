package com.services.interfaces;

import com.account.Account;
import com.user.User;

public interface AdminServiceInterface extends EmployeeServiceInterface {
	
	public User modifyUser(User user);
	
	public Account modifyAccount(Account acc);
}
