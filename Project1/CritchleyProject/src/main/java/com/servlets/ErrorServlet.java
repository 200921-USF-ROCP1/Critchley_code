package com.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.services.ResponseHelper;

public class ErrorServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(404);
		ResponseHelper.makeResponse(response, "Resource at requested path was not found.");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
