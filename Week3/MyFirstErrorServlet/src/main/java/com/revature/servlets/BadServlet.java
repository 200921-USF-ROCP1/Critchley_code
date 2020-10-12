package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BadServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// This will throw an unchecked exception! 
		//int i = 1 / 0;
		System.out.println("In Bad Servlet");
		HttpSession session = request.getSession();
		
		User user = (User) session.getAttribute("User");
		
		if (user == null) {
			session.invalidate();
			try {
				response.sendError(403, "Invalid Credentials");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().println("<!DOCTYPE html>"
						+ "<html>"
						+ "<head>"
						+ "	<title>SecretPage"
						+ "</title>"
						+ "</head>"
						+ "<body>"
						+ "<h1>"
						+ "Congrats!"
						+ "</h1>"
						+ "<p>"
						+ "You made it to the secret html page yay"
						+ "</p>"
						+"</body></html>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
