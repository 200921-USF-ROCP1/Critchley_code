package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.account.Account;
import com.dao.impl.AccountDAOImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.User;

public class AccountsServlet extends HttpServlet {
	
	AccountDAOImpl accountDao;
	
	/*
	 * PATHS TO HANDLE:
	 * 	/accounts
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User cur = (User) session.getAttribute("User");
		
		if (cur == null) {
			session.invalidate();
			response.sendError(400, "No session logged in");
		}
		
		String role = cur.getRole().getRole();
		
		if (!role.equals("Admin")) {
			response.sendError(401, "You are not allowed to perform this action");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		Account acc = mapper.readValue(request.getReader(), Account.class);
		accountDao = new AccountDAOImpl();
		acc = accountDao.create(acc);
		if (acc == null) {
			response.sendError(500, "Server could not create the requested account");
		}
		
		response.getWriter().println(mapper.writeValueAsString(acc));
	}
	
	/*
	 * PATHS TO HANDLE:
	 * 	/accounts
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User cur = (User) session.getAttribute("User");
		
		if (cur == null) {
			session.invalidate();
			response.sendError(400, "No session logged in");
		}
		
		String role = cur.getRole().getRole();
		
		if (!role.equals("Admin") && !role.equals("Employee")) {
			response.sendError(401, "You are not allowed to perform this action");
		}
		
		accountDao = new AccountDAOImpl();
		List<Account> accs = accountDao.getAll();
		
		if (accs == null) {
			response.sendError(500, "Server could not get all accounts");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().println(mapper.writeValueAsString(accs));
		
		
	}
	
	/*
	 * PATHS TO HANDLE:
	 * 	/accounts
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User cur = (User) session.getAttribute("User");
		
		if (cur == null) {
			session.invalidate();
			response.sendError(400, "No session logged in");
		}
		
		String role = cur.getRole().getRole();
		
		if (!role.equals("Admin")) {
			response.sendError(401, "You are not allowed to perform this action");
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		Account acc = mapper.readValue(request.getReader(), Account.class);
		accountDao = new AccountDAOImpl();
		acc = accountDao.update(acc);
		if (acc == null) {
			response.sendError(500, "Server could not update account");
		}
		
		response.getWriter().println(mapper.writeValueAsString(acc));
	}

}
