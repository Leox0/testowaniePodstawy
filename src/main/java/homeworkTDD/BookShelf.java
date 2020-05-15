package homeworkTDD;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BookShelf {

    private final Book[] books;

    private BookShelf(int size) {
        this.books = new Book[size];
    }

    public Book[] getBooks() {
        return books;
    }

    public BookShelf createEmptyBookShelf(int size) {
        //TODO implement this
        throw new NotImplementedException();
    }

    public void addBook(Book book) throws BookShelfOperationException {
        //TODO implement this
        throw new NotImplementedException();
    }

    public void removeBook(int id) throws BookShelfOperationException {
        //TODO implement this
        throw new NotImplementedException();
    }
}
