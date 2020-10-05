package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
	
	public static void main(String[] args) {
		String connectionString = "jdbc:postgresql://lallah.db.elephantsql.com:5432/iewkljsf",
		 	   username = "iewkljsf",
		       password = "nBG7He5aybzPUMcSjAZ6ueA6PCMuqT-l";
		
		
		Connection connection = null;
		
		try {
			// This can help 
			Class.forName("org.postgresql.Driver");

			// Connection object represents out authenticated connection to our DB
			// Its where Statement and PreparedStatement objects come from
			connection = DriverManager.getConnection(connectionString, username, password);
		
			regularStatement(connection);
			preparedStatement(connection);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// We should close our connection at the end
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void regularStatement(Connection connection) throws SQLException {
		// Statement object doesnt represent a statemtnt; it's the vehicle for running statements
		Statement stmt = connection.createStatement();
					
		// We use our Statement to execute a query, which will return a ResultSet
		// ResultSets contain the rows returned by the query
		ResultSet rs = stmt.executeQuery("select * from residents;");
					
		// ResultSet has an iternal iterator that starts the row above the first row
		// We move it along by calling ResultSet.next()
		// If there is no next row, next() returns false
		while (rs.next()) {
			// in order to get data out of the row, we can call a variety of get methods
			// Results.getString, and give either column num or column name as the parameter
			// to identify the column
			System.out.println(rs.getString(1) + " " + rs.getString("last_name"));
		}
					
	}
	
	public static void preparedStatement(Connection connection) throws SQLException {
		
		// PreparedStatements are Statements that have question marks in them. the ?s represent
		// parameters, or variables, that are then set after the Prepared Statement is created.
		// The ?s can be anything.
		PreparedStatement ps = connection.prepareStatement("select * from residents where id = ?;");
		
		// We use PreparedStatement.set<Type>(position, value) to set the value of a particular paremter
		// As with columns in ResultSet.gets, the first ? number is 1
		ps.setInt(1, 1);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
		}
	}
	
}
