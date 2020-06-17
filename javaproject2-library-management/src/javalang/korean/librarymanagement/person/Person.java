package javalang.korean.librarymanagement.person;

import java.util.ArrayList;
import javalang.korean.librarymanagement.Library;
import javalang.korean.librarymanagement.collection.*;

public abstract class Person {
	private final int uid;
	private final String name;
	private final int numberOfBorrowable;
	private final int daysOfBorrowable;
	private final ArrayList<Collection> borrowingCollectionArrayList;
	
	Person(Library library, int uid, String name, int numberOfBorrowable, int daysOfBorrowable) throws PersonException {
		if (library.isUidDuplicated(uid)) {
			throw new PersonException("회원의 데이터 파일의 line "
				+ (library.getPersonArrayList().size() + 1)
				+ "\n'"
				+ library.getPersonInformationLine()
				+ "'에서 중복된 고유번호(uid)가 존재합니다."
			);
		}

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
