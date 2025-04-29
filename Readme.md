Rest тесты для проекта vacancy_backend
[vacancy_backend](https://github.com/cherepakhin/family/tree/master/src/main/resources/db/migration)

Перед проведением тестов запустить проект [vacancy_backend](https://github.com/cherepakhin/family/tree/master/src/main/resources/db/migration).

Используется RestAssured и Cucumber

Прведение тестов:

````shell
./mvnw test
````

Отчеты в target/cucumber-reports/cucumber.html
![Результаты тестов](doc/test_result.png "Результаты тестов")


Cucumber для Before, Given, Then, When:

````java
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

....

@Given("Given. the Echo API is available. Message = {string}.") // string - указать тип
public void the_vacancy_api_is_available(String message) {
    ....
}

@When("When. I request GET echo MESSAGE. Message = {string}.")
public void i_request_echo_message(String message) {
    ....
}

@Then("Status OK.")
public void statusOk() {
    ....
}

````

RestAssured для request, response

````java
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

````
