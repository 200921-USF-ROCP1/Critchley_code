package com.dao;

import com.user.User;

public interface UserDAOInterface extends GenericDAO<User> {
	
	public User getByUsername(String s);
	
	public User getByEmail(String s);
}
