package javalang.korean.librarymanagement.collection;

import java.time.LocalDate;
import java.util.ArrayList;
import javalang.korean.librarymanagement.person.Person;

public class Book extends Collection {
	public Book(String title, String author) {
		super(title, author);
	}

	public Book(
		ArrayList<Person> personArrayList,
		String title,
		String author,
		String isBorrowableToString,
		String borrowerUidToString,
		String borrowedDateToString
	) {
		super(personArrayList, title, author, isBorrowableToString, borrowerUidToString, borrowedDateToString);
	}

	@Override
	public void setIsBorrowable(boolean isBorrowable) {
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
