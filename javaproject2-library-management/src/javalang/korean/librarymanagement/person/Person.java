package javalang.korean.librarymanagement.person;

import java.util.ArrayList;
import javalang.korean.librarymanagement.collection.*;

public abstract class Person {
	private final int uid;
	private final String name;
	private final int numberOfBorrowable;
	private final int daysOfBorrowable;
	private final ArrayList<Collection> borrowingCollectionArrayList;
	
	Person(int uid, String name, int numberOfBorrowable, int daysOfBorrowable) {
		this.uid = uid;
		this.name = name;
		this.numberOfBorrowable = numberOfBorrowable;
		this.daysOfBorrowable = daysOfBorrowable;
		borrowingCollectionArrayList = new ArrayList<>();
	}

	public int getNumberOfBorrowable() {
		return numberOfBorrowable;
	}

	public int getDaysOfBorrowable() {
		return daysOfBorrowable;
	}

	public int getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Collection> getBorrowingCollectionArrayList() {
		return borrowingCollectionArrayList;
	}
}
