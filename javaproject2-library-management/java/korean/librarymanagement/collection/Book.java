package java.korean.librarymanagement.collection;

import java.time.LocalDate;
import java.korean.librarymanagement.person.Person;

public class Book extends Collection{
	public Book(String title, String author) {
		super(title, author, true);
	}

	@Override
	public void setBorrowable(boolean borrowable) {
		this.borrowable = borrowable;
	}

	@Override
	public void setBorrowedDate(LocalDate borrowedDate) {
		this.borrowedDate = borrowedDate;
	}

	@Override
	public void setBorrower(Person borrower) {
		this.Borrower = borrower;
	}
}
