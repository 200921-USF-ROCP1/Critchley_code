package com.revature.servlets;

import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		throw new NullPointerException("Hello from My Servlet");
	}
}
