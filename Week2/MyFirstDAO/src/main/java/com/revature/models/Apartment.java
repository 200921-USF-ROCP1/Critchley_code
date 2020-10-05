package com.revature.models;

public class Apartment {
	private int id;
	private String buildingLetter;
	private int roomNumber;
	private double monthly_rent;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBuildingLetter() {
		return buildingLetter;
	}
	public void setBuildingLetter(String buildingLetter) {
		this.buildingLetter = buildingLetter;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public double getMonthly_rent() {
		return monthly_rent;
	}
	public void setMonthly_rent(double monthly_rent) {
		this.monthly_rent = monthly_rent;
	}
}
