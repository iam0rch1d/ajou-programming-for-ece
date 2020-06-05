package javalang.korean.librarymanagement.collection;

import java.time.LocalDate;
import javalang.korean.librarymanagement.person.Person;

public class ClassMaterial extends Collection{
	public ClassMaterial(String title, String author) {
		super(title, author, false);
	}

	@Override
	public void setBorrowable(boolean isBorrowable) throws CollectionException {
		throw new CollectionException("setBorrowable 에러: 수업자료는 대출할 수 없습니다.");
	}

	@Override
	public void setBorrowedDate(LocalDate borrowedDate) throws CollectionException {
		throw new CollectionException("setBorrowedDate 에러: 수업자료는 대출할 수 없습니다.");
	}

	@Override
	public void setBorrower(Person borrower) throws CollectionException {
		throw new CollectionException("setBorrower 에러: 수업자료는 대출할 수 없습니다.");
	}
}
