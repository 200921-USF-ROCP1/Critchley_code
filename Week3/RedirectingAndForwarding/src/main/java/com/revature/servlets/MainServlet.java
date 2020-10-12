package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().println("Hello Servlet Forwards and Redirects");
		
			ServletContext context = getServletContext();
			response.getWriter().println(context.getInitParameter("MyAwesomeVar"));
			
			ServletConfig config = getServletConfig();
			response.getWriter().println(config.getInitParameter("MySillyVar"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
}
