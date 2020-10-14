package com.revature.servlets;


import java.io.IOException;

import javax.servlet.http.*;

public class ErrorServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("In Error Servlet");

		
		System.out.println("Made it to ErrorServlet");
	    // Set response content type so it will always render to HTML
	    response.setContentType("text/html");
	    
	   // Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");

		//response.getWriter().println(e.getMessage());
	      
	    // Do error stuff here!
	  
	    Integer i = (Integer) request.getAttribute("javax.servlet.error.status_code");
	    int status_code = i.intValue();
	    Exception e = (Exception) request.getAttribute("javax.servlet.error.exception");
	    Error err = (Error) request.getAttribute("javax.servlet.error");
	    
	    response.getWriter().println("<!DOCTYPE html>"
	    		+ "<html>"
	    		+ "<head>"
	    		+ "	<title>ErrorPage"
	    		+ "</title>"
	    		+ "</head>"
	    		+ "<body>"
	    		+ "<h1>"
	    		+ "Error Code: " + status_code
	    		+ "</h1>"
	    		+ "<p>");
	    
	    if (e != null) {
	    	response.getWriter().println(e.getMessage());
	    } else if (err != null){
	    	
	    	response.getWriter().println(err.getMessage());
	    }
	    
	    response.getWriter().println(" </p> </body>"
	    		+ "</html>");
	    		
	   
	    
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
