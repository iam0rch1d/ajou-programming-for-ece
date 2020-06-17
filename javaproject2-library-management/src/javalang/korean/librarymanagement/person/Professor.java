package javalang.korean.librarymanagement.person;

import javalang.korean.librarymanagement.Library;

public class Professor extends Person {
	public Professor(Library library, int uid, String name) throws PersonException {
		super(library, uid, name, 40, 120);
	}
}
