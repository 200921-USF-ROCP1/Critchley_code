package com.services.interfaces;

public interface StandardServiceInterface {
	
	public void updateCurrentUserInfo(String username, String password,String firstName, String lastName, String email);
	
	public void withdrawal(double withdrawalAmount, int accountId);
	
	public void deposit(double depositAmount, int accountId);
	
	public void transfer(int sourceAccountId, int destinationAccountId, double transferAmount);
}
