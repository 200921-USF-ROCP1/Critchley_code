package com.revature.app;

public class Inheritance {
	private String name;
	private int age;
	
	public Inheritance(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void becomeOlder() {
		this.age++;
	}
	
	public void printSelf() {
		System.out.println("I am the Parent");
	}
}
