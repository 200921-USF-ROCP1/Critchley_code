package com.revature.app;

// cannot be instantiated
// can be inherited 
public abstract class AbstractClass {
	
	public void printThis(String s) {
		System.out.println(s);
	}
	
	// this method would need to be implemented
	public abstract void doSomething();
	
}
