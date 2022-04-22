package helpers;

import core.TestManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    private static final String baseURI = "https://demoqa.com";
    protected final TestManager manager;
    protected RequestSpecification requestSpec;
    protected static String token;

    public ApiHelper() {
        manager = TestManager.getInstance();
        RestAssured.baseURI = baseURI;
    }

    protected void setRequestSpec(boolean auth) {
        requestSpec = given()
                .filter(new FilterHelper())
                .contentType(ContentType.JSON);
        if (auth && token != null) {
            requestSpec
                    .headers("Authorization", "Bearer " + token);
        }
    }
}
