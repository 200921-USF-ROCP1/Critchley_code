package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.account.Account;
import com.dao.impl.UserDAOImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.ResponseHelper;
import com.services.StandardService;
import com.user.TransferClass;
import com.user.User;
import com.user.WithdrawDepositClass;

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
			response.setStatus(400);
			ResponseHelper.makeResponse(response, "No session logged in");
			return;
		}
		
		String role = cur.getRole().getRole();
		
		int src; 
		int target;
		double amount;
		ObjectMapper om = new ObjectMapper();
		
		
		
		
		StandardService service = new StandardService();
		boolean success = false;
		
		
		if (parts[parts.length-1].equals("withdraw")) {
			
			WithdrawDepositClass wd = om.readValue(request.getReader(), WithdrawDepositClass.class);
			
			src = wd.getAccountId();
			amount = wd.getAmount();
			if (amount < 0) {
				response.setStatus(401);
				ResponseHelper.makeResponse(response, "You are not allowed to perform this action (Cannot withdraw a negative amount)");
				return;
			}
			if (!role.equals("Admin") && cur.getAccount(src) == null) {
				response.setStatus(401);
				ResponseHelper.makeResponse(response, "You are not allowed to perform this action");
				return;
			}
			
			success = service.withdraw(amount, src);
			
			if (success) {
				response.setStatus(201);
				ResponseHelper.makeResponse(response, amount + " has been withdrawn from Account " + src);
			} else {
				response.setStatus(500);
				ResponseHelper.makeResponse(response, "Could not withdraw " + amount + " from Account " + src);
			}
			
		} else if (parts[parts.length-1].equals("deposit")) {
			WithdrawDepositClass wd = om.readValue(request.getReader(), WithdrawDepositClass.class);
			src = wd.getAccountId();
			amount = wd.getAmount();
			if (amount < 0) {
				response.setStatus(401);
				ResponseHelper.makeResponse(response, "You are not allowed to perform this action (Cannot deposit a negative amount)");
				return;
			}
			if (!role.equals("Admin") && cur.getAccount(src) == null) {
				response.setStatus(401);
				ResponseHelper.makeResponse(response, "You are not allowed to perform this action");
				return;
			}
			
			success = service.deposit(amount, src);
			
			if (success) {
				response.setStatus(201);
				ResponseHelper.makeResponse(response, amount + " has been deposited to Account " + src);
			} else {
				response.sendError(500);
			}
			
		} else if ((parts[parts.length-1].equals("transfer"))) {
			
			TransferClass tc = om.readValue(request.getReader(), TransferClass.class);
			
			src = tc.getSourceAccountId();
			target = tc.getTargetAccountId();
			amount = tc.getAmount();
			
			if (amount < 0) {
				response.setStatus(401);
				ResponseHelper.makeResponse(response, "You are not allowed to perform this action (Cannot deposit a negative amount)");
				return;
			}
			
			if (!role.equals("Admin") && cur.getAccount(src) == null) {
				response.setStatus(401);
				ResponseHelper.makeResponse(response, "You are not allowed to perform this action");
				return;
			}
			
			success = service.transfer(src, target, amount);
			
			if (success) {
				response.setStatus(201);
				ResponseHelper.makeResponse(response, amount + " has been transfered from Account " + src + " to Account " + target);
			} else {
				response.setStatus(401);
				ResponseHelper.makeResponse(response, "Could not transfer " + amount + " from Account " + src + " to Account: " + target);
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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		User cur = (User) session.getAttribute("User");
		
		if (cur == null) {
			session.invalidate();
			response.setStatus(400);
			ResponseHelper.makeResponse(response, "No session logged in");
			return;
		}
				
		String role = cur.getRole().getRole();
		
		if (!role.equals("Admin") && !role.equals("Employee")) {
			response.setStatus(401);
			ResponseHelper.makeResponse(response, "You are not allowed to perform this action");
			return;
		}
		
		String[] parts = request.getRequestURI().split("/");
		
		
		if (parts[parts.length - 1].equals("users")) {
			UserDAOImpl userDao = new UserDAOImpl();
			List<User> users = userDao.getAll();
			if (users == null) {
				response.setStatus(500);
				ResponseHelper.makeResponse(response, "Server could not get all users");
				return;
			}
			ObjectMapper mapper = new ObjectMapper();
			response.getWriter().println(mapper.writeValueAsString(users));
		} else { 		// URI Pattern matched /accounts/status/:statusId
			
			int statusId;
			try {
				// get /:statusId
				statusId = Integer.parseInt(parts[parts.length - 1]);
			} catch (NumberFormatException e) {		// if statusId is not a number
				response.setStatus(400);
				ResponseHelper.makeResponse(response, "Improper path " + request.getRequestURI() + " : " + parts[parts.length-1] + " must be an integer");
				return;
			}
			
			service = new StandardService();
			List<Account> accounts = service.accountsByStatus(statusId);
			if (accounts == null) {
				response.setStatus(500);
				ResponseHelper.makeResponse(response, "Server could not get all accounts by status id = " + statusId);
				return;
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
			response.setStatus(400);
			ResponseHelper.makeResponse(response, "No session logged in");
			return;
		}
		
		String role = cur.getRole().getRole();
		
		if (!role.equals("Admin")) {
			response.setStatus(401);
			ResponseHelper.makeResponse(response, "You are not allowed to perform this action");
			return;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.readValue(request.getReader(), User.class);
		service = new StandardService();
		
		user = service.updateUserInfo(user);
		
		if (user == null) {
			response.setStatus(401);
			ResponseHelper.makeResponse(response, "Server could not fulfill PUT at /users");
			return;
		}
		
		response.getWriter().println(mapper.writeValueAsString(user));
		
	}
	
	/*
	 * PATHS TO HANDLE:
	 * 	/users
	 */
	public void doDelete(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		User cur = (User) session.getAttribute("User");
		
		if (cur == null) {
			session.invalidate();
			response.setStatus(400);
			ResponseHelper.makeResponse(response, "No session logged in");
			return;
		}
		
		String role = cur.getRole().getRole();
		
		if (!role.equals("Admin")) {
			response.setStatus(401);
			ResponseHelper.makeResponse(response, "You are not allowed to perform this action");
			return;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			User user = mapper.readValue(request.getReader(), User.class);
			UserDAOImpl userDao = new UserDAOImpl();
			if (userDao.get(user.getUserId()) == null) {
				response.setStatus(401);
				ResponseHelper.makeResponse(response, "User " + user.getUserId() + " cannot be found, and therefore cannot be deleted.");
				return;
			}
			userDao.delete(user);
			response.setStatus(202);
			ResponseHelper.makeResponse(response, "User " + user.getUserId() + " has been deleted");
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(500);
			ResponseHelper.makeResponse(response, "Server side error while attempting to Delete User");
			return;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(500);
			ResponseHelper.makeResponse(response, "Server side error while attempting to Delete User");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(500);
			ResponseHelper.makeResponse(response, "Server side error while attempting to Delete User");
			return;
		} 
		
	}

}
