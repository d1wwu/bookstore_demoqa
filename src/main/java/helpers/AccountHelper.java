package helpers;

import dto.LoginResponseDto;
import dto.TokenResponseDto;
import dto.UserResponseDto;
import io.restassured.response.Response;
import org.json.JSONObject;

public class AccountHelper extends ApiHelper {

    public enum Status {
        SUCCESS("Success"), FAILED("Failed");

        private final String name;

        Status(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public TokenResponseDto generateToken() {
        setRequestSpec(false);
        JSONObject requestParams = new JSONObject();
        requestParams.put("userName", manager.getUsername());
        requestParams.put("password", manager.getPassword());
        requestSpec.body(requestParams.toString());
        Response response = requestSpec.post(RouteHelper.generateToken());
        TokenResponseDto tokenResponseDto = response.getBody().as(TokenResponseDto.class);
        token = tokenResponseDto.getToken();
        return tokenResponseDto;
    }

    public LoginResponseDto login() {
        Response response = requestSpec.post(RouteHelper.login());
        return response.getBody().as(LoginResponseDto.class);
    }

    public UserResponseDto getUserData(String userId) {
        setRequestSpec(true);
        Response response = requestSpec.get(RouteHelper.userAccount(userId));
        return response.getBody().as(UserResponseDto.class);
    }
}
