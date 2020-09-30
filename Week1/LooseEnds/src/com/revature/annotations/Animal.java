package com.revature.annotations;

public class Animal {
	int eyeballs = 0;
	
	public void printEyeballs() {
		System.out.println("Im a basicanimal");
	}
	
}

/*
 * Annotations are keywords protected by an @
 * and they can go over classes, methods, and variables
 * depending on the anotation
 */

class Bird extends Animal {
	
	// marks a method as oiverriding a parent method
	@Override
	public void printEyeballs() {
		System.out.println("probably got 2 eyes");
	}
	
	// marks a method as being phased out + should not be used
	@Deprecated
	public void printEyes() {
		System.out.println("probably got 2 eyes");
	}
}

class usesBird {
	
	// prevents certain warnings in certain context that are given by IDE
	@SuppressWarnings("deprecation")
	public void useBird() {
		Bird b = new Bird();
		b.printEyes();
	}
}
