package javalang.korean.librarymanagement.collection;

import java.time.LocalDate;
import javalang.korean.librarymanagement.Library;
import javalang.korean.librarymanagement.person.Person;

public class ClassMaterial extends Collection {
	public ClassMaterial(String title, String author) {
		super(title, author);
	}

	public ClassMaterial(
		Library library,
		String title,
		String author,
		String isBorrowableToString,
		String borrowerUidToString,
		String borrowedDateToString
	) {
		super(library, title, author, isBorrowableToString, borrowerUidToString, borrowedDateToString);
	}

	@Override
	public void setIsBorrowable(boolean isBorrowable) throws CollectionException {
		throw new CollectionException("수업자료를 대출할 권한이 없습니다.");
	}

	@Override
	public void setBorrowedDate(LocalDate borrowedDate) throws CollectionException {
		throw new CollectionException("수업자료를 대출할 권한이 없습니다.");
	}

	@Override
	public void setBorrower(Person borrower) throws CollectionException {
		throw new CollectionException("수업자료를 대출할 권한이 없습니다.");
	}
}
