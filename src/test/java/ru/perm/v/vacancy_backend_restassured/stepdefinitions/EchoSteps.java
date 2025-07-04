package ru.perm.v.vacancy_backend_restassured.stepdefinitions;

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
    // moved to Settings.java
    // private static final String BASE_URL = "http://127.0.0.1:8080/api/echo";
    private Response response;
    private RequestSpecification request;

    Logger logger = LoggerFactory.getLogger(EchoSteps.class);

//    @Before
//    public void setup() {
//        logger.info("Setup EchoSteps");
//    }

    @Given("The Echo API is available. Message = {string}.") // string - указать тип
    public void the_vacancy_api_is_available(String message) {
        logger.info("Given stage: Message = " + message + ".");
        RestAssured.baseURI = Setting.BASE_URL + "/echo";
        logger.info("RestAssured.baseURI = " + RestAssured.baseURI);
        RestAssured.useRelaxedHTTPSValidation(); // skip ssl verify
        request = given()
                .header("Content-Type", "text/plain");
    }

    @When("I request GET echo MESSAGE. Message = {string}.")
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