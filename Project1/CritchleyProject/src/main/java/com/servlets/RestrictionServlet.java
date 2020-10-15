package com.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.account.Account;
import com.dao.impl.AccountDAOImpl;
import com.dao.impl.UserDAOImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.LoginService;
import com.services.ResponseHelper;
import com.user.LoginClass;
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
		ObjectMapper mapper = new ObjectMapper();
		try {
			if (cur == null) {
				// no current session
				session.invalidate();
				response.setStatus(400);
				return;
				
			} else if (!cur.getRole().getRole().equals("Admin")) {
				// invalid register credentials
				response.setStatus(401);
				ResponseHelper.makeResponse(response,  "The requested action is not permitted");
				return;
			} else {
				// register new user
				User newUser = mapper.readValue(request.getReader(), User.class);
				User u = thisService.registerUser(newUser);
				if (u != null) {
					response.setStatus(201);
					response.getWriter().println(mapper.writeValueAsString(u));
				} else {
					response.setStatus(400);
					ResponseHelper.makeResponse(response,  "Invalid fields");
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
				response.setStatus(400);
				ResponseHelper.makeResponse(response, "There is no user logged into the session");
			} else {
				ResponseHelper.makeResponse(response, "You have successfully logged out " + u.getUsername());
				// response.getWriter().println("You have successfully logged out " + u.getUsername());	
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
					response.setStatus(400);
					ResponseHelper.makeResponse(response, "already logged in");
					return;
			} else {
				ObjectMapper om = new ObjectMapper();
				LoginClass lc = om.readValue(request.getReader(), LoginClass.class);
				// if session does not exist
				String username = lc.getUsername();
				String password = lc.getPassword();
				
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
					response.setStatus(400);
					ResponseHelper.makeResponse(response, "Invalid credentials");
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
				
				response.setStatus(400);
				ResponseHelper.makeResponse(response, "No session logged in");
				return;
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
			response.setStatus(400);
			ResponseHelper.makeResponse(response, "Improper path " + request.getRequestURI() + " : " + parts[parts.length-1] + " must be an integer");
			return;
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
		
		if (acc.getStatus() == null) {			
			response.setStatus(400);
			ResponseHelper.makeResponse(response, "Servlet could not find account with id: " + id);
			return;
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
		
		if (user.getEmail() == null) {
			response.setStatus(400);
			ResponseHelper.makeResponse(response, "Servlet could not find user with id: " + id);
			return;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().println(mapper.writeValueAsString(user));
		
		
	}
	
	
}
