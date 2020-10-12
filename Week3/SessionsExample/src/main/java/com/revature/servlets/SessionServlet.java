package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;

public class SessionServlet extends HttpServlet {
	HttpSession session;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String un;
		if ((un = (String)session.getAttribute("username")) != null) {
			//ObjectMapper om = new ObjectMapper();
			try {
				response.getWriter().println(un + " is logged in!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().println("No one is logged in!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		//session = request.getSession();
		ObjectMapper om = new ObjectMapper();
		try {
			User u = om.readValue(request.getReader(), User.class);
			if (checkUsernamePassword(u.getUsername(), u.getPassword())) {
				//session = request.getSession();
				//session.setAttribute("username", u.getUsername());
				request.getSession().setAttribute("username", u.getUsername());
			} else {
				
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private boolean checkUsernamePassword(String username, String password) {
		// TODO Auto-generated method stub
		
		if (username.equals("dummyUsername")) {
			if (password.equals("dummyPassword")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		
	}
}
