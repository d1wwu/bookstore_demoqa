package data;

import java.util.List;

public class BooksResponseDto {

    private List<Books> books;

    public List<Books> getBooks() {
        return books;
    }

    public class Books {
        private String author;
        private String description;
        private String isbn;
        private Number pages;
        private String publish_date;
        private String publisher;
        private String subTitle;
        private String title;
        private String website;

        public String getIsbn() {
            return isbn;
        }
    }
}
