package com.revature.generic.array;

public class ArrayTester {

	public static void main(String[] args) {
		ArrayList<String> arrList = new ArrayList<String>();
		
		arrList.add("Hello");
		arrList.add("ello");
		arrList.add("llo");
		arrList.add("lo");
		arrList.add("o");
		
		for (int i = 0; i<5; i++) {
			System.out.println(arrList.get(i).toString());
		}
		
		arrList.add("resize");
		arrList.add("number2");
		// test resize capability
		for (int i = 0; i<7; i++) {
			System.out.println(arrList.get(i).toString());
		}
		
		
		Object[][] splitArr = arrList.split(3);
		
		System.out.println(get2dArrayString(splitArr));
		/*
		for (int i = 0; i < 2; i++) {
			System.out.print("[");
			for (int j = 0; j <4; j++) {
				System.out.print(" " + splitArr[i][j] + " ");
			}
			System.out.println("]");
		}
		*/
	}
	
	public void print2dArray(Object[][] array2d) {
		for (int i = 0; i < array2d.length; i++) {
			
			for (int j = 0; j < array2d[i].length; j++) {
				
			}
			
		}
	}
	
	public static String get2dArrayString(Object[][] array2d) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < array2d.length; i++) {
			for (int j = 0; j < array2d[i].length; j++) {
				sb.append(array2d[i][j]);
				
				if (j+1 < array2d[i].length) { 
					sb.append(" , ");
				}
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}

}
