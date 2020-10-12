package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

public class FormatServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		FormModel model = new FormModel();
		model.username = request.getParameter("username");
		model.password = request.getParameter("password");
		
		model.food = request.getParameter("food");
		model.languages = request.getParameterValues("language");
		
		
		
		//FormModel fm = (FormModel) request.getSession().getAttribute("FormModel");
		HttpSession session = request.getSession();
		
		session.setAttribute("FormModel", model);
		System.out.println("Form Submitted");
		
		
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		//session.invalidate();
		//System.out.println(session.toString());
		FormModel fm = (FormModel) request.getSession().getAttribute("FormModel");
		if (fm == null) {
			System.out.println("null");
			session.invalidate();
			try {
				PrintWriter pw = response.getWriter();
				pw.println("no form has been submitted");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			try {
				System.out.println("NOT null");
				//HttpSession curSession = request.getSession();
				//FormModel fm = (FormModel) curSession.getAttribute("FormModel");
				PrintWriter pw = response.getWriter();
				pw.println("Model Values: <br>"
											+ "Username: " + fm.username
											+ "<br>Password: " + fm.password
											+ "<br>Food: " + fm.food
											+ "<br>Spoken Languages: ");
				for (int i = 0; i < fm.languages.length; i++) {
					pw.println("<br> " + fm.languages[i]);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class FormModel {
	public String username;
	public String password;
	public String food;
	public String[] languages;
}
