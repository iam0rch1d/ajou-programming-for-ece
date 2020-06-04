class Book {
	private final String title;
	private String author;
	private boolean isBorrowable = true;

	// Constructors
	Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	Book(String title) {
		this.title = title;
	}

	// Getters
	String getTitle() {
		return title;
	}

	String getAuthor() {
		return author;
	}

	boolean getIsBorrowable() {
		return isBorrowable;
	}

	// Setters
	void setIsBorrowable(boolean isBorrowable) {
		this.isBorrowable = isBorrowable;
	}

	// Class methods
	// printInformation() - Print book information
	void printInformation() {
		System.out.print("Book title: " + getTitle());

		if (author != null) {
			System.out.print(" / Author: " + getAuthor());
		}

		System.out.println();
	}
}
