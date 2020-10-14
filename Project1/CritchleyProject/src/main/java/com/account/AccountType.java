package com.account;

public class AccountType {
	private int typeId; // primary key
	private String type; // not null, unique
	
	public AccountType() {
		
	}
	
	public AccountType(int typeID) {
		// TODO Auto-generated constructor stub
		this.typeId = typeID;
		switch(typeID) {
		case 1:
			this.type = "Checking";
			break;
		case 2:
			this.type = "Savings";
			break;
		case 3:
			this.type = "Premium";
			break;
		}
	}

	public int getTypeId() {
		return typeId;
	}
	
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
