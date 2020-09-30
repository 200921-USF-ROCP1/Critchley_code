package com.revature.app;

/*
 * Annotations, including Override and Deprecated
 * final vs finalize vs finally
 * var args (or the ...)
 */
public class App {
	public static void main(String[] args) {
		String a = "Hello", b = "there", c = "friend";
		
		String[] arr = {a, b, c};
		printArgs(arr);			// works
		// printArgs();			does not work
		
		printVarArgs(a, b, c);	// works
		printVarArgs(arr); 		// works
	}
	
	public static void printArgs(String[] strings) {
		for (int i=0; i < strings.length; i++) 
			System.out.println(strings[i]);
		
	}
	
	/*
	 * Showcasing elipsis (...)
	 * 
	 * methods like printf("%d %s", 1, "hello") use varargs
	 */
	public static void printVarArgs(String... strings) {
		for (int i=0; i < strings.length; i++) 
			System.out.println(strings[i]);
	}
	
	// other params can be used but the varargs must be last param
	// and only one parameter can be varargs
	public static void printNameVarArgs(String name, String...strings) {
		
	}
}
