// Part2.kt
class Book(private val title: String, private val author: String?) {
    constructor(title: String): this(title, null)

    private var isBorrowable = true

    // Getters
    fun getTitle(): String {
        return title
    }

    fun getIsBorrowable(): Boolean {
        return isBorrowable
    }

    // Setters
    fun setIsBorrowable(isBorrowable: Boolean) {
        this.isBorrowable = isBorrowable
    }

    // Class methods
    // printInformation() - Print book information
    fun printInformation() {
        print("Book title: $title")

        if (author != null) {
            print(" / Author: $author")
        }

        println()
    }
}


class Library(numBook: Int) {
    private val bookArray = Array<Book?>(numBook) { null }

    // Setters
    fun setBook(bookNo: Int, book: Book) {
        if (bookNo >= 0 && bookNo < bookArray.size) {
            bookArray[bookNo] = book
        } else {
            println("Out of book array index range.")
            println("---")
        }
    }

    // Class methods
    // isBookExisting() - Check if book is set in [bookArray]
    private fun isBookExisting(bookNo: Int): Boolean {
        return if (bookNo >= 0 && bookNo < bookArray.size && bookArray[bookNo] != null) {
            true
        } else {
            println("Book doesn't exist.")
            println("---")

            false
        }
    }

    // isBookBorrowable() - Check if book is borrowable
    private fun isBookBorrowable(bookNo: Int): Boolean {
        return isBookExisting(bookNo) && bookArray[bookNo]!!.getIsBorrowable()
    }

    // borrowBook() - Borrow book if existing, unborrowed
    fun borrowBook(bookNo: Int) {
        if (!isBookExisting(bookNo)) {
            return
        } else if (isBookBorrowable(bookNo)) {
            bookArray[bookNo]?.setIsBorrowable(false)
            println("Borrowed '" + bookArray[bookNo]?.getTitle() + "'.")
        } else {
            println("Can't borrow '" + bookArray[bookNo]?.getTitle() + "' now.")
        }

        println("---")
    }

    // returnBook() - Return book if existing, borrowed
    fun returnBook(bookNo: Int) {
        if (!isBookExisting(bookNo)) {
            return
        } else if (isBookBorrowable(bookNo)) {
            println("'" + bookArray[bookNo]?.getTitle() + "' is not borrowed now.")
        } else {
            bookArray[bookNo]?.setIsBorrowable(true)
            println("'" + bookArray[bookNo]?.getTitle() + "' has returned.")
        }

        println("---")
    }

    // printBookArray() - Print information of all books belonging to library
    fun printBookArray() {
        println("*** Information about books in library ***")
        for (element in bookArray) {
            element?.printInformation()
        }

        println("---")
    }
}


fun main() {
    val library = Library(5)

    library.setBook(0, Book("Moby-Dick", "Herman Melville"))
    library.setBook(1, Book("Nineteen Eighty-Four: A Novel", "George Orwell"))
    library.setBook(2, Book("The Moon and Sixpence", "William Somerset"))
    library.setBook(3, Book("Zorba the Greek", "Nikos Kazantzakis"))
    library.setBook(4, Book("Hong Gil Dong"))

    library.printBookArray()
    library.borrowBook(0)
    library.borrowBook(1)
    library.borrowBook(4)
    library.borrowBook(2)
    library.borrowBook(4)
    library.returnBook(0)
    library.returnBook(2)
}
