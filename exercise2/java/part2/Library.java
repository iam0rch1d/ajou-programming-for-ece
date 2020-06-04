class Library {
	private final Book[] bookArray;

	// Constructor
	Library(int numBook) {
		bookArray = new Book[numBook];
	}

	// Setters
	void setBook(int bookNo, Book book) {
		if (bookNo >= 0 && bookNo <= bookArray.length) {
			bookArray[bookNo] = book;
		} else {
			System.out.println("Out of book array index range.");
			System.out.println("---");
		}
	}

	// Class methods
	// isBookExisting() - Check if book is set in [bookArray]
	boolean isBookExisting(int bookNo) {
		if (bookNo <= bookArray.length && bookArray[bookNo] != null) {
			return true;
		} else {
			System.out.println("Book doesn't exist.");
			System.out.println("---");
			return false;
		}
	}

	// isBookBorrowable() - Check if book is borrowable
	boolean isBookBorrowable(int bookNo) {
		return isBookExisting(bookNo) && bookArray[bookNo].getIsBorrowable();
	}

	// borrowBook() - Borrow book if existing, unborrowed
	void borrowBook(int bookNo) {
		if (!isBookExisting(bookNo)) {
			return;
		} else if (isBookBorrowable(bookNo)) {
			bookArray[bookNo].setIsBorrowable(false);
			System.out.println("Borrowed '" + bookArray[bookNo].getTitle() + "'.");
		} else {
			System.out.println("Can't borrow '" + bookArray[bookNo].getTitle() + "' now.");
		}

		System.out.println("---");
	}

	// returnBook() - Return book if existing, borrowed
	void returnBook(int bookNo) {
		if (!isBookExisting(bookNo)) {
			return;
		} else if (isBookBorrowable(bookNo)) {
			System.out.println("'" + bookArray[bookNo].getTitle() + "' is not borrowed now.");
		} else {
			bookArray[bookNo].setIsBorrowable(true);
			System.out.println("'" + bookArray[bookNo].getTitle() + "' has returned.");
		}

		System.out.println("---");
	}

	// printBookArray() - Print information of all books belonging to library
	void printBookArray() {
		System.out.println("*** Information about books in library ***");

		for (Book element: bookArray) {
			if (element != null) {
				element.printInformation();
			}
		}

		System.out.println("---");
	}
}
