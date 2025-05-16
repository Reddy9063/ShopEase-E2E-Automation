package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import java.util.Map;

public class ApiUtils {
    public static Response post(String url, Map<String, Object> body, Map<String, String> headers) {
        RequestSpecification req = RestAssured.given().headers(headers).body(body);
        return req.post(url);
    }

    public static Response get(String url, Map<String, String> headers) {
        RequestSpecification req = RestAssured.given().headers(headers);
        return req.get(url);
    }

    public static void validateStatusCode(Response response, int expected) {
        if (response.getStatusCode() != expected) {
            throw new AssertionError("Expected status code: " + expected + ", but got: " + response.getStatusCode());
        }
    }
}
