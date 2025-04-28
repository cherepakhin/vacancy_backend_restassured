Feature: Echo test
  I want to interact with the Vacancy REST API

  Scenario Outline: Send message and check return
    Given Given. the Echo API is available. Message = "<send>".
    When When. I request GET echo MESSAGE. Message = "<send>".
    Then The response equal "<answer>".
    And  Status OK.
    Examples:
      | send | answer |
      | AAA  | AAA    |
      | BBB  | BBB    |


  Scenario: Send ONE message and check return with param. "TEST_MESSAGE" - param of test
    Given Given. the Echo API is available. Message = "TEST_MESSAGE".
    When When. I request GET echo MESSAGE. Message = "TEST_MESSAGE".
    Then The response equal "TEST_MESSAGE".
    And  Status OK.

