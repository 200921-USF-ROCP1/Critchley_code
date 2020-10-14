package com.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.account.Account;
import com.dao.impl.AccountDAOImpl;
import com.dao.impl.UserDAOImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.services.ResponseHelper;
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
			response.setStatus(400);
			ResponseHelper.makeResponse(response, "No session logged in");
			return;
		}
		ObjectMapper mapper = new ObjectMapper();
		Account acc = mapper.readValue(request.getReader(), Account.class);
		String role = cur.getRole().getRole();
		
		if (!role.equals("Admin") && acc.getUserID() != cur.getUserId()) {
			response.setStatus(401);
			ResponseHelper.makeResponse(response, "The requested action is not permitted");
			return;
		}
		
		accountDao = new AccountDAOImpl();
		acc = accountDao.create(acc);
		if (acc == null) {
			response.setStatus(500);
			ResponseHelper.makeResponse(response, "Server could not create the requested account");
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
			response.setStatus(400);
			ResponseHelper.makeResponse(response, "No session logged in");
			return;
		}
		
		String role = cur.getRole().getRole();
		
		if (!role.equals("Admin") && !role.equals("Employee")) {
			response.setStatus(401);
			ResponseHelper.makeResponse(response, "The requested action is not permitted");
			return;
		}
		
		accountDao = new AccountDAOImpl();
		List<Account> accs = accountDao.getAll();
		
		if (accs.get(0) == null) {
			response.setStatus(500);
			ResponseHelper.makeResponse(response, "Server could not get all accounts");
			return;
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
			response.setStatus(400);
			ResponseHelper.makeResponse(response, "No session logged in");
			return;
		}
		
		String role = cur.getRole().getRole();
		
		if (!role.equals("Admin")) {
			response.setStatus(401);
			ResponseHelper.makeResponse(response, "The requested action is not permitted");
			return;
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		Account acc = mapper.readValue(request.getReader(), Account.class);
		accountDao = new AccountDAOImpl();
		acc = accountDao.update(acc);
		if (acc == null) {
			response.setStatus(500);
			ResponseHelper.makeResponse(response, "Server could not update account");
			return;
		}
		
		response.getWriter().println(mapper.writeValueAsString(acc));
	}
	
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
			Account acc = mapper.readValue(request.getReader(), Account.class);
			AccountDAOImpl accDao = new AccountDAOImpl();
			if (accDao.get(acc.getAccountId()) == null) {
				response.setStatus(401);
				ResponseHelper.makeResponse(response, "Account " + acc.getAccountId() + " cannot be found, and therefore cannot be deleted.");
				return;
			}
			accDao.delete(acc);
			response.setStatus(202);
			ResponseHelper.makeResponse(response, "User " + acc.getAccountId() + " has been deleted");
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(500);
			ResponseHelper.makeResponse(response, "Server side error while attempting to Delete Account");
			return;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(500);
			ResponseHelper.makeResponse(response, "Server side error while attempting to Delete Account");
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(500);
			ResponseHelper.makeResponse(response, "Server side error while attempting to Delete Account");
			return;
		} 
		
	}

}
