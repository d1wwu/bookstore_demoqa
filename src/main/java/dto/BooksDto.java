package dto;

import java.util.List;

public class BooksDto {

    private String userId;
    private List<CollectionOfIsbns> collectionOfIsbns;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCollectionOfIsbns(List<CollectionOfIsbns> collectionOfIsbns) {
        this.collectionOfIsbns = collectionOfIsbns;
    }

    public class CollectionOfIsbns {
        private String isbn;

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
    }
}
