package com.services;

import java.sql.Connection;

import com.dao.impl.AccountDAOImpl;
import com.dao.impl.UserDAOImpl;
import com.services.interfaces.AdminServiceInterface;
import com.user.User;

public class AdminService extends EmployeeService implements AdminServiceInterface {
	
	static User currentUser;
	private UserDAOImpl userDAO;
	private AccountDAOImpl accDAO;
	private Connection connection;
	
	public AdminService() {
		connection = ConnectionService.getConnection();
		userDAO = new UserDAOImpl(connection);
		accDAO = new AccountDAOImpl();
	}

	public void modifyUserById(int userId) {
		// TODO Auto-generated method stub
		
		User u = null;
		
		userDAO.update(u);
		
		
	}

	public void modifyUserByEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	public void modifyUserByUsername(String username) {
		// TODO Auto-generated method stub
		
	}

	public void modifyAccount(int accountId) {
		// TODO Auto-generated method stub
		
	}

}
