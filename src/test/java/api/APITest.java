package api;

import assertions.Matcher;
import base.BaseTest;
import dto.BooksResponseDto;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Random;

public class APITest extends BaseTest {

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        bookStoreHelper.deleteBooks(userId);
    }

    @Test(groups = "api")
    @Description("Test Case1 - Book added to collection")
    public void addBookTest() {
        login();
        BooksResponseDto.Books book = addBook();
        Matcher.assertTrue(() -> findBookInCollection(book.getIsbn()), "Book added to collection");
    }

    @Test(groups = "api")
    @Description("Test Case2 - Book deleted from collection")
    public void deleteBookTest() {
        login();
        BooksResponseDto.Books book = addBook();
        Matcher.assertEquals(() -> bookStoreHelper.deleteBook(userId, book.getIsbn()), 204,
                "Delete book from collection");
        Matcher.assertFalse(() -> findBookInCollection(book.getIsbn()), "Book found in collection");
    }

    @Test(groups = "api")
    @Description("Test Case3 - Book replaced in collection")
    public void replaceBookTest() {
        login();
        BooksResponseDto.Books book = addBook();
        BooksResponseDto.Books anotherBook = extractAnotherBook(book);
        Matcher.assertNotNull(anotherBook, "Another book is available");
        Matcher.assertEquals(() -> bookStoreHelper.replaceBook(userId, book.getIsbn(), anotherBook.getIsbn()),
                200, "Replace book in collection");
        Matcher.assertTrue(() -> findBookInCollection(anotherBook.getIsbn()), "Book replaced in collection");
    }

    private BooksResponseDto.Books addBook() {
        List<BooksResponseDto.Books> booksList = bookStoreHelper.getBooks();
        BooksResponseDto.Books randomBook = booksList.get(new Random().nextInt(booksList.size()));
        Assert.assertEquals(bookStoreHelper.addBook(userId, randomBook.getIsbn()), 201,
                "Can't add book to collection:");
        return randomBook;
    }

    private boolean findBookInCollection(String isbn) {
        return accountHelper.getUserData(userId).getBooks().stream()
                .anyMatch(item -> item.getIsbn().equals(isbn));
    }

    private BooksResponseDto.Books extractAnotherBook(BooksResponseDto.Books book) {
        return bookStoreHelper.getBooks().stream()
                .filter(item -> !book.getIsbn().equals(item.getIsbn()))
                .findAny()
                .orElse(null);
    }
}
