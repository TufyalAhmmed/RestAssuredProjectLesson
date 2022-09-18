package Steps;

import io.restassured.http.ContentType;
import org.hamcrest.core.Is;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

public class BDDStyleMethod {
//    private HashMap<Object, Object> postContent;
//    private Object String;

    public static void SimpleGetPost(String postNumber){
        given().contentType(ContentType.JSON).
        when().get(String.format("http://localhost:3000/posts/%s", postNumber)).
                then().body("author", is("typicode")).statusCode(200);
    }
    public static void PerformContainsCollection(){
                given().
                        contentType(ContentType.JSON).
                when().
                    get("http://localhost:3000/posts/").
                then().
                        body("author", containsInAnyOrder("typicode","typicode",null)).statusCode(200);
    }
    public static void PerformPathParameter(){
        given()
                .contentType(ContentType.JSON).
        with()
                .pathParams("post",1).
        when()
                .get("http://localhost:3000/posts/{post}").
        then()
                .body("author",containsString("typicode"));
    }
    public static void PerformQueryParameter(){
                given()
                        .contentType(ContentType.JSON)
                        .queryParam("id",1).
                when()
                         .get("http://localhost:3000/posts/").
                then()
                        .body("author",hasItem("typicode"));
    }
    //Post-method
    public static void PerformPOSTWithBodyParameter(){
       HashMap<String ,String> postContent = new HashMap<>();
       postContent.put("id","2");
       postContent.put("title","Robotium course");
       postContent.put("author","ExecuteAutomation");

                given()
                        .contentType(ContentType.JSON).
                with()
                        .body(postContent).
                when()
                        .post("http://localhost:3000/posts/").
                then()
                        .body("author", Is.is("ExecuteAutomation"));
    }

}
