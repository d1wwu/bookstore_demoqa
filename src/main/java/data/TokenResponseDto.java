package data;

public class TokenResponseDto {

    private String token;
    private String expires;
    private String status;
    private String result;

    public String getToken() {
        return token;
    }

    public String getStatus() {
        return status;
    }

    public String getResult() {
        return result;
    }
}
