package com.revature.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.interfaces.ResidentDAO;
import com.revature.models.Apartment;
import com.revature.models.Resident;
import com.revature.services.ConnectionService;

public class ResidentDAOImpl implements ResidentDAO {

	Connection connection;
	
	public ResidentDAOImpl(Connection connection) {
		this.connection = ConnectionService.getConnection();
	}
	
	public void createResident(Resident resident) {
		// TODO Auto-generated method stub
		try {
			/*
			Statement stmt = connection.createStatement();
			stmt.executeUpdate("INSERT INTO residents (first_name, last_name, apartment_id) VALUES ('" 
								+ resident.getFirstName() + "', '" + resident.getLastName() + "', '" 
								+ resident.getApartment().getId() + "');" );
			*/
			
			// We may want to checlk that apartment exists in apartment table
			// I need to check if the Apartment exists
			if (resident.getApartment() != null) {
				PreparedStatement ps = connection.prepareStatement("SELCT * FROM aparments WHERE id = ?;");
				ps.setInt(1,  resident.getApartment().getId());
				
				ResultSet rs = ps.executeQuery();
				if (!rs.next()) {
					// if there's no rows in the ResultSet, we have to create the apartment
					// apartmentDoe.createApartment(resident.getApartment())
					
					PreparedStatement apartmentStatement = connection.prepareStatement("INSERT INTO apartments " 
									+ "(building_letter, room_number, monthly_rent)",Statement.RETURN_GENERATED_KEYS);
					apartmentStatement.setString(1, resident.getApartment().getBuildingLetter());
					apartmentStatement.setInt(2,  resident.getApartment().getRoomNumber());
					apartmentStatement.setDouble(3,  resident.getApartment().getMonthly_rent());
					apartmentStatement.executeUpdate();
					
					// Get the generated keys ResultSet
					ResultSet keys = apartmentStatement.getGeneratedKeys();
					keys.next();
					resident.getApartment().setId(keys.getInt(1));
				}
			}
			PreparedStatement ps = connection.prepareStatement("INSER INTO residents (first_name, last_name, apartment_id) VALUES (?, ?, ?);");
			ps.setString(1,  resident.getFirstName());
			ps.setString(2, resident.getLastName());
			ps.setInt(3, resident.getApartment().getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Resident getResident(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM residents WHERE id = ?");
			ps.setInt(1,  id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Resident r = new Resident();
				r.setId(rs.getInt("id"));
				r.setFirstName(rs.getString("first_name"));
				r.setLastName(rs.getString("last_name"));
				
				// We need to get the Apartment from the DB
				// should really be using apartmentDAO here, in a getApartment() method 
				// r.getApartmentId(rs.getInt("apartment_id"));
				PreparedStatement apartmentStatement = connection.prepareStatement("SELECT * FROM apartments WHERE id = ?");
				apartmentStatement.setInt(1,  r.getApartment().getId());
				
				ResultSet apartmentRs = apartmentStatement.executeQuery();
				if (apartmentRs.next()) {
					Apartment apt = new Apartment();
					apt.setId(apartmentRs.getInt("id"));
					apt.setBuildingLetter(apartmentRs.getString("building_letter"));
					apt.setRoomNumber(apartmentRs.getInt("room_number"));
					apt.setMonthly_rent(apartmentRs.getDouble("monthly_rent"));
					
					r.setApartment(apt);
				}
				
				return r;
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// There were 0 records returned
		return null;
	}
	
	public void updateResident(Resident resident) {
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE residents"
								+ " SET first_name = ?, last_name = ?, apartment_id = ?"
							+ " WHERE id = ?;");
			ps.setString(1,  resident.getFirstName());
			ps.setString(2,  resident.getLastName());
			ps.setInt(3, resident.getApartment().getId());
			ps.setInt(4, resident.getId());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Resident> getAllResidents() {
		// TODO Auto-generated method stub
		List<Resident> residents = new ArrayList<Resident>();
		try {
			// more of a test thing
			// should not do in enterprise
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM residents " +
									"LEFT JOIN apartments on residents.apartment_id = apartment.id;");
		
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Resident r = new Resident();
				r.setId(rs.getInt("residents.id"));
				r.setFirstName(rs.getString("first_name"));
				r.setLastName(rs.getString("last_name"));
				// r.setApartmentId(rs.getInt("apartment_id"));
				/*
				PreparedStatement apartmentStatement = connection.prepareStatement("SELECT * FROM apartments WHERE id = ?");
				apartmentStatement.setInt(1,  r.getApartment().getId());
				
				ResultSet apartmentRs = apartmentStatement.executeQuery();
				*/
				//if (apartmentRs.next()) {
				Apartment apt = new Apartment();
				apt.setId(rs.getInt("apartment.id"));
				apt.setBuildingLetter(rs.getString("building_letter"));
				apt.setRoomNumber(rs.getInt("room_number"));
				apt.setMonthly_rent(rs.getDouble("monthly_rent"));
				
				r.setApartment(apt);
				//}
				
				residents.add(r);
			}
			
			return residents;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}


	public void deleteResident(Resident resident) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM residents WHERE id = ?;");
			ps.setInt(1,  resident.getId());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
