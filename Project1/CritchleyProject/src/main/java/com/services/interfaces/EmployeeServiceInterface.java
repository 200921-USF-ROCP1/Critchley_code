package com.services.interfaces;

public interface EmployeeServiceInterface extends StandardServiceInterface {
	
	public void viewAllUsers();
	
	public void viewUser(int userId);
	
	public void viewAccount(int accountId);
}
