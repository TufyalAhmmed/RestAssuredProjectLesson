package Steps;

import io.cucumber.core.gherkin.DataTableArgument;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.it.Data;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

import java.net.URISyntaxException;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetPostSteps {
    private static ResponseOptions<Response> response;

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url) throws URISyntaxException {
//        given().contentType(ContentType.JSON);
       response = (ResponseOptions<Response>) RestAssuredExtension.GetOps(url);
    }

    @And("I perform GET for the post number {string}")
    public void iPerformGETForThePostNumber(String postNumber) {
//        when().get(String.format("http://localhost:3000/posts/%s", postNumber)).
//                then().body("author", is("typicode")).statusCode(200);
        BDDStyleMethod.SimpleGetPost(postNumber);
    }

    @Then("I should see the author name as {string}")
    public void iShouldSeeTheAuthorNameAs(String authorName) {
        assertThat(response.getBody().jsonPath().get("author"),hasItem("typicode"));
    }



    @Then("I should see the author names")
    public void iShouldSeeTheAuthorNames() {
        BDDStyleMethod.PerformContainsCollection();
    }

    @Then("I should verify GET Parameter")
    public void iShouldVerifyGETParameter() {
        BDDStyleMethod.PerformPathParameter();
    }

    @Then("I should verify Query Parameter")
    public void iShouldVerifyQueryParameter() {
        BDDStyleMethod.PerformQueryParameter();
    }

    @Given("I should perform POST operation for {string}")
    public void iShouldPerformPOSTOperationFor(String arg0) {
        BDDStyleMethod.PerformPOSTWithBodyParameter();
    }

    @Given("I perform POST operation for {string}with body")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table)  {
       var data = table.transpose();

       //Set Body
        HashMap<String,String> body = new HashMap<>();
        body.put("name", data.cell(1,1));

        //path Params
        HashMap<String,String> pathParams = new HashMap<>();
        pathParams.put("profileNo", data.cell(1,1));

        //Perform post operation
        response = RestAssuredExtension.PostOpsWithBodyAndPathParams(url,pathParams,body);
    }

    @Then("I should see the body has name as {string}")
    public void iShouldSeeTheBodyHasNameAs(String name) {
        assertThat(response.getBody().jsonPath().get("name"),equalTo(name));
    }
}
