package homeworkTDD;


import java.util.ArrayList;
import java.util.List;

public class BookShelf {
    public static final String SHELF_IS_FULL = "Shelf is full. Remove some book.";
    public static final String CANNOT_REMOVE_BOOK_NOT_EXISTS = "This book doesn't exist";
    public static final String BOOK_ALREADY_ON_SHELF = "This book is already on shelf";


    private final List<Book> books;
    private final Integer bookShelfSize;

    public BookShelf(int size) {
        bookShelfSize = size;
        this.books = new ArrayList<>(size);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Integer getBookShelfSize() {
        return bookShelfSize;
    }

    public void addBook(Book book) throws BookShelfOperationException {
        if (books.size() == bookShelfSize) {
            throw new BookShelfOperationException(SHELF_IS_FULL);
        }
        if (findBookById(book.getId()) != null) {
            throw new BookShelfOperationException(BOOK_ALREADY_ON_SHELF);
        }
        books.add(book);
    }

    public void removeBook(Long id) throws BookShelfOperationException {
        /**
         * solution with java stream API (the most elegant)
         *
         *       Book book = books.stream()
         *                 .filter(e -> e.getId().equals(id))
         *                 .findFirst()
         *                 .orElseThrow(() -> new BookShelfOperationException(CANNOT_REMOVE_BOOK_NOT_EXISTS));
         *
         *         books.remove(book);
         */
        Book book = findBookById(id);
        if (book == null) {
            throw new BookShelfOperationException(CANNOT_REMOVE_BOOK_NOT_EXISTS);
        }
        books.remove(book);
    }

    private Book findBookById(Long id) {
        Book book = null;
        for (Book e : books) {
            if (e.getId().equals(id)) {
                book = e;
                break;
            }
        }
        return book;
    }

}
