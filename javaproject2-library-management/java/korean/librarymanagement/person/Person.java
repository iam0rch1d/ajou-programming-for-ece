package java.korean.librarymanagement.person;

import java.util.ArrayList;
import java.korean.librarymanagement.collection.Collection;

public abstract class Person {
	private int numberOfBorrowable;
	private int daysOfBorrowable;
	private int id;
	private String name;
	private ArrayList<Collection> borrowingCollection;
	
	public Person(int numberOfBorrowable, int daysOfBorrowable, int id, String name) {
		this.numberOfBorrowable = numberOfBorrowable;
		this.daysOfBorrowable = daysOfBorrowable;
		this.id = id;
		this.name = name;
		borrowingCollection = new ArrayList<Collection>();
	}
		
	public int getNumberOfBorrowable() {
		return numberOfBorrowable;
	}
	public int getDaysOfBorrowable() {
		return daysOfBorrowable;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public ArrayList<Collection> getBorrowingCollection() {
		return borrowingCollection;
	}
	
	public Collection FindCollection(String title) {

	}
}
