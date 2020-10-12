package com.revature.dao.interfaces;

import java.util.List;

import com.revature.models.Resident;

public interface ResidentDAO {
	 // A classic DAO has 4 basic operations: create (add), retrieve (get), update, delete
	public void createResident(Resident resident);
	
	public Resident getResident(int id);
	
	public List<Resident> getAllResidents();
	
	public void updateResident(Resident resident);
	
	public void deleteResident(Resident resident);
}
