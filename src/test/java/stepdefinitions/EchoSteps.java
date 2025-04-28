package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

// for test echo.feature

public class EchoSteps {
//    private static final String BASE_URL = "http://127.0.0.1:8080/api/echo";
    private static final String BASE_URL = Setting.BASE_URL;
    private Response response;
    private RequestSpecification request;

    Logger logger = LoggerFactory.getLogger(EchoSteps.class);

    @Before
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        request = given()
                .header("Content-Type", "text/plain");
    }

    @Given("Given. the Echo API is available. Message = {string}.") // string - указать тип
    public void the_vacancy_api_is_available(String message) {
        logger.info("Given stage: Message = " + message + ".");
    }

    @When("When. I request GET echo MESSAGE. Message = {string}.")
    public void i_request_echo_message(String message) {
        logger.info("When stage. Message = " + message + ".");
        response = request.get("/"+ message);
    }

    @Then("Status OK.")
    public void statusOk() {
        logger.info("Then stage");
//        assert "aaa".equals("bbb"); // example bad test
        assert response.getStatusCode() == 200;
    }

    @Then("The response equal {string}.")
    public void echo_equal(String message) {
        logger.info("Then stage. Message from script=" + message);
        logger.info("Then stage. response.getBody()=" + response.getBody().print());
        assert response.getBody().asString().equals(message);
    }
}