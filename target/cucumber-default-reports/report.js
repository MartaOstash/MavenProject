$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/apiWorkflow.feature");
formatter.feature({
  "name": "Syntax HRMS API Workflow",
  "description": "  Description: This feature tests Syntax HRMS API WorkFlow",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@apiWorkFlow"
    }
  ]
});
formatter.background({
  "name": "",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "a JWT is generated",
  "keyword": "Given "
});
formatter.match({
  "location": "com.hrms.Api.generateTokenSteps.a_JWT_is_generated()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "get all Employees status",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@apiWorkFlow"
    },
    {
      "name": "@marta"
    }
  ]
});
formatter.step({
  "name": "a request is prepared to get all employees status",
  "keyword": "Given "
});
formatter.match({
  "location": "com.hrms.Api.ApiTestingFinalSteps.a_request_is_prepared_to_get_all_employees_status()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a GET call is made to retrieve all employees status",
  "keyword": "When "
});
formatter.match({
  "location": "com.hrms.Api.ApiTestingFinalSteps.a_GET_call_is_made_to_retrieve_all_employees_status()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the status code is 200",
  "keyword": "Then "
});
formatter.match({
  "location": "com.hrms.Api.ApiTestingFinalSteps.the_status_code_is(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "it contains the value1 \"Employee\" value2 \"Worker\"",
  "keyword": "And "
});
formatter.match({
  "location": "com.hrms.Api.ApiTestingFinalSteps.it_contains_the_value1_value2(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "passed"
});
});