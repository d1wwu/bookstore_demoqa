package helpers;

import core.TestManager;
import data.TokenResponseDto;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    protected final TestManager manager;
    protected RequestSpecification requestSpec;
    protected static TokenResponseDto tokenResponseDto;

    public ApiHelper() {
        manager = TestManager.getInstance();
    }

    protected void configure(String baseURI, boolean auth) {
        RestAssured.baseURI = baseURI;
        if (auth) {
            Assert.assertNotNull(tokenResponseDto, "Token was not generated:");
            Assert.assertNotNull(tokenResponseDto.getToken(), "This user has not token:");
            requestSpec = given().headers("Authorization" , "Bearer " + tokenResponseDto.getToken());
        } else {
            requestSpec = given();
        }
        requestSpec.header("Content-Type", "application/json");
    }
}
