package com.services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseHelper {

	public static void makeResponse(HttpServletResponse response, String msg) {
		 ObjectMapper mapper = new ObjectMapper();
		 
		 Message m = new Message();
		 m.setMessage(msg);
		 
		 try {
			response.getWriter().println(mapper.writeValueAsString(m));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static class Message {
		private String message;
		
		public Message () {
			
		}
		
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}




