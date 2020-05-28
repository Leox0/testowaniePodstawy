package homeworkTDD;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookShelfTest {

    @Test
    void shouldCreateValidBook() {
        //given
        int exprectedIdCounter = 1;
        //when
        try {
            Book book = new Book("Tytul", "Autor", "007462542X");
        } catch (BookCreationException e) {
            System.out.println(e.getMessage());
        }
        //then
        assertEquals(exprectedIdCounter, Book.getIdCounter());

    }

    @Test
    void shouldCreateBookShelfOfGivenSize() {
        //TODO implement this

    }

    @Test
    void shouldAddBookToTheShelf() {
        //TODO implement this

    }

    @Test
    void shouldRemoveBookFromShelfByBookId() {
        //TODO implement this

    }

    @Test
    void shouldThrowExceptionCreatingBookWithInvalidISBN() {
        //TODO implement this

    }

    @Test
    void shouldThrowExceptionForAddingBookToTheFullShelf() {
        //TODO implement this

    }

    @Test
    void shouldThrowExceptionRemovingFirstBookFromEmptyShelf() {
        //TODO implement this

    }

    @Test
    void shouldThrowExceptionRemovingBookNotPresentOnTheShelf() {
        //TODO implement this

    }
}