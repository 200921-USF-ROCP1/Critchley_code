package com.revature.generics;

import java.util.ArrayList;

import com.revature.generics.abstractclass.LinkedList;
import com.revature.generics.abstractclass.LinkedList.Iterator;

public class App {
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		
		list.insert("Hello");
		list.insert("there");
		list.insert("friend");
		
		/*
		for (int  i = 0; i < list.getSize(); i++) {
			System.out.println(list.get(i));
		}
		*/
		
		Iterator itr = list.getIterator();
		while (itr.hasNext()) {
			System.out.println(itr.getData());
			itr.next();
		}
		
	}
}
