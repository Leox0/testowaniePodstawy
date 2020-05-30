package homeworkTDDp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BookpShelfTest {

    @Test
    void shouldCreateValidBook() throws BookCreationException {
        //given
        String expectedTitle = "Pan Tadeusz";
        String expectedAuthor = "Adam Mickiewicz";
        String expectedIsbn = "9780781800334";

        //when
        Bookp book = new Bookp(expectedTitle, expectedAuthor, expectedIsbn);

        //then
        assertEquals(expectedAuthor, book.getAuthor());
        assertEquals(expectedIsbn, book.getISBN());
        assertEquals(expectedTitle, book.getTitle());
        assertNotNull(book.getId());
    }

    @Test
    void shouldCreateBookShelfOfGivenSize() {
        //given
        Integer expectedBookShelfSize = 5;

        //when
        BookShelf bookShelf = new BookShelf(expectedBookShelfSize);

        //then
        assertEquals(expectedBookShelfSize, bookShelf.getBookShelfSize());
    }

    @Test
    void shouldAddBookToTheShelf() throws BookCreationException, BookShelfOperationException {
        //given
        Bookp validBook = createValidBook();
        BookShelf bookShelf = new BookShelf(1);

        //when
        bookShelf.addBook(validBook);

        //then
        assertThat(bookShelf.getBooks())
                .containsOnly(validBook);
    }


    @Test
    void shouldRemoveBookFromShelfByBookId() throws BookCreationException, BookShelfOperationException {
        //given
        Bookp validBook = createValidBook();
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
        String expectedIsbn = "9780781800331";

        //when
        Executable executable = () -> new Bookp(expectedTitle, expectedAuthor, expectedIsbn);

        //then
        BookCreationException bookCreationException = assertThrows(BookCreationException.class, executable);
        assertEquals(Bookp.INVALID_ISBN,bookCreationException.getLocalizedMessage());
    }

    @Test
    void shouldThrowExceptionForAddingBookToTheFullShelf() throws BookCreationException, BookShelfOperationException {
        //given
        Bookp validBook1 = createValidBook();
        Bookp validBook2 = createValidBook();
        BookShelf bookShelf = new BookShelf(1);
        bookShelf.addBook(validBook1);

        //when
        Executable executable = () -> bookShelf.addBook(validBook2);

        //then
        assertThat(bookShelf.getBooks())
                .containsOnly(validBook1);
        BookShelfOperationException bookShelfOperationException = assertThrows(BookShelfOperationException.class, executable);
        assertThat(bookShelfOperationException).hasMessage(BookShelf.SHELF_IS_FULL);
    }

    @Test
    void shouldThrowExceptionAddingTheseSameBookTwice() throws BookCreationException, BookShelfOperationException {
        //given
        Bookp validBook1 = createValidBook();
        BookShelf bookShelf = new BookShelf(2);
        bookShelf.addBook(validBook1);

        //when
        Executable executable = () -> bookShelf.addBook(validBook1);

        //then
        assertThat(bookShelf.getBooks())
                .containsOnly(validBook1);
        BookShelfOperationException bookShelfOperationException = assertThrows(BookShelfOperationException.class, executable);
        assertThat(bookShelfOperationException).hasMessage(BookShelf.BOOK_ALREADY_ON_SHELF);
    }

    @Test
    void shouldThrowExceptionRemovingBookNotPresentOnTheShelf() {
        //given
        BookShelf bookShelf = new BookShelf(0);
        Long bookToRemoveId = 1L;

        //when
        Executable executable = () -> bookShelf.removeBook(bookToRemoveId);

        //then
        BookShelfOperationException bookShelfOperationException = assertThrows(BookShelfOperationException.class, executable);
        assertThat(bookShelfOperationException).hasMessage(BookShelf.CANNOT_REMOVE_BOOK_NOT_EXISTS);
    }

    private Bookp createValidBook() throws BookCreationException {
        String expectedTitle = "Pan Tadeusz";
        String expectedAuthor = "Adam Mickiewicz";
        String expectedIsbn = "9780781800334";

        return new Bookp(expectedTitle, expectedAuthor, expectedIsbn);
    }
}