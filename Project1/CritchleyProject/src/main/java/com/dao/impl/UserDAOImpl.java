package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.account.Account;
import com.dao.GenericDAO;
import com.dao.UserDAOInterface;
import com.services.ConnectionService;
import com.user.User;

public class UserDAOImpl implements UserDAOInterface{
	Connection connection;
	ResultSet rs;
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
		this.connection = ConnectionService.getConnection();
	}

	public User create(User t) {
		// TODO Auto-generated method stub
		try {
			//ArrayList<Account> accs = t.getAccounts();
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO users (username, password, firstName, lastName, email, role_id) VALUES (?,?,?,?,?,?);");
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(5, t.getEmail());
			ps.setInt(6, t.getRole().getRoleId());
			
			ps.executeUpdate();
			rs = ps.getResultSet();
			rs.next();
			
			t.setUserId(rs.getInt("user_id"));
			return t;
			//return rs.getInt("user_id");
		} catch (SQLException e) {
			return null;
		}
		
	}

	public User get(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?;");
			ps.setInt(1, id);
			// maybe get accounts??
			rs = ps.executeQuery();
			AccountDAOImpl accDAO = new AccountDAOImpl();
			User cur = new User();
			if (rs.next() ) {
				cur.setUsername(rs.getString("username"));
				cur.setUserId(rs.getInt("user_id"));
				cur.setFirstName(rs.getString("firstName"));
				cur.setLastName(rs.getString("lastName"));
				cur.setEmail(rs.getString("email"));
				cur.setRole(rs.getInt("role_id"));
				// set Accounts
				
				
				List<Account> accounts = accDAO.getByUserId(cur.getUserId());
				for (Account a : accounts) {
					cur.setAccount(a);
				}
			}
			return cur;
			
		} catch (SQLException e) {
			
		}
		return null;
	}
	
	public User getByString(String type, String s) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE ? = ?;");
			ps.setString(1, type);
			ps.setString(2, s);
			// maybe get accounts??
			rs = ps.executeQuery();
			AccountDAOImpl accDAO = new AccountDAOImpl();
			User cur = new User();
			if (rs.next() ) {
				cur.setUsername(rs.getString("username"));
				cur.setUserId(rs.getInt("user_id"));
				cur.setFirstName(rs.getString("firstName"));
				cur.setLastName(rs.getString("lastName"));
				cur.setEmail(rs.getString("email"));
				cur.setRole(rs.getInt("role_id"));
				// set Accounts
				
				List<Account> accounts = accDAO.getByUserId(cur.getUserId());
				for (Account a : accounts) {
					cur.setAccount(a);
				}
			}
			return cur;
			
		} catch (SQLException e) {
			
		}
		return null;
	}

	public User update(User t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE users SET user_id = ?, username = ?, password = ?, firstName = ?, lastName = ?, email = ?, role_id = ?;");
			ps.setInt(1, t.getUserId());
			ps.setString(2, t.getUsername());
			ps.setString(3, t.getPassword());
			ps.setString(4, t.getFirstName());
			ps.setString(5, t.getLastName());
			ps.setString(6, t.getEmail());
			ps.setInt(6, t.getRole().getRoleId());
			
			ps.executeUpdate();
			return t;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	public void delete(User t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE user_id = ?;");
			ps.setInt(1, t.getUserId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users;");
			// maybe get accounts??
			rs = ps.executeQuery();
			AccountDAOImpl accDAO = new AccountDAOImpl();
			List<User> allUsers = new ArrayList<User>();
			User cur = null;
			Account acc = null;
			while (rs.next()) {
				cur = new User();
				cur.setUsername(rs.getString("username"));
				cur.setUserId(rs.getInt("user_id"));
				cur.setFirstName(rs.getString("firstName"));
				cur.setLastName(rs.getString("lastName"));
				cur.setEmail(rs.getString("email"));
				cur.setRole(rs.getInt("role_id"));
			
				List<Account> accounts = accDAO.getByUserId(cur.getUserId());
				for (Account a : accounts) {
					cur.setAccount(a);
				}
				// set Accounts
				
				allUsers.add(cur);
			}
			return allUsers;
			
		} catch (SQLException e) {
			
		}
		return null;
	}

}
