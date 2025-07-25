package ru.perm.v.vacancy_backend_restassured.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

// for test vacancy_api.feature
public class VacancySteps {

    //  moved to Settings.java
//  private static final String BASE_URL = "http://127.0.0.1:8080/api/vacancy";
    private static final String BASE_URL = Setting.BASE_URL + "/vacancy";
    private Response response;
    private RequestSpecification request;
    private Map<String, String> newVacancyData;
    private Map<String, String> updatedVacancyData;
    Logger logger = LoggerFactory.getLogger(VacancySteps.class);

//    @Before
//    public void setup() {
//        RestAssured.useRelaxedHTTPSValidation(); // skip ssl verify
//        logger.info("Setup VacancySteps");
//        RestAssured.baseURI = BASE_URL;
//        request = given()
//                .header("Content-Type", "application/json")
//                .header("Accept", "application/json");
//    }

    @Given("the Vacancy API is available")
    public void the_vacancy_api_is_available() {
        logger.info("Setup VacancySteps");
        RestAssured.useRelaxedHTTPSValidation(); // skip ssl verify
        RestAssured.baseURI = BASE_URL;
        request = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        response = request.get("/");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Given("the following vacancies exist")
    public void the_following_vacancies_exist(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> vacancies = dataTable.asMaps();
        for (Map<String, String> vacancy : vacancies) {
            request.body(vacancy).put("/");
        }
    }

    @Given("I have a new vacancy with")
    public void i_have_a_new_vacancy_with(io.cucumber.datatable.DataTable dataTable) {
        newVacancyData = dataTable.asMaps().get(0);
    }

    @Given("I have updated data for vacancy {int}")
    public void i_have_updated_data_for_vacancy(Integer id, io.cucumber.datatable.DataTable dataTable) {
        updatedVacancyData = dataTable.asMaps().get(0);
        updatedVacancyData.put("n", id.toString());
    }

    @When("I request all vacancies")
    public void i_request_all_vacancies() {
        response = request.get("/");
    }

    @When("I request vacancy with id {int}")
    public void i_request_vacancy_with_id(Integer id) {
        response = request.get("/" + id);
    }

    @When("I create the vacancy")
    public void i_create_the_vacancy() {
        response = request.body(newVacancyData).put("/");
    }

    @When("I update vacancy {int}")
    public void i_update_vacancy(Integer id) {
        response = request.body(updatedVacancyData).post("/");
    }

    @When("I delete vacancy with id {int}")
    public void i_delete_vacancy_with_id(Integer id) {
        response = request.delete("/" + id);
    }

    @When("I search for vacancies with title containing {string}")
    public void i_search_for_vacancies_with_title_containing(String title) {
        String searchCriteria = "{\"byName\":\"" + title + "\"}";
        response = request.body(searchCriteria).post("/find");
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain {int} vacancies")
    public void the_response_should_contain_vacancies(Integer count) {
        response.then().body("size()", equalTo(count));
    }

    @Then("vacancy {int} should have title {string}")
    public void vacancy_should_have_title(Integer n, String title) {
        response.then().body("find { it.n == " + n + " }.title", equalTo(title));
    }

    @Then("the response should have title {string}")
    public void the_response_should_have_title(String title) {
        response.then().body("title", equalTo(title));
    }

    @Then("vacancy {int} should no longer exist")
    public void vacancy_should_no_longer_exist(Integer id) {
        request.get("/" + id).then().statusCode(500);
    }
}