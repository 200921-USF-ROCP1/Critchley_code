package com.services.interfaces;

import com.user.User;

public interface LoginServiceInterface {
	
	public User registerUser(int userId, String username, String password,
			String firstName, String lastName, String email, String role);
	
	
	public User login(String username, String password);
}
