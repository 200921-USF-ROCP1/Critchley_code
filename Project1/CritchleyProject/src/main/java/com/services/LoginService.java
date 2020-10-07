package com.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.account.Account;
import com.dao.impl.*;
import com.services.interfaces.LoginServiceInterface;
import com.user.*;

public class LoginService implements LoginServiceInterface {
	static User currentUser;
	private UserDAOImpl userDAO;
	private AccountDAOImpl accDAO;
	private Connection connection;
	
	public LoginService() {
		// TODO Auto-generated constructor stub
		connection = ConnectionService.getConnection();
		userDAO = new UserDAOImpl(connection);
		accDAO = new AccountDAOImpl();
	}

	public User registerUser(int userId, String username, String password, String firstName, String lastName,
			String email, String role) {
		// TODO Auto-generated method stub
		// check if username or email is taken in DB
		if (isTaken(username, email)) {
			promptCreateAccountFailure();
			return null;
		} else {
			currentUser = new User(userId, username, password, firstName, lastName, email, role);
			return currentUser;
		}
	}

	public User login(String username, String password) {
		// TODO Auto-generated method stub
		User temp = userDAO.getByString("username", username);
		if (temp != null) {
			if (password.equals(temp.getPassword())) {
				currentUser = temp;
				return temp;
			} else {
				promptLoginFailure();
				return null;
			}
		} else {
			promptLoginFailure();
			return null;
		}
		
	}
	
	
	// Access DB checking for any existing usernames or emails
		private boolean isTaken(String username, String email) {
			// TODO Auto-generated method stub
			// check DB for username and email
			User found = userDAO.getByString("username", username);
			if (found != null)
				return true;
			
			found = userDAO.getByString("email", email);
			if (found != null )
				return true;
			
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

}
