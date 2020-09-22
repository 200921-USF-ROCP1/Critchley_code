package com.revature.app;

public class ChildOfInheritance extends Inheritance {
	public ChildOfInheritance(String name) {
		super(name, 1);
	}
	
	public ChildOfInheritance(String name, int age) {
		super(name, age);
	}
	
	public void test() {
		int i = getAge();
	}
	
	public void printSelf() {
		System.out.println("I am the Child");
	}
}
