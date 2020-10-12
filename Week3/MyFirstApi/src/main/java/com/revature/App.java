package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.revature.dao.interfaces.ResidentDAO;
import com.revature.impl.ResidentDAOImpl;
import com.revature.models.Resident;
import com.revature.services.ConnectionService;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String connectionString = "jdbc:postgresql://lallah.db.elephantsql.com:5432/iewkljsf",
		//	 	   username = "iewkljsf",
		//	       password = "nBG7He5aybzPUMcSjAZ6ueA6PCMuqT-l";
			
			
			Connection connection = null;
			
			try {
				// This can help 
				Class.forName("org.postgresql.Driver");

				// Connection object represents out authenticated connection to our DB
				// Its where Statement and PreparedStatement objects come from
				// connection = DriverManager.getConnection(connectionString, username, password);
				
				ResidentDAO residentDAO = new ResidentDAOImpl(connection);
				Resident res = new Resident();
				res.setFirstName("Ressy");
				res.setLastName("Ression");
				//res.setApartmentId(1);
				
				residentDAO.createResident(res);
				//regularStatement(connection);
				//preparedStatement(connection);
				
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				
				// We should close our connection at the end
				/*
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
			}
			
	}

}
