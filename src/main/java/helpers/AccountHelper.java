package helpers;

import data.LoginResponseDto;
import data.TokenResponseDto;
import data.UserResponseDto;
import io.restassured.response.Response;
import org.json.JSONObject;


public class AccountHelper extends ApiHelper {

    private final String baseURI = "https://demoqa.com/Account/v1/";

    public enum Status {
        SUCCESS,
        FAILED;

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    public TokenResponseDto generateToken() {
        configure(baseURI, false);
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", manager.getUsername());
        requestParams.put("password", manager.getPassword());
        requestSpec.body(requestParams.toString());
        Response response = requestSpec.post("GenerateToken");
        System.out.println("POST GenerateToken: " + response.statusLine());
        return response.getBody().as(TokenResponseDto.class);
    }

    public LoginResponseDto login() {
        configure(baseURI, false);
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", manager.getUsername());
        requestParams.put("password", manager.getPassword());
        requestSpec.body(requestParams.toString());
        Response response = requestSpec.post("Login");
        System.out.println("POST Login: " + response.statusLine());
        return response.getBody().as(LoginResponseDto.class);
    }

    public UserResponseDto getUserData(String userId) {
        configure(baseURI, true);
        Response response = requestSpec.get("User/" + userId);
        System.out.println("GET User: " + response.statusLine());
        return response.getBody().as(UserResponseDto.class);
    }
}
