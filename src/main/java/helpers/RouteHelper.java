package helpers;

public class RouteHelper {

    private static final String ACCOUNT = "/Account";
    private static final String BOOKSTORE = "/BookStore";
    private static final String VERSION = "/v1";

    public static String generateToken() {
        return ACCOUNT + VERSION + "/GenerateToken";
    }

    public static String login() {
        return ACCOUNT + VERSION + "/Login";
    }

    public static String userAccount(String userId) {
        return ACCOUNT + VERSION + "/User/" + userId;
    }

    public static String books() {
        return BOOKSTORE + VERSION + "/Books";
    }

    public static String userBooks(String userId) {
        return BOOKSTORE + VERSION + "/Books/?UserId=" + userId;
    }

    public static String book() {
        return BOOKSTORE + VERSION + "/Book";
    }

    public static String isbnBook(String isbn) {
        return BOOKSTORE + VERSION + "/Books/" + isbn;
    }
}
