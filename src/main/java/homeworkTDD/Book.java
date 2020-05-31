package homeworkTDD;

public class Book {
    public static final String INVALID_ISBN_LENGTH = "ISBN has invalid length";
    public static final String INVALID_ISBN = "ISBN is not correct";

    private static Long ID_COUNTER = 0L;
    private Long id;
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

    private void validateISBN(String isbn) throws BookCreationException {
        int[] validationFactors = new int[]{1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3};
        if (isbn.length() != validationFactors.length + 1) {
            throw new BookCreationException(INVALID_ISBN_LENGTH);
        }
        int[] isbnIntArray = isbnStringToIntArray(isbn);

        int checksum = 0;
        for (int i = 0; i < validationFactors.length; i++) {
            checksum += validationFactors[i] * isbnIntArray[i];
        }

        int reminder = checksum % 10;

        if (reminder != 0 && 10 - reminder != isbnIntArray[isbnIntArray.length - 1]) {
            throw new BookCreationException(INVALID_ISBN);
        }
    }

    public Long getId() {
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

    private static int[] isbnStringToIntArray(String isbn) {
        int[] isbnIntArray = new int[isbn.length()];
        char[] chars = isbn.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            isbnIntArray[i] = Integer.parseInt(String.valueOf(chars[i]));
        }
        return isbnIntArray;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
