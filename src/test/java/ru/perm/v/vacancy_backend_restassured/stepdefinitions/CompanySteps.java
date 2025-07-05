package ru.perm.v.vacancy_backend_restassured.stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.perm.v.vacancy_backend_restassured.company.CompanyDto;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

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

        assertEquals(200, response.getStatusCode());
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

    @Then("the response should have id {long}")
    public void the_response_should_have_id(Long id) throws JsonProcessingException {
        ResponseBody<?> body = response.body();
        ObjectMapper objectMapper = new ObjectMapper();
        CompanyDto dto = objectMapper.readValue(body.asString(), CompanyDto.class);

        assertEquals(id, dto.getN());
    }

    @Then("the response should have name {string}")
    public void the_response_should_have_name(String name) throws JsonProcessingException {
        ResponseBody<?> body = response.body();
        ObjectMapper objectMapper = new ObjectMapper();
        CompanyDto dto = objectMapper.readValue(body.asString(), CompanyDto.class);

        assertEquals(name, dto.getName());

        CompanyDto dtoOther = body.as(CompanyDto.class); // так тоже можно сконвертировать
        assertEquals(name, dtoOther.getName());
    }

    @When("I request ALL companies")
    public void i_request_all_company() {
        response = request.get("/");
        logger.info(response.toString());
    }

    @Then("there are {int} companies in the response")
    public void the_response_should_have_count(Integer count) throws JsonProcessingException {
        logger.info("count: " + count);
        ResponseBody body = response.body(); // почему то json пустой
        ObjectMapper objectMapper = new ObjectMapper();
        CompanyDto[] dtos = objectMapper.readValue(body.asString(), CompanyDto[].class);
        for (CompanyDto dto : dtos) {
            logger.info(dto.toString());
        }
        assertEquals(count.intValue(), dtos.length);
    }

}

