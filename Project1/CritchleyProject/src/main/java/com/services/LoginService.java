package com.services;

import java.sql.Connection;
import com.dao.impl.UserDAOImpl;
import com.services.interfaces.LoginServiceInterface;
import com.user.*;

public class LoginService implements LoginServiceInterface {
	static User currentUser;
	private UserDAOImpl userDAO;
	private Connection connection;
	
	public LoginService() {
		// TODO Auto-generated constructor stub
		connection = ConnectionService.getConnection();
		userDAO = new UserDAOImpl();
	}

	public User registerUser(User user) {
		// TODO Auto-generated method stub
		// check if username or email is taken in DB
		if (isTaken(user.getUsername(), user.getEmail())) {
			return null;
		} else {
			
			userDAO.create(user);
			return user;
		}
	}

	public User login(String username, String password) {
		
		User temp = userDAO.getByString("username", username);
		if (temp != null) {
			if (password.equals(temp.getPassword())) {	
				return temp;
			} else {
				return null;
			}
		} else {
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

}
