package dto;

public class LoginResponseDto {

    private String userId;
    private String username;
    private String password;
    private String token;
    private String expires;
    private String created_date;
    private boolean isActive;

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }
}
