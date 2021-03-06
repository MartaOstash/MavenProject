#Author: Instructor
@apiWorkFlow

Feature: Syntax HRMS API Workflow
  Description: This feature tests Syntax HRMS API WorkFlow

  Background:
    Given  a JWT is generated


  Scenario: Creating an employee
    Given a request is prepared to create an employee
    When a POST call is made to create an Employee
    Then the status code for creating an employee is 201
    And the employee is created contains key "Message" and value "Entry Created"
    And the employeeID "Employee[0].employee_id" is stored in the global variable to be used for other calls


  Scenario: Retrieving the created employee
    Given a request is prepared to retrieve the created employee
    When a GET call is made to retrieve the created Employee
    Then the status code for retrieving the created employee is 200
    And the retrieved EmployeeID "employee[0].employee_id" matches the globally stored EmployeeID
    And the retrieved data matches the data used to create the employee
    And the retrieved data at "employee" matches the data used to create the employee with employee ID "employee[0].employee_id"
      | emp_firstname | emp_middle_name | emp_lastname | emp_birthday | emp_gender | emp_job_title | emp_status |
      | Martusia      | Mary            | Ostash       | 2021-02-11   | Female     | IT Analyst    | Employee   |


  Scenario: Update the created employee
    Given  a request is prepared to update the created employee
    When a PUT call is made to update the employee
    Then the status code is 201
    And  the updated employee contains key "Message" and value "Entry updated"

  Scenario: Retrieving the updated employee
    Given  a request is prepared to retrieve the updated employee
    When a GET call is made to retrieve the updated Employee
    Then the status code for retrieving the updated employee is 200
    And  the retrieved Employee_Middle_Name "employee[0].emp_middle_name" matches the updated_middle_name


  Scenario: Partially updating the employee
    Given  a request is prepared to partially update the employee
    When a PATCH call is made to partially update the employee
    Then the status code is 201
    And  the partially updated employee contains key "Message" and value "Entry updated"
    And  the partially updated employee "employee[0].emp_firstname" matches the updated_firstname


  Scenario:Delete the created the employee
    Given  a request is prepared to delete the employee
    When a DELETE call is made
    Then the status code is 201
    And  the employee is successfully deleted with the "message" in response "Entry deleted"
    And the employee ID "employee[0].employee_id" is the same as the one stored in global

  Scenario: get all Employees
    Given a request is prepared to get all employees
    When a GET call is made to retrive all employees
    Then the status code is 200
    And it contains key1 "Total Employees" and key2 "Employees"


  Scenario: get all Employees status
    Given a request is prepared to get all employees status
    When a GET call is made to retrieve all employees status
    Then the status code is 200
    And it contains the value1 "Employee" value2 "Worker"






