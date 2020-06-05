package javalang.korean.librarymanagement.person;

import java.util.ArrayList;
import javalang.korean.librarymanagement.collection.Collection;

public abstract class Person {
	private final int id;
	private final String name;
	private final int numberOfBorrowable;
	private final int daysOfBorrowable;
	private final ArrayList<Collection> borrowingCollection;
	
	public Person(int id, String name, int numberOfBorrowable, int daysOfBorrowable) {
		this.id = id;
		this.name = name;
		this.numberOfBorrowable = numberOfBorrowable;
		this.daysOfBorrowable = daysOfBorrowable;
		borrowingCollection = new ArrayList<>();
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
	
	//public Collection findCollection(String title) {

	//}
}
