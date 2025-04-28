Feature: Echo test
  I want to interact with the Vacancy REST API

  Scenario Outline: Send message and check return
    Given Given. the Echo API is available. Message = "<send>".
    When When. I request GET echo MESSAGE. Message = "<send>".
    Then Then stage. The response equal "<answer>".
    Examples:
      | send | answer |
      | AAA  | AAA    |
      | BBB  | BBB    |
