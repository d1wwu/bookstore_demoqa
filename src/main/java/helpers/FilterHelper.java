package helpers;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class FilterHelper implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);
        String content = "";
        if (requestSpec.getBody() != null) {
            content = "Request =>" + System.lineSeparator() + requestSpec.getBody() + System.lineSeparator();
        }
        content += "Response =>" + System.lineSeparator() + response.getStatusLine() + System.lineSeparator() +
                response.getBody().asPrettyString();
        Allure.addAttachment(requestSpec.getMethod() + " " + requestSpec.getURI(), content);
        return response;
    }
}
