Feature: Vacancy API Operations

  Scenario: Retrieve all vacancies
    When I request all vacancies
    Then the response status should be 200
    And the response should contain 4 vacancies
    And vacancy 0 should have title "Vacancy 1 Company 1"
    And vacancy 1 should have title "Vacancy 2 Company 1"
    And vacancy 2 should have title "Vacancy 1 Company 2"
    And vacancy 3 should have title "Vacancy 2 Company 2"

  Scenario: Get vacancy with id=1
    When I request vacancy with id 1
    Then the response status should be 200
    And vacancy 0 should have title "Vacancy 1 Company 1"

  Scenario: Search vacancies by criteria "Vacancy 1 Company 1"
    When I search for vacancies with title containing "Vacancy 1 Company 1"
    Then the response status should be 200
    And the response should contain 1 vacancies
    And vacancy 0 should have title "Vacancy 1 Company 1"

  Scenario: Search vacancies by criteria "Vacancy 1 Company 2"
    When I search for vacancies with title containing "Vacancy 1 Company 2"
    Then the response status should be 200
    And the response should contain 1 vacancies
    And vacancy 0 should have title "Vacancy 1 Company 2"

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

