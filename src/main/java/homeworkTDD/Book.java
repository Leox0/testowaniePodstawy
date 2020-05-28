package homeworkTDD;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Book {
    private static String WRONG_ISBN = "ISBN is not correct";
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
        {
            if (!isISBNGood(ISBN)) {
                throw new BookCreationException(WRONG_ISBN);
            }
        }
    }

    private boolean isISBNGood(String ISBN) {
        // length must be 10
        int n = ISBN.length();
        if (n != 10)
            return false;

        // Computing weighted sum
        // of first 9 digits
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = ISBN.charAt(i) - '0';
            if (0 > digit || 9 < digit)
                return false;
            sum += (digit * (10 - i));
        }

        // Checking last digit.
        char last = ISBN.charAt(9);
        if (last != 'X' && (last < '0' ||
                last > '9'))
            return false;

        // If last digit is 'X', add 10
        // to sum, else add its value
        sum += ((last == 'X') ? 10 : (last - '0'));

        // Return true if weighted sum
        // of digits is divisible by 11.
        return (sum % 11 == 0);
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
