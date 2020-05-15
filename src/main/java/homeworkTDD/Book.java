package homeworkTDD;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Book {

    private static int ID_COUNTER = 0;
    private long id;
    private String title;
    private String author;
    private String ISBN;

    public Book(String title, String author, String ISBN) throws BookCreationException {
        validateISBN(ISBN);
        this.id = ID_COUNTER++;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    private void validateISBN(String ISBN) throws BookCreationException {
        //TODO implement this
        throw new NotImplementedException();
    }

    public static int getIdCounter() {
        return ID_COUNTER;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }
}
