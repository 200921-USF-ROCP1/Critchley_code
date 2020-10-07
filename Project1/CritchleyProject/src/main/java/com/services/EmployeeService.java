package com.services;

import java.sql.Connection;
import java.util.List;

import com.account.Account;
import com.dao.impl.AccountDAOImpl;
import com.dao.impl.UserDAOImpl;
import com.services.interfaces.EmployeeServiceInterface;
import com.user.User;

public class EmployeeService extends StandardService  implements EmployeeServiceInterface {
	
	static User currentUser;
	private UserDAOImpl userDAO;
	private AccountDAOImpl accDAO;
	private Connection connection;
	
	public EmployeeService()  {
		// TODO Auto-generated constructor stub
		connection = ConnectionService.getConnection();
		userDAO = new UserDAOImpl(connection);
		accDAO = new AccountDAOImpl();
	}

	public void viewAllUsers() {
		
		List<User> allUsers = userDAO.getAll();
		// TODO do something with this
	}

	public void viewUser(int userId) {
		// TODO Auto-generated method stub
		User curView = userDAO.get(userId);
		
		if (curView == null) {
			// TODO return failure
		} else {
			// TODO return User
		}
	}

	public void viewAccount(int accountId) {
		// TODO Auto-generated method stub
		Account acc = accDAO.get(accountId);
		
		if (acc == null) {
			// TODO return failure
		} else {
			// TODO return Account
		}
		
	}

}
