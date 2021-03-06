Feature: Add Employee Functionality

  Background:
    When enter valid credentials
    And click on login button
    Then verify dashboard is displayed
    When click on PIM
    And click on add employee button

    @addEmployeeWithoutLogin
  Scenario: Add employee without login details
    Then enter first and last name
    And click on save button
    Then verify employee is added successfully

      @addEmployeeWithLogin
  Scenario: Add employee with login credentials and with middle name
    Then enter first and last name and middle name
    When click on login details checkbox
    Then enter login details
    And click on save button
    Then verify employee is added successfully to the list

        @parameter
  Scenario: Add employee without login details but with middle name
          Then enter first name "Alik", middle name "Osmana" and last name "Kursunss"
          And click on save button
          Then verify that "Alik Osmana Kursunss" is added successfully

          @examples
  Scenario Outline: Adding multiple employees without login details
    When enter "<FirstName>", "<MiddleName>" and "<LastName>"
    And click on save button
    Then verify "<FirstName>", "<MiddleName>" and "<LastName>" is added successfully

      Examples:
          |FirstName|MiddleName|LastName|
          |Markis     |J         |Smith   |
          |Johnis     |K         |Wick    |



      @dtWithHeader
  Scenario: Adding multiple employees at one execution
    When add multiple employees and verify they are added successfully
      | FirstName | MiddleName | LastName | EmployeeId |
      | Jacks     | J          | Toronto  | 111761111  |
      | Davids    | K          | Wick     | 22223452   |



  @excelTask
  Scenario: Adding multiple employees from excel
    When add multiple employees from excel "AddEmployee" sheet and verify they are added


    @db @regression
    Scenario:Adding Employee and database validation
      And enter first name "John", middle name "Osmaa" and last name "Kursuna"
      And capture employeeId
      And click on save button
      Then collect employee data from hrms database
      And verify data from database and ui is matching













