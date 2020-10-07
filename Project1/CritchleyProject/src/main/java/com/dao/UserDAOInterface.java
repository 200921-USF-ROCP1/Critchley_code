package com.dao;

import com.user.User;

public interface UserDAOInterface extends GenericDAO<User> {
	
	public User getByString(String type, String s);
}
