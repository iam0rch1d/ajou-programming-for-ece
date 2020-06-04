package java.korean.librarymanagement.collection;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import java.korean.librarymanagement.person.Person;

public abstract class Collection {
	private String title = null;
	private String author = null;
	protected boolean borrowable;
	protected LocalDate borrowedDate;
	protected Person Borrower;

	public Collection(String title, String author, boolean borrowable) {
		this.title = title;
		this.author = author;
		this.borrowable = borrowable;
	}
	
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public boolean isBorrowable() {
		return borrowable;
	}

	public abstract void setBorrowable(boolean borrowable) throws CollectionException;
	
	public LocalDate getBorrowedDate() {
		return borrowedDate;
	}

	public abstract void setBorrowedDate(LocalDate borrowedDate) throws CollectionException;

	public Person getBorrower() {
		return Borrower;
	}

	public abstract void setBorrower(Person borrower) throws CollectionException;

}
