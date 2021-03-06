Feature: Searching for multiple employees Functionality

  Background:
    When enter valid credentials
    And  click on login button
    Then verify dashboard is displayed
    When click on PIM
    Then user shall  be able to navigate to employee List page

  @searchById
  Scenario Outline: Searching for multiple employees by ID
    When enter employee id "<ID>"
    And user should be able click on search BTN
    Then Verify that id "<ID>" appears successfully

    Examples:
      | ID    |
      | 11689 |
      | 11691 |
      | 11705 |
      | 11991 |
      | 12011 |

  @searchByName
  Scenario Outline: Searching for multiple Employees by name

    When enter employee first name "<firstName>" and  last name "<lastName>"
    And user should be able click on search BTN
    Then verify that first name "<firstName>" and last name "<lastName>" were found in the table

    Examples:
      | firstName | lastName |
      | Bella     | Harris   |
      | Marta     | Ross     |
      | Bella     | Harris   |
      | Bella     | Harris   |
      | Bella     | Harris   |








