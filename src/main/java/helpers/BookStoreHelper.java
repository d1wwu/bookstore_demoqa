package helpers;

import data.BooksDto;
import data.BooksResponseDto;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookStoreHelper extends ApiHelper {

    private final String baseUrl = "https://demoqa.com/BookStore/v1/";

    public List<BooksResponseDto.Books> getBooks() {
        configure(baseUrl, false);
        Response response = requestSpec.get("Books");
        System.out.println("GET Books: " + response.statusLine());
        return response.getBody().as(BooksResponseDto.class).getBooks();
    }

    public int addBook(String userId, String isbn) {
        List<BooksDto.CollectionOfIsbns> isbns = new ArrayList<>();
        BooksDto booksDto = new BooksDto();
        BooksDto.CollectionOfIsbns isbnItem = booksDto.new CollectionOfIsbns();
        booksDto.setUserId(userId);
        isbnItem.setIsbn(isbn);
        isbns.add(isbnItem);
        booksDto.setCollectionOfIsbns(isbns);
        configure(baseUrl, true);
        requestSpec.body(booksDto);
        Response response = requestSpec.post("Books");
        System.out.println("POST Books: " + response.statusLine());
        return response.getStatusCode();
    }

    public int deleteBooks(String userId) {
        configure(baseUrl, true);
        Response response = requestSpec.delete("Books/?UserId=" + userId);
        System.out.println("DELETE Books: " + response.statusLine());
        return response.getStatusCode();
    }

    public int deleteBook(String userId, String isbn) {
        setRequest(userId, isbn);
        Response response = requestSpec.delete("Book");
        System.out.println("DELETE Book: " + response.statusLine());
        return response.getStatusCode();
    }

    public int replaceBook(String userId, String isbnFrom, String isbnTo) {
        setRequest(userId, isbnTo);
        Response response = requestSpec.put("Books/" + isbnFrom);
        System.out.println("PUT Books: " + response.statusLine());
        return response.getStatusCode();
    }

    private void setRequest(String userId, String isbn) {
        configure(baseUrl, true);
        JSONObject requestParams = new JSONObject();
        requestParams.put("userId", userId);
        requestParams.put("isbn", isbn);
        requestSpec.body(requestParams.toString());
    }
}
