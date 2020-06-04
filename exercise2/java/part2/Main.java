class Main {
	public static void main(String[] args) {
		Library library = new Library(5);

		library.setBook(0, new Book("Moby-Dick", "Herman Melville"));
		library.setBook(1, new Book("Nineteen Eighty-Four: A Novel", "George Orwell"));
		library.setBook(2, new Book("The Moon and Sixpence", "William Somerset"));
		library.setBook(3, new Book("Zorba the Greek", "Nikos Kazantzakis"));
		library.setBook(4, new Book("Hong Gil Dong"));

		library.printBookArray();
		library.borrowBook(0);
		library.borrowBook(1);
		library.borrowBook(4);
		library.borrowBook(0);
		library.borrowBook(4);
		library.returnBook(0);
		library.returnBook(2);
	}
}
