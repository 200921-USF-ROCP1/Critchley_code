package com.services.interfaces;

public interface AdminServiceInterface extends EmployeeServiceInterface {
	
	public void modifyUserById(int userId);
	
	public void modifyUserByEmail(String email);
	
	public void modifyUserByUsername(String username);
	
	public void modifyAccount(int accountId);
}
