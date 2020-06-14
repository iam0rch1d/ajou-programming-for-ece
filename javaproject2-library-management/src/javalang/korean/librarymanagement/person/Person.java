package javalang.korean.librarymanagement.person;

import java.time.LocalDate;
import java.util.ArrayList;
import javalang.korean.librarymanagement.collection.*;

public abstract class Person {
	private final int uid;
	private final String name;
	private final int numberOfBorrowable;
	private final int daysOfBorrowable;
	private final ArrayList<Collection> borrowingCollection;
	
	Person(int uid, String name, int numberOfBorrowable, int daysOfBorrowable) {
		this.uid = uid;
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

	public int getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Collection> getBorrowingCollection() {
		return borrowingCollection;
	}

	public void borrowCollection(Collection collection) throws CollectionException {
		if (collection.getIsBorrowable()) {
			collection.setIsBorrowable(false);
			collection.setBorrower(this);
			collection.setBorrowedDate(LocalDate.now());
			getBorrowingCollection().add(collection);
		} else {
			throw new CollectionException("해당 자료는 이미 대출 중입니다.");
		}
	}

	public void returnCollection(Collection collection) throws CollectionException {
		if (borrowingCollection.contains(collection)) {
			collection.setIsBorrowable(true);
			collection.setBorrower(null);
			collection.setBorrowedDate(null);
			getBorrowingCollection().remove(collection);
		} else {
			throw new CollectionException(name + " 님은 해당 자료를 대출하지 않았습니다.");
		}
	}
}
