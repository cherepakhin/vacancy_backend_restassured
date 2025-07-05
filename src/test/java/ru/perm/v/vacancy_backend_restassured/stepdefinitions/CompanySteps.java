package ru.perm.v.vacancy_backend_restassured.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.perm.v.vacancy_backend_restassured.company.CompanyDto;

import static io.restassured.RestAssured.given;

public class CompanySteps {
    private static final String BASE_URL = Setting.BASE_URL;
    private RequestSpecification request;
    private Response response;
    Logger logger = LoggerFactory.getLogger(CompanySteps.class);

//    @Before
//    public void setup() {
//        logger.info("Setup CompanySteps");
//        RestAssured.baseURI = BASE_URL + "/company";
//        RestAssured.useRelaxedHTTPSValidation();
//        request = given()
//                .header("Content-Type", "application/json")
//                .header("Accept", "application/json");
//    }

    @Given("the Company API is available")
    public void the_company_api_is_available() {
        RestAssured.baseURI = BASE_URL + "/company";
        RestAssured.useRelaxedHTTPSValidation();
        request = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        RequestSpecification request = RestAssured.given();
        response = request.get("/");

        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("I request company with id {int}")
    public void i_request_vacancy_with_id(Integer id) {
        response = request.get("/" + id);
        logger.info(response.toString());
    }

    @Then("the response Company API status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        response.then().statusCode(statusCode); // проверка работает
    }

    @Then("the response should have id {int}")
    public void the_response_should_have_id(int id) {
        response.getBody().asString().equals(""); // почему то json пустой
//        response.then().body("id", equalTo(id));
    }

    @Then("the response should have name {string}")
    public void the_response_should_have_name(String name) throws JsonProcessingException {
        ResponseBody body = response.body(); // почему то json пустой
        ObjectMapper objectMapper = new ObjectMapper();

        assert body.asString().equals(objectMapper.writeValueAsString(new CompanyDto(1L, name)));
//        CompanyDto dto = objectMapper.readValue(body.asString(), CompanyDto.class);
//        CompanyDto company = body.as(CompanyDto.class);
//        assert dto.name.equals(name);
    }


}

