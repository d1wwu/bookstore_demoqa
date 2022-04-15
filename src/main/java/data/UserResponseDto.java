package data;

import java.util.List;

public class UserResponseDto {

    private String userId;
    private String username;
    private List<BooksResponseDto.Books> books;

    public List<BooksResponseDto.Books> getBooks() {
        return books;
    }
}
