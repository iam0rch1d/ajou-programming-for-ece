package javalang.korean.librarymanagement.collection;

import java.time.LocalDate;
import javalang.korean.librarymanagement.person.Person;

public class Book extends Collection {
	public Book(String title, String author) {
		super(title, author, true);
	}

	@Override
	public void setBorrowable(boolean isBorrowable) {
		this.isBorrowable = isBorrowable;
	}

	@Override
	public void setBorrowedDate(LocalDate borrowedDate) {
		this.borrowedDate = borrowedDate;
	}

	@Override
	public void setBorrower(Person borrower) {
		this.borrower = borrower;
	}
}
