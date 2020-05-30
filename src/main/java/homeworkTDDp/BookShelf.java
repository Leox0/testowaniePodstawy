package homeworkTDDp;

import java.util.ArrayList;
import java.util.List;

public class BookShelf {

    public static final String SHELF_IS_FULL = "Pułka jest już pełna. Zdejmij którąś z książek aby zrobić miejsce";
    public static final String CANNOT_REMOVE_BOOK_NOT_EXISTS = "Książka o podanym id nie istnieje, wiec nie można jej usunąć";
    public static final String BOOK_ALREADY_ON_SHELF = "Podana książka już jest na pułce";

    private final List<Bookp> books;
    private final Integer bookShelfSize;

    public BookShelf(int size) {
        bookShelfSize = size;
        this.books = new ArrayList<>(size);
    }

    public List<Bookp> getBooks() {
        return books;
    }

    public Integer getBookShelfSize() {
        return bookShelfSize;
    }

    public void addBook(Bookp book) throws BookShelfOperationException {
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

        Bookp book = findBookById(id);
        if (book == null) {
            throw new BookShelfOperationException(CANNOT_REMOVE_BOOK_NOT_EXISTS);
        }

        books.remove(book);
    }

    private Bookp findBookById(Long id) {
        Bookp book = null;
        for (Bookp e : books) {
            if (e.getId().equals(id)) {
                book = e;
                break;
            }
        }
        return book;
    }
}
