package java.korean.librarymanagement.person;

import java.util.ArrayList;
import java.korean.librarymanagement.collection.Collection;

public abstract class Person {
	private final int numberOfBorrowable;
	private final int daysOfBorrowable;
	private final int id;
	private final String name;
	private final ArrayList<Collection> borrowingCollection;
	
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
	
	public Collection findCollection(String title) {

	}
}
