Feature: Vacancy API Operations

  Scenario: Retrieve all vacancies
    When I request all vacancies
    Then the response status should be 200
    And the response should contain 4 vacancies
    And vacancy 0 should have name "NAME_VACANCY_1_COMPANY_1"
    And vacancy 1 should have name "NAME_VACANCY_2_COMPANY_1"
    And vacancy 2 should have name "NAME_VACANCY_1_COMPANY_2"
    And vacancy 3 should have name "NAME_VACANCY_1_COMPANY_3"

  Scenario: Get vacancy with id=1
    When I request vacancy with id 1
    Then the response status should be 200
    And vacancy 0 should have name "NAME_VACANCY_1_COMPANY_1"

  Scenario: Search vacancies by criteria "Vacancy 1 Company 1"
    When I search for vacancies with title containing "NAME_VACANCY_1_COMPANY_1"
    Then the response status should be 200
    And the response should contain 1 vacancies
    And vacancy 0 should have name "NAME_VACANCY_1_COMPANY_1"

  Scenario: Search vacancies by criteria "Vacancy 1 Company 2"
    When I search for vacancies with title containing "NAME_VACANCY_1_COMPANY_2"
    Then the response status should be 200
    And the response should contain 1 vacancies
    And vacancy 0 should have name "NAME_VACANCY_1_COMPANY_2"

#  Scenario: Create new vacancy
#    Given I have a new vacancy with:
#      | title       | description         |
#      | Go Engineer | Golang development  |
#    When I create the vacancy
#    Then the response status should be 200
#    And the response should have name "Go Engineer"
#
#  Scenario: Update existing vacancy
#    Given I have updated data for vacancy 1:
#      | title            | description             |
#      | Senior Java Dev  | Senior Java programming |
#    When I update vacancy 1
#    Then the response status should be 200
#    And the response should have name "Senior Java Dev"
#
#  Scenario: Delete vacancy
#    When I delete vacancy with id 2
#    Then the response status should be 200
#    And vacancy 2 should no longer exist

