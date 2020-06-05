package javalang.korean.librarymanagement.collection;

import javalang.korean.librarymanagement.person.Person;

import java.time.LocalDate;

public abstract class Collection {
	private final String title;
	private final String author;
	protected boolean isBorrowable;
	protected LocalDate borrowedDate;
	protected Person borrower;

	public Collection(String title, String author, boolean isBorrowable) {
		this.title = title;
		this.author = author;
		this.isBorrowable = isBorrowable;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public boolean getIsBorrowable() {
		return isBorrowable;
	}

	public LocalDate getBorrowedDate() {
		return borrowedDate;
	}

	public Person getBorrower() {
		return borrower;
	}

	public abstract void setBorrowable(boolean isBorrowable) throws CollectionException;

	public abstract void setBorrowedDate(LocalDate borrowedDate) throws CollectionException;

	public abstract void setBorrower(Person borrower) throws CollectionException;

}
