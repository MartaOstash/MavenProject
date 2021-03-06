Feature: Configure PIM - Optional fields
@pim
  Scenario:Unchecking unnecessary checkboxes
    When enter valid credentials
    And click on login button
    Then verify dashboard is displayed
    When click on PIM
    When click on configuration drop down
    And click on optional fields
    Then click on edit button
    And uncheck unnecessary checkboxes
      | Show SSN field in Personal Details |
      | Show SIN field in Personal Details |
  And click on save button











