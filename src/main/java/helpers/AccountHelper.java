package helpers;

import data.LoginResponseDto;
import data.TokenResponseDto;
import data.UserResponseDto;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;


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

    private void generateToken() {
        setRequest();
        Response response = requestSpec.post("GenerateToken");
        System.out.println("POST GenerateToken: " + response.statusLine());
        tokenResponseDto = response.getBody().as(TokenResponseDto.class);
        Assert.assertEquals(tokenResponseDto.getStatus(), Status.SUCCESS.toString(), tokenResponseDto.getResult());
    }

    public LoginResponseDto login() {
        generateToken();
        setRequest();
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

    private void setRequest() {
        configure(baseURI, false);
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", manager.getUsername());
        requestParams.put("password", manager.getPassword());
        requestSpec.body(requestParams.toString());
    }
}
