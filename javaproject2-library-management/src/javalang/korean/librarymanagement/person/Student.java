package javalang.korean.librarymanagement.person;

import javalang.korean.librarymanagement.Library;

public class Student extends Person {
	public Student(Library library, int uid, String name) throws PersonException {
		super(library, uid, name, 5, 14);
	}
}
