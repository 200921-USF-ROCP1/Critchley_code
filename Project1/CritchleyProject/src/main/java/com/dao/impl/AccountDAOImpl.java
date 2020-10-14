package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.account.Account;
import com.account.AccountStatus;
import com.account.AccountType;
import com.dao.AccountDAOInterface;
import com.services.ConnectionService;

public class AccountDAOImpl implements AccountDAOInterface {
	Connection connection;
	ResultSet rs;
	
	public AccountDAOImpl(){
		// TODO Auto-generated constructor stub
		this.connection = ConnectionService.getConnection();
		rs = null;
	}

	public Account create(Account t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO accounts (user_id, balance, status_id, type_id) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, t.getUserID());
			ps.setDouble(2, t.getBalance());
			ps.setInt(3, t.getStatus().getStatusId());
			ps.setInt(4, t.getType().getTypeId());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			
			t.setAccountId(rs.getInt(1));
			return t;
		} catch (SQLException e) {
			return null;
		}
	}

	public Account get(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts WHERE account_id = ?;");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				Account acc = new Account();
				acc.setUserID(rs.getInt("user_id"));
				acc.setAccountId(rs.getInt("account_id"));
				acc.setBalance(rs.getDouble("balance"));
				AccountStatus as = new AccountStatus(rs.getInt("status_id"));
				acc.setStatus(as);
				AccountType at = new AccountType(rs.getInt("type_id"));
				acc.setType(at);	
				return acc;
			}
			
			return null;
			
		} catch (SQLException e) {
			return null;
		}
		
		
	}

	public Account update(Account t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE accounts SET balance = ?, status_id = ?, type_id = ?, user_id = ? WHERE account_id = ?;");
			ps.setDouble(1, t.getBalance());
			ps.setInt(2, t.getStatus().getStatusId());
			ps.setInt(3, t.getType().getTypeId());
			ps.setInt(4, t.getUserID());
			ps.setInt(5, t.getAccountId());
			
			ps.executeUpdate();
			return t;
		} catch (SQLException e) {
			return null;
		}
		
	}

	public void delete(Account t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM accounts WHERE account_id = ?;");
			ps.setInt(1, t.getAccountId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			
		}
	}

	public List<Account> getAll() {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts;");
			
			rs = ps.executeQuery();
			List<Account> allAccounts = new ArrayList<Account>();
			
			while (rs.next()) {	
				Account acc = new Account();
				acc.setUserID(rs.getInt("user_id"));
				acc.setAccountId(rs.getInt("account_id"));
				acc.setBalance(rs.getDouble("balance"));
				AccountStatus as = new AccountStatus(rs.getInt("status_id"));
				acc.setStatus(as);
				AccountType at = new AccountType(rs.getInt("type_id"));
				acc.setType(at);		
				
				allAccounts.add(acc);
			}
			
			return allAccounts;
			
		} catch (SQLException e) {
			return null;
		}
		
	}
	
	public List<Account> getByUserId(int userId) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts WHERE user_id = ?;");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			Account acc; 
			List<Account> accounts = new ArrayList<Account>();
			
			while (rs.next()) {	
				acc = new Account();
				acc.setUserID(rs.getInt("user_id"));
				acc.setAccountId(rs.getInt("account_id"));
				acc.setBalance(rs.getDouble("balance"));
				AccountStatus as = new AccountStatus(rs.getInt("status_id"));
				acc.setStatus(as);
				AccountType at = new AccountType(rs.getInt("type_id"));
				acc.setType(at);	
				accounts.add(acc);
			}
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
