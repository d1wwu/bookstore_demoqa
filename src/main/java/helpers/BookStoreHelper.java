package helpers;

import dto.BooksDto;
import dto.BooksResponseDto;
import io.restassured.response.Response;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BookStoreHelper extends ApiHelper {

    public List<BooksResponseDto.Books> getBooks() {
        setRequestSpec(false);
        Response response = requestSpec.get(RouteHelper.books());
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
        setRequestSpec(true);
        requestSpec.body(booksDto);
        Response response = requestSpec.post(RouteHelper.books());
        return response.getStatusCode();
    }

    public int deleteBooks(String userId) {
        setRequestSpec(true);
        Response response = requestSpec.delete(RouteHelper.userBooks(userId));
        return response.getStatusCode();
    }

    public int deleteBook(String userId, String isbn) {
        setRequestSpec(userId, isbn);
        Response response = requestSpec.delete(RouteHelper.book());
        return response.getStatusCode();
    }

    public int replaceBook(String userId, String isbnFrom, String isbnTo) {
        setRequestSpec(userId, isbnTo);
        Response response = requestSpec.put(RouteHelper.isbnBook(isbnFrom));
        return response.getStatusCode();
    }

    private void setRequestSpec(String userId, String isbn) {
        setRequestSpec(true);
        JSONObject requestParams = new JSONObject();
        requestParams.put("userId", userId);
        requestParams.put("isbn", isbn);
        requestSpec.body(requestParams.toString());
    }
}
