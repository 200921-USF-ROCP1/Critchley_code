package com.dao;

import java.util.List;

import com.account.Account;

public interface AccountDAOInterface extends GenericDAO<Account> {
	public List<Account> getByUserId(int userId);
}
