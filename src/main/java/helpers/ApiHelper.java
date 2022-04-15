package helpers;

import core.TestManager;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    protected final TestManager manager;
    protected RequestSpecification requestSpec;

    public ApiHelper() {
        manager = TestManager.getInstance();
    }

    protected void configure(String baseURI, boolean auth) {
        RestAssured.baseURI = baseURI;
        if (auth) {
            requestSpec = given().auth().preemptive().basic(manager.getUsername(), manager.getPassword());
        } else {
            requestSpec = RestAssured.given();
        }
        requestSpec.header("Content-Type", "application/json");
    }
}
