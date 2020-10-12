package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.account.Account;
import com.dao.impl.UserDAOImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.StandardService;
import com.user.User;

public class ServiceServlet extends HttpServlet {
	
	StandardService service;
	/*
	 * PATHS TO HANDLE:
	 * 	/accounts/withdraw
	 * 	/accounts/deposit
	 * 	/accounts/transfer
	 * 
	 * All above paths can call on StandardService
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] parts = request.getRequestURI().split("/");
		
		HttpSession session = request.getSession();
		User cur = (User) session.getAttribute("User");
		
		if (cur == null) {
			session.invalidate();
			response.sendError(400, "No session logged in");
		}
		
		String role = cur.getRole().getRole();
		
		int src; 
		int srcTransfer;
		
		try {
			src = Integer.parseInt(request.getParameter("accountId"));
			
		} catch (Exception e) {
			srcTransfer = Integer.parseInt(request.getParameter("sourceAccountId"));
			src = srcTransfer;
		}
		
		if (!role.equals("Admin") && cur.getAccount(src) == null) {
			response.sendError(401, "You are not allowed to perform this action");
		}
		
		double amount = Double.parseDouble(request.getParameter("amount"));
		StandardService service = new StandardService();
		boolean success = false;
		
		if (parts[parts.length-1].equals("withdraw")) {
			success = service.withdraw(amount, src);
			if (!success) {
				response.sendError(200, amount + " has been withdrawn from Account " + src);
			}
		} else if (parts[parts.length-1].equals("deposit")) {
			success = service.deposit(amount, src);
			if (!success) {
				response.sendError(200, amount + " has been deposited to Account " + src);
			}
		} else if ((parts[parts.length-1].equals("transfer"))) {
			int target = Integer.parseInt(request.getParameter("targetAccountId"));
			success = service.transfer(src, target, amount);
			if (!success) {
				response.sendError(200, amount + " has been transfered from Account " + src + " to Account " + target);
			}
		} else {
			response.sendError(500, "How did we get here ?? (DoPost, StandardServlet)");
		}
		
		
	}
	
	/*
	 * PATHS TO HANDLE:
	 * 	/users
	 * 	/accounts/status/:statusID
	 * 		- this path can call on StandardService
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, NumberFormatException {
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
		
		String[] parts = request.getRequestURI().split("/");
		
		
		if (parts[parts.length - 1].equals("users")) {
			UserDAOImpl userDao = new UserDAOImpl();
			List<User> users = userDao.getAll();
			if (users == null) {
				response.sendError(500, "Server could not get all users");
			}
			ObjectMapper mapper = new ObjectMapper();
			response.getWriter().println(mapper.writeValueAsString(users));
		} else {
			int statusId = Integer.parseInt(parts[parts.length - 1]);
			service = new StandardService();
			List<Account> accounts = service.accountsByStatus(statusId);
			if (accounts == null) {
				response.sendError(500, "Server could not get all accounts by status id = " + statusId);
			}
			ObjectMapper mapper = new ObjectMapper();
			response.getWriter().println(mapper.writeValueAsString(accounts));
		}
	}
	
	/*
	 * PATHS TO HANDLE:
	 * 	/users
	 * 		- this path can call on StandardService
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
		User user = mapper.readValue(request.getReader(), User.class);
		
		user = service.updateUserInfo(user);
		
		if (user == null) {
			response.sendError(500, "Server could not fulfill PUT at /users");
		}
		
		response.getWriter().println(mapper.writeValueAsString(user));
		
	}

}
