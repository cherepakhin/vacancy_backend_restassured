Feature: Vacancy API Operations
  As an API client
  I want to interact with the Vacancy REST API
  So that I can manage job vacancies

  Background:
    Given the Vacancy API is available
    And the following vacancies exist
      | n  | title                | description                     | company_n | link  | comment |
      | 1  | Vacancy 1 Company 1  | Description Vacancy 1 Company 1 | 1         |       |         |
      | 2  | Vacancy 2 Company 1  | Description Vacancy 2 Company 1 | 1         |       |         |
      | 3  | Vacancy 1 Company 2  | Description Vacancy 1 Company 2 | 2         |       |         |
      | 4  | Vacancy 2 Company 2  | Description Vacancy 2 Company 2 | 2         |       |         |

  Scenario: Retrieve all vacancies
    When I request all vacancies
    Then the response status should be 200
    And the response should contain 4 vacancies
    And vacancy 1 should have title "Vacancy 1 Company 1"

  Scenario: Get specific vacancy
    When I request vacancy with id 1
    Then the response status should be 200
    And the response should have title "Vacancy 1 Company 1"

#  Scenario: Create new vacancy
#    Given I have a new vacancy with:
#      | title       | description         |
#      | Go Engineer | Golang development  |
#    When I create the vacancy
#    Then the response status should be 200
#    And the response should have title "Go Engineer"
#
#  Scenario: Update existing vacancy
#    Given I have updated data for vacancy 1:
#      | title            | description             |
#      | Senior Java Dev  | Senior Java programming |
#    When I update vacancy 1
#    Then the response status should be 200
#    And the response should have title "Senior Java Dev"
#
#  Scenario: Delete vacancy
#    When I delete vacancy with id 2
#    Then the response status should be 200
#    And vacancy 2 should no longer exist

  Scenario: Search vacancies by criteria "Vacancy 1 Company 1"
    When I search for vacancies with title containing "Vacancy 1 Company 1"
    Then the response status should be 200
    And the response should contain 1 vacancies
    And vacancy 1 should have title "Vacancy 1 Company 1"

  Scenario: Search vacancies by criteria "Vacancy 1 Company 2"
    When I search for vacancies with title containing "Vacancy 1 Company 2"
    Then the response status should be 200
    And the response should contain 1 vacancies
    And vacancy 3 should have title "Vacancy 1 Company 2"
