Feature: Search Employee Functionality

  Background:
    Given enter valid credentials
    And click on login button
    Then user shall  be able to navigate to employee List page

  Scenario: Search All Job Titles
    And collect job titles from UI Employee List Job Title DropDown
    And collect job titles from hrms DB
    Then verify job titles data from UI Employee List Job Title DropDown and DB is matched