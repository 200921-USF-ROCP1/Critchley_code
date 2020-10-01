package com.account;

public class AccountStatus {
	private int statusId; // primary key
	private String status; // not null, unique
	
	public AccountStatus() {
		
	}
	
	public AccountStatus(int statusID) {
		// TODO Auto-generated constructor stub
		this.statusId = statusID;
		switch(statusID) {
			case 1:
				this.status = "Pending";
				break;
			case 2:
				this.status = "Open";
				break;
			case 3:
				this.status = "Closed";
				break;
			case 4:
				this.status = "Denied";
				break;
		}
	}

	public int getStatusId() {
		return statusId;
	}
	
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
