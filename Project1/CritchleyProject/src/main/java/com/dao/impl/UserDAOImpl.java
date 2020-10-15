package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.account.Account;
import com.dao.UserDAOInterface;
import com.services.ConnectionService;
import com.user.Role;
import com.user.User;

public class UserDAOImpl implements UserDAOInterface{
	Connection connection;
	
	public UserDAOImpl() {
		// TODO Auto-generated constructor stub
		this.connection = ConnectionService.getConnection();
	}

	public User create(User t) {
		// TODO Auto-generated method stub
		try {
			//ArrayList<Account> accs = t.getAccounts();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO users (username, password, firstName, lastName, email, role_id) VALUES (?,?,?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(5, t.getEmail());
			ps.setInt(6, t.getRole().getRoleId());
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			
			t.setUserId(rs.getInt(1));
			return t;
			//return rs.getInt("user_id");
		} catch (SQLException e) {
			return null;
		}
		
	}

	public User get(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?;");
			ps.setInt(1, id);
			// maybe get accounts??
			ResultSet rs = ps.executeQuery();
			AccountDAOImpl accDAO = new AccountDAOImpl();
			if (rs.next() ) {
				User cur = new User();
				cur.setUsername(rs.getString("username"));
				cur.setUserId(rs.getInt("user_id"));
				cur.setPassword(rs.getString("password"));
				cur.setFirstName(rs.getString("firstName"));
				cur.setLastName(rs.getString("lastName"));
				cur.setEmail(rs.getString("email"));
				int roleId = rs.getInt("role_id");
				Role role = new Role();
				
				role.setRoleId(roleId);
				if (roleId == 1) {
					role.setRole("Admin");
				} else if (roleId == 2) {
					role.setRole("Employee");
				} else if (roleId == 3) {
					role.setRole("Standard");
				} else if (roleId == 4) {
					role.setRole("Premium");
				}
				cur.setRole(role);
				// set Accounts
				
				List<Account> accounts = accDAO.getByUserId(cur.getUserId());
				for (Account a : accounts) {
					cur.setAccount(a);
					
				}
				return cur;
			}

			return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getByUsername(String s) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username = ?;");
			ps.setString(1, s);
			// maybe get accounts??
			ResultSet rs = ps.executeQuery();
			AccountDAOImpl accDAO = new AccountDAOImpl();
			
			if (rs.next()) {
				User cur = new User();
				cur.setUsername(rs.getString("username"));
				cur.setPassword(rs.getString("password"));
				cur.setUserId(rs.getInt("user_id"));
				cur.setFirstName(rs.getString("firstName"));
				cur.setLastName(rs.getString("lastName"));
				cur.setEmail(rs.getString("email"));
				int roleId = rs.getInt("role_id");
				Role role = new Role();
				
				role.setRoleId(roleId);
				if (roleId == 1) {
					role.setRole("Admin");
				} else if (roleId == 2) {
					role.setRole("Employee");
				} else if (roleId == 3) {
					role.setRole("Standard");
				} else if (roleId == 4) {
					role.setRole("Premium");
				}
				cur.setRole(role);
				// set Accounts
				List<Account> accounts = accDAO.getByUserId(cur.getUserId());
				for (Account a : accounts) {
					cur.setAccount(a);
				}
				return cur;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User getByEmail(String s) {
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE email = ?;");
			ps.setString(1, s);
			// maybe get accounts??
			ResultSet rs = ps.executeQuery();
			AccountDAOImpl accDAO = new AccountDAOImpl();
			if (rs.next()) {
				User cur = new User();
				cur.setUsername(rs.getString("username"));
				cur.setPassword(rs.getString("password"));
				cur.setUserId(rs.getInt("user_id"));
				cur.setFirstName(rs.getString("firstName"));
				cur.setLastName(rs.getString("lastName"));
				cur.setEmail(rs.getString("email"));
				int roleId = rs.getInt("role_id");
				Role role = new Role();
				
				role.setRoleId(roleId);
				if (roleId == 1) {
					role.setRole("Admin");
				} else if (roleId == 2) {
					role.setRole("Employee");
				} else if (roleId == 3) {
					role.setRole("Standard");
				} else if (roleId == 4) {
					role.setRole("Premium");
				}
				cur.setRole(role);
				// set Accounts
				List<Account> accounts = accDAO.getByUserId(cur.getUserId());
				for (Account a : accounts) {
					cur.setAccount(a);
				}
				return cur;
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User update(User t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE users SET username = ?, password = ?, firstName = ?, lastName = ?, email = ?, role_id = ? WHERE user_id = ?;");
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.setString(3, t.getFirstName());
			ps.setString(4, t.getLastName());
			ps.setString(5, t.getEmail());
			ps.setInt(6, t.getRole().getRoleId());
			ps.setInt(7, t.getUserId());
			
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
			
			List<Account> accounts = t.getAccounts();
			AccountDAOImpl accDao = new AccountDAOImpl();
			
			for (Account a: accounts) {
				accDao.delete(a);
			}
			
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
			ResultSet rs = ps.executeQuery();
			AccountDAOImpl accDAO = new AccountDAOImpl();
			List<User> allUsers = new ArrayList<User>();
			User cur = null;
			while (rs.next()) {
				cur = new User();
				cur.setUsername(rs.getString("username"));
				cur.setPassword(rs.getString("password"));
				cur.setUserId(rs.getInt("user_id"));
				cur.setFirstName(rs.getString("firstName"));
				cur.setLastName(rs.getString("lastName"));
				cur.setEmail(rs.getString("email"));
				int roleId = rs.getInt("role_id");
				Role role = new Role();
				
				role.setRoleId(roleId);
				if (roleId == 1) {
					role.setRole("Admin");
				} else if (roleId == 2) {
					role.setRole("Employee");
				} else if (roleId == 3) {
					role.setRole("Standard");
				} else if (roleId == 4) {
					role.setRole("Premium");
				}
				cur.setRole(role);
			
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
