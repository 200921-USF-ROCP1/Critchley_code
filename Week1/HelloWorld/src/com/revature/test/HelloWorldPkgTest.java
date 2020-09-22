package com.revature.test;

public class HelloWorldPkgTest {
	String testSubject;
			
	public HelloWorldPkgTest() {
		testSubject = new String("Hello from new package");
	}
	
	public String ReturnTest() {
		return testSubject;
	}
}
