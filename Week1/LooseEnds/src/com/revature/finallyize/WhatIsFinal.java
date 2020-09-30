package com.revature.finallyize;

// final vs finally vs finalize
public class WhatIsFinal {
	// final variables can not be changed after initialization
	final int b = 5; 
	
	// Final methods cannot be overridden
	public final void doStuff() {
		
	}
	
	public void doFinally() {
		try {
			
		} catch (Exception e) {
			
		} finally {
			// this block always runs
			// even if a return is reached, 
			// this block will still run before code resumes
			// could be used for cleanups for objects and connections
			// opened during try catch
		}
	}
	
	// finalize runs before garbage collection deletes
	// object from memory.
	// very last section of code that runs for an object
	// garbage collection will still work
	// actually overrides Object.finalize()
	public void finalize() {
		// just for logic that pertains to program / object
		// low level stuff left to JVM
	}
}
