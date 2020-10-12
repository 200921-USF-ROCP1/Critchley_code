package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.account.Account;
import com.dao.impl.AccountDAOImpl;
import com.dao.impl.UserDAOImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.LoginService;
import com.user.User;

public class RestrictionServlet extends HttpServlet {
	
	private AccountDAOImpl accDAO;
	private UserDAOImpl userDAO;
	private LoginService thisService;
	
	/*
	 * PATHS TO HANDLE:
	 * 	/login
	 * 	/logout
	 * 	/register
	 * 
	 * - These paths can rely on LoginService thisService
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String[] parts = request.getRequestURI().split("/");
		thisService = new LoginService();
		
		if (parts[parts.length - 1].equals("login")) {
			doLogin(request, response);
		} else if (parts[parts.length - 1].equals("logout")) {
			doLogout(request, response);
		} else if (parts[parts.length - 1].equals("register")) {
			doRegister(request, response);
		}
			
	}
	
	/*
	 * PATH:
	 * 	/register
	 */
	private void doRegister(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User cur = (User) session.getAttribute("User");
		try {
			if (cur == null) {
				// no current session
				session.invalidate();
				response.sendError(400, "No session logged in");
			} else if (!cur.getRole().getRole().equals("Admin")) {
				// invalid register credentials
				response.sendError(400, "Invalid credentials");
			} else {
				ObjectMapper mapper = new ObjectMapper();
				User newUser = mapper.readValue(request.getReader(), User.class);
				User u = thisService.registerUser(newUser);
				if (u != null) {
					response.setStatus(201);
					response.getWriter().println(mapper.writeValueAsString(u));
				} else {
					response.sendError(400, "Invalid fields");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * PATH:
	 * 	/logout
	 */
	private void doLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("User");
		try {
			if (u == null) {
				response.sendError(400, "There is no user logged into the session");
			} else {
				response.getWriter().println("You have successfully logged out " + u.getUsername());	
			}
		} catch (Exception e) {
			
		} finally {
			session.invalidate();
		}
	}

	/*
	 * PATH:
	 * 	/login
	 */
	private void doLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("User");
		try {
			// check if already have session
			if (u != null) {
					// if session exists
					response.sendError(400, "already logged in");	
			} else {
				// if session does not exist
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				
				// get user with username and password
				u = thisService.login(username, password);
				
				// if username and password exist
				if (u != null) {
					// set session
					session.setAttribute("User", u);
			
					// write user as response
					ObjectMapper mapper = new ObjectMapper();
					response.getWriter().println(mapper.writeValueAsString(u));
					
					
				} else {
					// username and pass does not exist
					session.invalidate();
					response.sendError(400, "Invalid credentials");	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * PATHS TO HANDLE:
	 * 	/users/:id
	 * 	/accounts/:id
	 * 	/accounts/owned/:userID
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User cur = (User) session.getAttribute("User");
		String[] parts = request.getRequestURI().split("/");
		try {
			if (cur == null) {
				// no current session
				session.invalidate();
				
				response.sendError(400, "No session logged in");
				
			}
			
			int id = Integer.parseInt(parts[parts.length-1]);
			
			
			if (parts[2].equals("accounts")) {
				// accounts/:id
				if (parts.length == 4) {
					// If the current session is the owner of the account
					//  	OR an Admin or Employee
					if (isEmployAdmin(cur) || cur.getAccount(id) != null)
						doGetByAccountId(response, id);
				// accounts/owned/:userID
				} else if (parts.length == 5) {
					// If the current session is the the requested user
					//  	OR an Admin or Employee
					if (isEmployAdmin(cur) || cur.getUserId() == id)
						doGetAccountsByUser(response, id);
				}
			// users/:id
			} else if (parts[2].equals("users") && parts.length == 4) {
				// If the current session is the the requested user
				//  	OR an Admin or Employee
				if (isEmployAdmin(cur) || cur.getUserId() == id)
					doGetUserById(response, id);
			}
		} catch (NumberFormatException e) {
			// last part of path was not a number
			// bad request
		} catch (Exception e) {
			
		}
		
	}
	
	private boolean isEmployAdmin(User cur) {
		String role = cur.getRole().getRole();
		// check role
		if (role.equals("Admin") || role.equals("Employee")) {
			// check if account belongs to user
			return true;
		}
		return false;
		
	}
	
	/*
	 * PATH:
	 * 	/accounts/owned/:userID
	 */
	private void doGetAccountsByUser(HttpServletResponse response, int id) throws IOException {
		
		accDAO = new AccountDAOImpl();
		List<Account> accs = accDAO.getByUserId(id);
		
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().println(mapper.writeValueAsString(accs));
	}

	/*
	 * PATH:
	 * 	/accounts/:id
	 */
	private void doGetByAccountId(HttpServletResponse response, int id) throws JsonProcessingException, IOException {
		// TODO Auto-generated method stub
		accDAO = new AccountDAOImpl();
		Account acc = accDAO.get(id);
		
		if (acc == null) {
			response.sendError(400, "Servlet could not find account with id: " + id);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().println(mapper.writeValueAsString(acc));
	}

	/*
	 * PATH
	 * 	/users/:id
	 */
	private void doGetUserById(HttpServletResponse response, int id) throws JsonProcessingException, IOException {
		// TODO Auto-generated method stub
		userDAO = new UserDAOImpl();
		User user = userDAO.get(id);
		
		if (user == null) {
			response.sendError(400, "Servlet could not find user with id: " + id);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().println(mapper.writeValueAsString(user));
		
		
	}
	
	
}
