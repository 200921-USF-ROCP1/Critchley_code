package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.interfaces.ResidentDAO;
import com.revature.impl.ResidentDAOImpl;
import com.revature.models.*;
import com.revature.services.ConnectionService;
import com.revature.services.StringUtils;

public class ResidentServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String[] parts = request.getRequestURI().split("/");
		ResidentDAOImpl resDao = new ResidentDAOImpl(ConnectionService.getConnection());
		
		if (parts.length == 3) {
		
			// if there is no ID, it is a get all
			List<Resident> residents = resDao.getAllResidents();
			
			
			
			try {
				ObjectMapper mapper = new ObjectMapper();
				response.getWriter().println(mapper.writeValueAsString(residents));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (parts.length == 4) {
			// Otherwise, last element is an id
			if (StringUtils.isInteger(parts[3])) {
				int id = Integer.parseInt(parts[3]);
				ObjectMapper mapper = new ObjectMapper();
				Resident res = resDao.getResident(id);
				try {
					response.getWriter().println(mapper.writeValueAsString(res));
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				// error handling
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		//Deserialize the body
		ObjectMapper mapper = new ObjectMapper();
		try {
			Resident res = mapper.readValue(request.getReader(), Resident.class);
			
			ResidentDAOImpl residentDao = new ResidentDAOImpl(ConnectionService.getConnection());
			residentDao.createResident(res);
			
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
}
