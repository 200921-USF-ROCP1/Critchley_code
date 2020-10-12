package com.services.interfaces;

import com.user.User;

public interface LoginServiceInterface {
	
	public User registerUser(User user);
	
	
	public User login(String username, String password);
}
