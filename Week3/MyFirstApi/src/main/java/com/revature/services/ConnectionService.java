package com.revature.services;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


// A ConnectionSevice provides a centralized place to store
// your Connection Object. Now whenever one is needed, you
// can simply call ConnectionService.getConnection();
// It is a Singleton - only one instasnce (or in this case, zero)
// and carries a centralized place for data and functionality
public class ConnectionService {
	private static Connection connection;
	
	private static String connectionString = "",
		 	   username = "",
		       password = "";
		
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				/*
				FileInputStream fis = new FileInputStream("connection.properties");
				Properties prop = new Properties();
				prop.load(fis);
				connection = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
				*/
				Class.forName("org.postgresql.Driver");
				String url = "jdbc:postgresql://lallah.db.elephantsql.com:5432/iewkljsf",
					username= "iewkljsf",
					password= "nBG7He5aybzPUMcSjAZ6ueA6PCMuqT-l";
				connection = DriverManager.getConnection(url, username, password);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} 

		return connection;
	}
	
	public static void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
