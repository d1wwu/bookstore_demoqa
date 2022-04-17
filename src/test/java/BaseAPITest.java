import data.BooksResponseDto;
import helpers.AccountHelper;
import helpers.BookStoreHelper;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;

public class BaseAPITest {

    private AccountHelper accountHelper;
    private BookStoreHelper bookStoreHelper;
    private String userId;
    BooksResponseDto.Books book;

    @BeforeTest(alwaysRun = true)
    public void init() {
        accountHelper = new AccountHelper();
        bookStoreHelper = new BookStoreHelper();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        book = loginAndAddBook();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        bookStoreHelper.deleteBooks(userId);
    }

    @Test(priority = 1, groups = "api")
    @Description("Test Case1 - Book added to collection")
    public void addBookTest() {
        Assert.assertTrue(findBookInCollection(book.getIsbn()), "Book not added to collection:");
    }

    @Test(priority = 2, groups = "api")
    @Description("Test Case2 - Book deleted from collection")
    public void deleteBookTest() {
        Assert.assertEquals(bookStoreHelper.deleteBook(userId, book.getIsbn()), 204, "Book is absent in collection:");
        Assert.assertFalse(findBookInCollection(book.getIsbn()), "Book not deleted from collection:");
    }

    @Test(priority = 3, groups = "api")
    @Description("Test Case3 - Book replaced in collection")
    public void replaceBookTest() {
        String isbn = extractAnotherBook(book);
        Assert.assertNotNull(isbn, "No data is available:");
        Assert.assertEquals(bookStoreHelper.replaceBook(userId, book.getIsbn(), isbn), 200, "Can't replace book in collection:");
        Assert.assertTrue(findBookInCollection(isbn), "Book not replaced in collection:");
    }

    private BooksResponseDto.Books loginAndAddBook() {
        userId = accountHelper.login().getUserId();
        Assert.assertEquals(bookStoreHelper.deleteBooks(userId), 204, "Can't prepare collection:");
        List<BooksResponseDto.Books> booksList = bookStoreHelper.getBooks();
        BooksResponseDto.Books randomBook = booksList.get(new Random().nextInt(booksList.size()));
        Assert.assertEquals(bookStoreHelper.addBook(userId, randomBook.getIsbn()), 201, "Can't add book to collection:");
        return randomBook;
    }

    private boolean findBookInCollection(String isbn) {
        List<BooksResponseDto.Books> books = accountHelper.getUserData(userId).getBooks();
        for (BooksResponseDto.Books item : books) {
            if (item.getIsbn().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    private String extractAnotherBook(BooksResponseDto.Books book) {
        String isbn = null;
        List<BooksResponseDto.Books> booksList = bookStoreHelper.getBooks();
        for (BooksResponseDto.Books item : booksList) {
            isbn = item.getIsbn();
            if (!isbn.equals(book.getIsbn())) {
                break;
            }
        }
        return isbn;
    }
}
