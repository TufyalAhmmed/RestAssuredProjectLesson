package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class RestAssuredExtension {

public static RequestSpecification Request;
    public RestAssuredExtension(){

        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("http://localhost:3000");
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec);
    }
    public static void GetOpsWithPathParameter(String url, Map<String,String>pathParams) throws URISyntaxException {
        //Act
        Request.pathParams(pathParams);
        Request.get(new URI(url));
    }
    public static Object GetOps(String url)  {
        //Act
        try {
            Request.get(new URI(url));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
            return null;
    }
    public static ResponseOptions<Response> PostOpsWithBodyAndPathParams(String url, Map<String, String> pathParams, Map<String, String> body) {
        Request.pathParams(pathParams);
        Request.body(body);
            return Request.post(url);
    }
}
