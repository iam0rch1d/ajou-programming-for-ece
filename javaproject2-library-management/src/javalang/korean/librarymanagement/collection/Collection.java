package javalang.korean.librarymanagement.collection;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javalang.korean.librarymanagement.Library;
import javalang.korean.librarymanagement.person.Person;

public abstract class Collection {
	private final String title;
	private final String author;
	boolean isBorrowable;
	Person borrower;
	LocalDate borrowedDate;

	Collection(String title, String author) {
		this.title = title;
		this.author = author;
		isBorrowable = true;
	}

	Collection(
		Library library,
		String title,
		String author,
		String isBorrowableToString,
		String borrowerUidToString,
		String borrowedDateToString
	) {
		this.title = title;
		this.author = author;
		this.isBorrowable = Boolean.parseBoolean(isBorrowableToString);

		for (Person element : library.getPersonArrayList()) {
			if (element.getUid() == Integer.parseInt(borrowerUidToString)) {
				this.borrower = element;

				break;
			}
		}

		this.borrowedDate = LocalDate.parse(borrowedDateToString, DateTimeFormatter.ISO_DATE);
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

	public Person getBorrower() {
		return borrower;
	}

	public String getBorrowerInformation() {
		if (borrower != null) {
			return borrower.getName() + "(" + borrower.getUid() + ")";
		} else {
			return "--";
		}
	}

	public LocalDate getBorrowedDate() {
		return borrowedDate;
	}

	public String getBorrowedDateToString() {
		if (borrowedDate != null) {
			return borrowedDate.toString();
		} else {
			return "--";
		}
	}

	public abstract void setIsBorrowable(boolean isBorrowable) throws CollectionException;

	public abstract void setBorrower(Person borrower) throws CollectionException;

	public abstract void setBorrowedDate(LocalDate borrowedDate) throws CollectionException;
}
