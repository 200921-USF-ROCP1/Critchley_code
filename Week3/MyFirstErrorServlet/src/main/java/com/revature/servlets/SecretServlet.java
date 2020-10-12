package com.revature.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecretServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("In Secret Servlet");

		
		HttpSession session = request.getSession();
		User user = new User();
		user.username = "secretUser";
		user.password = "secretPassVVord";
		
		session.setAttribute("User", user);
	}

}

class User {
	public String username;
	public String password;
}
