package com.services;

import com.dao.impl.UserDAOImpl;
import com.services.interfaces.LoginServiceInterface;
import com.user.*;

public class LoginService implements LoginServiceInterface {
	static User currentUser;
	private UserDAOImpl userDAO;
	
	public LoginService() {
		// TODO Auto-generated constructor stub
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
		
		User temp = userDAO.getByUsername(username);
		if (temp != null) {
			if (password.equals(temp.getPassword())) {	
				return temp;
			} 
		}
		
		return null;
		
	}
	
	
	// Access DB checking for any existing usernames or emails
		private boolean isTaken(String username, String email) {
			// TODO Auto-generated method stub
			// check DB for username and email
			User found = userDAO.getByUsername(username);
			if (found != null)
				return true;
			
			found = userDAO.getByEmail(email);
			if (found != null )
				return true;
			
			return false;
		}

}
