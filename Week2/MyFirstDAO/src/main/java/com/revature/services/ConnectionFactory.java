package com.revature.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// ConnectionFactory carries all of the info to connect
// but does not manage them. It's up to the programmer to close
// connections with them.
public class ConnectionFactory {

	// need strings
	private static String connectionString = "jdbc:postgresql://lallah.db.elephantsql.com:5432/iewkljsf",
		 	   username = "iewkljsf",
		       password = "nBG7He5aybzPUMcSjAZ6ueA6PCMuqT-l";
		
	
	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(connectionString, username, password);
			return connection;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
