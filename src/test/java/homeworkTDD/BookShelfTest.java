package homeworkTDD;
//given

//when

//then

import homeworkTDDp.Bookp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookShelfTest {

    @Test
    void shouldCreateValidBook() throws BookCreationException {
        //given
        String expectedTitle = "Pan Tadeusz";
        String expectedAuthor = "Adam Mickiewicz";
        String expectedIsbn = "9780781800334";
        //when
        Book book = new Book(expectedTitle, expectedAuthor, expectedIsbn);
        //then
        assertEquals(expectedTitle, book.getTitle());
        assertEquals(expectedAuthor, book.getAuthor());
        assertEquals(expectedIsbn, book.getISBN());
        assertNotNull(book.getId());

    }

    @Test
    void shouldCreateBookShelfOfGivenSize() {
        //given
        Integer expectedBookShelfOfGivenSize = 5;
        //when
        BookShelf bookShelf = new BookShelf(expectedBookShelfOfGivenSize);
        //then
        assertEquals(expectedBookShelfOfGivenSize, bookShelf.getBookShelfSize());
    }

    @Test
    void shouldAddBookToTheShelf() throws BookCreationException, BookShelfOperationException {
        //given
        Book validBook = createValidBook();
        BookShelf bookShelf = new BookShelf(1);

        //when
        bookShelf.addBook(validBook);

        //then
        assertThat(bookShelf.getBooks()).containsOnly(validBook);

    }

    @Test
    void shouldRemoveBookFromShelfByBookId() throws BookCreationException, BookShelfOperationException {
        //given
        Book validBook = createValidBook();
        BookShelf bookShelf = new BookShelf(1);
        Integer initialBookShelfSize = bookShelf.getBookShelfSize();
        bookShelf.addBook(validBook);

        //when
        bookShelf.removeBook(validBook.getId());

        //then
        assertThat(bookShelf.getBooks()).isEmpty();
        assertThat(bookShelf.getBookShelfSize()).isEqualTo(initialBookShelfSize);


    }

    @Test
    void shouldThrowExceptionCreatingBookWithInvalidISBN() {

        //given
        String expectedTitle = "Pan Tadeusz";
        String expectedAuthor = "Adam Mickiewicz";
        String invalidIsbn = "9780781800335";

        //when
        Executable executable = () -> new Book(expectedTitle, expectedAuthor, invalidIsbn);

        //then
        BookCreationException bookCreationException = assertThrows(BookCreationException.class, executable);
        assertEquals(Book.INVALID_ISBN, bookCreationException.getMessage());

    }

    @Test
    void shouldThrowExceptionForAddingBookToTheFullShelf() throws BookCreationException, BookShelfOperationException {
        //given
        Book validBook1 = createValidBook();
        Book validBook2 = createValidBook();
        BookShelf bookShelf = new BookShelf(1);
        bookShelf.addBook(validBook1);

        //when
        Executable executable = () -> bookShelf.addBook(validBook2);

        //then
        assertThat(bookShelf.getBooks()).containsOnly(validBook1);
        BookShelfOperationException bookShelfOperationException = assertThrows(BookShelfOperationException.class, executable);
        assertEquals(BookShelf.SHELF_IS_FULL, bookShelfOperationException.getMessage());

    }


    @Test
    void shouldThrowExceptionAddingTheseSameBookTwice() throws BookCreationException, BookShelfOperationException {
        //given
        Book validBook1 = createValidBook();
        BookShelf bookShelf = new BookShelf(2);
        bookShelf.addBook(validBook1);

        //when
        Executable executable = () -> bookShelf.addBook(validBook1);

        //then
        assertThat(bookShelf.getBooks()).containsOnly(validBook1);
        BookShelfOperationException bookShelfOperationException = assertThrows(BookShelfOperationException.class, executable);
        assertThat(bookShelfOperationException).hasMessage(BookShelf.BOOK_ALREADY_ON_SHELF);


    }

    @Test
    void shouldThrowExceptionRemovingBookNotPresentOnTheShelf() throws BookCreationException, BookShelfOperationException {
        //given
        Book validBook1 = createValidBook();
        BookShelf bookShelf = new BookShelf(2);
        bookShelf.addBook(validBook1);
        Long bookToRemoveId = 1L;

        //when
        Executable executable = () -> bookShelf.removeBook(bookToRemoveId);

        //then
        assertThat(bookShelf.getBooks()).containsOnly(validBook1);
        BookShelfOperationException bookShelfOperationException = assertThrows(BookShelfOperationException.class, executable);
        assertThat(bookShelfOperationException).hasMessage(BookShelf.CANNOT_REMOVE_BOOK_NOT_EXISTS);
    }

    private Book createValidBook() throws BookCreationException {
        String expectedTitle = "Pan Tadeusz";
        String expectedAuthor = "Adam Mickiewicz";
        String expectedIsbn = "9780781800334";
        return new Book(expectedTitle, expectedAuthor, expectedIsbn);
    }
}