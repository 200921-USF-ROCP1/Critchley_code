package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.account.Account;
import com.dao.AccountDAOInterface;
import com.dao.GenericDAO;
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
			PreparedStatement ps = connection.prepareStatement("INSERT INTO accounts (balance, status_id, type_id) VALUES (?, ?, ?);");
			ps.setDouble(1, t.getBalance());
			ps.setInt(2, t.getStatus().getStatusId());
			ps.setInt(3, t.getType().getTypeId());
			
			ps.executeUpdate();
			rs = ps.getResultSet();
			rs.next();
			
			t.setAccountId(rs.getInt("account_id"));
			return t;
		} catch (SQLException e) {
			return null;
		}
	}

	public Account get(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM accounts WHERE id = ?;");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			Account acc = new Account();
			if (rs.next()) {	
				acc.setUserID(rs.getInt("user_id"));
				acc.setAccountId(rs.getInt("account_id"));
				acc.setBalance(rs.getDouble("balance"));
				acc.setStatus(rs.getInt("status_id"));
				acc.setType(rs.getInt("type_id"));	
			}
			
			return acc;
			
		} catch (SQLException e) {
			return null;
		}
		
		
	}

	public Account update(Account t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE accounts SET balance = ?, status_id = ?, type_id = ? WHERE account_id = ?;");
			ps.setDouble(1, t.getBalance());
			ps.setInt(2, t.getStatus().getStatusId());
			ps.setInt(3, t.getType().getTypeId());
			ps.setInt(4, t.getUserID());
			
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
			Account acc = new Account();
			while (rs.next()) {	
				acc.setUserID(rs.getInt("user_id"));
				acc.setAccountId(rs.getInt("account_id"));
				acc.setBalance(rs.getDouble("balance"));
				acc.setStatus(rs.getInt("status_id"));
				acc.setType(rs.getInt("type_id"));	
				
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
				acc.setStatus(rs.getInt("status_id"));
				acc.setType(rs.getInt("type_id"));	
				accounts.add(acc);
			}
			
			return accounts;
			
		} catch (SQLException e) {
			return null;
		}
	}

}
