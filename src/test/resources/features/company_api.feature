Feature: Company API Operations

  Background:
    Given the Company API is available

  Scenario: Get specific company with id 1
    When I request company with id 1
    Then the response Company API status should be 200
    And the response should have id 1
    And the response should have name "Company 1"

  Scenario: Get all companies
    When I request ALL companies
    Then the response Company API status should be 200
    And there are 3 companies in the response
