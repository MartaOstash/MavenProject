package com.hrms.Api;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hrms.testbase.PageInitializer;
import com.hrms.utils.ApiConstants;
import com.hrms.utils.CommonMethods;
import com.hrms.utils.apiCommonMethods;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.Assert;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ApiTestingFinalSteps extends PageInitializer {

    RequestSpecification request;
    Response response;
    static String employeeID;
    static String updated_employee_middle_name;
    static String updated_firstname;


    //--------------Scenario Creating an Employee

    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        //  preparing request to create Employee
        apiCommonMethods.createEmployeeRequest(generateTokenSteps.token, CommonMethods.readJson(ApiConstants.CREATE_EMPLOYEE_JSON));

//        File input=new File("/Users/martaostash/eclipse-workspace/CucumberProject2/CucumberFrameWorkBatch8/src/test/resources/JsonData/createUser.json");
//        JsonObject CreateUserData=null;
//        try {
//            //parsing the input file
//            JsonElement fileElement= JsonParser.parseReader(new FileReader(input));
//            CreateUserData = fileElement.getAsJsonObject();
//            JsonElement Message = CreateUserData.get("Message");
//            JsonArray Employee_Detail = CreateUserData.getAsJsonArray("Employee");
//            JsonElement EmployeeZero = Employee_Detail.get(0);
//            JsonObject Employee_0 =  EmployeeZero.getAsJsonObject();
//            System.out.println(Employee_0.get("employee_id"));
//
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
    }


    @When("a POST call is made to create an Employee")
    public void a_POST_call_is_made_to_create_an_Employee() {
        //sending the request to create Employee
        response = request.when().post(ApiConstants.CREATE_EMPLOYEE_URI);
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(int statusCode) {
        //assert that status code is 201
        response.then().assertThat().statusCode(201);
    }

    @Then("the employee is created contains key {string} and value {string}")
    public void the_employee_is_created_contains_key_and_value(String key, String value) {
        //assert that the message contains Entry created
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employeeID {string} is stored in the global variable to be used for other calls")
    public void the_employeeID_is_stored_in_the_global_variable_to_be_used_for_other_calls(String value) {
        //extract the EmployeeID from the json response
        employeeID = response.jsonPath().getString(value);
    }

    //--------------- Scenario: Retrieving the created employee
    @Given("a request is prepared to retrieve the created employee")
    public void a_request_is_prepared_to_retrieve_the_created_employee() {
        request = apiCommonMethods.getOneEmployeeRequest(generateTokenSteps.token, employeeID);
    }

    @When("a GET call is made to retrieve the created Employee")
    public void a_GET_call_is_made_to_retrieve_the_created_Employee() {
        response = request.when().get(ApiConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the status code for retrieving the created employee is {int}")
    public void the_status_code_for_retrieving_the_created_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the retrieved EmployeeID {string} matches the globally stored EmployeeID")
    public void the_retrieved_EmployeeID_matches_the_globally_stored_EmployeeID(String value) {
        //assert that employee id is same as the one stored globally
        response.then().assertThat().body(value, equalTo(employeeID));
    }


    @Then("the retrieved data matches the data used to create the employee")
    public void the_retrieved_data_matches_the_data_used_to_create_the_employee() {

//        response.then().assertThat().body("employee[0].emp_firstname",equalTo("moazzam"));

        JsonPath jpath = response.jsonPath();
        String emp_first_name = jpath.getString("employee[0].emp_firstname");
        String emp_last_name = jpath.getString("employee[0].emp_lastname");
        String emp_middle_name = jpath.getString("employee[0].emp_middle_name");
        String emp_birthday = jpath.getString("employee[0].emp_birthday");
        String emp_gender = jpath.getString("employee[0].emp_gender");
        String emp_job_title = jpath.getString("employee[0].emp_job_title");
        String emp_status = jpath.getString("employee[0].emp_status");

        assertThat(emp_first_name, equalTo("Martusia"));
        assertThat(emp_last_name, equalTo("Ostash"));
        assertThat(emp_middle_name, equalTo("Mary"));
        assertThat(emp_birthday, equalTo("2021-02-11"));
        assertThat(emp_gender, equalTo("Female"));
        assertThat(emp_job_title, equalTo("IT Analyst"));
        assertThat(emp_status, equalTo("Employee"));

    }

    @Then("the retrieved data at {string} matches the data used to create the employee with employee ID {string}")
    public void the_retrieved_data_at_matches_the_data_used_to_create_the_employee_with_employee_ID(String employeeObject, String responseEmployeeID, DataTable dataTable) {

        String body = response.asString();
        System.out.println("THE RESPONSE IS " + body);

        JsonElement Response_json = JsonParser.parseString(body);
        JsonObject Response_json_obj = Response_json.getAsJsonObject();
        JsonElement Employee_data = Response_json_obj.get("employee");
        JsonArray Employee_data_array = Employee_data.getAsJsonArray();
        JsonElement Employee_0_information = Employee_data_array.get(0);
        JsonObject Employee_Info = Employee_0_information.getAsJsonObject();
        System.out.println("The employee first name is " + Employee_Info.get("emp_firstname").toString());
        System.out.println("The employee birthday is " + Employee_Info.get("emp_birthday").toString());


        System.out.println("RESPONSE " + Employee_data);


    }

//        //A map to have the data expected in the response --> from feature file
//        List <Map<String, String>> expectedData = dataTable.asMaps();
//
//        //getting data from response body
//        List<Map<String, String >> actualData = response.body().jsonPath().get(employeeObject);
//
//        //loop through the keys in our hardcoded data and get the value
//        int index = 0;
//        for(Map<String, String> map : expectedData) {
//            Set<String> keys = map.keySet();
//            //loop through Keys and get their value and assert
//
//            for(String key : keys) {
//                String expectedValue = map.get(key);
//                String actualValue = actualData.get(index).get(key);
//                Assert.assertEquals(expectedValue, actualValue);
//            }
//            index ++;
//        }
//        String empID = response.body().jsonPath().getString(responseEmployeeID);
//        Assert.assertTrue(empID.contentEquals(employeeID));
//    }

    //-----------------------Scenario 3 Update the created Employee

    @Given("a request is prepared to update the created employee")
    public void a_request_is_prepared_to_update_the_created_employee() {

        updated_employee_middle_name = "updated middle name";
        JSONObject payload = new JSONObject();
        payload.put("employee_id", employeeID);
        payload.put("emp_firstname", "Martusia");
        payload.put("emp_lastname", "Ostash");
        payload.put("emp_middle_name", updated_employee_middle_name);
        payload.put("emp_gender", "F");
        payload.put("emp_birthday", "1986-02-18");
        payload.put("emp_status", "Employee");
        payload.put("emp_job_title", "IT Analyst");

        String finalPayload = payload.toString();

        request = given().header(ApiConstants.Header_Content_Type, ApiConstants.Content_type_)
                .header(ApiConstants.Header_Authorization, generateTokenSteps.token)
                .body(finalPayload);
    }

    @When("a PUT call is made to update the employee")
    public void a_PUT_call_is_made_to_update_the_employee() {
        response = request.when().put(ApiConstants.UPDATE_EMPLOYEE_URI);
    }

    @Then("the status code is {int}")
    public void the_status_code_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the updated employee contains key {string} and value {string}")
    public void the_updated_employee_contains_key_and_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    //----------------Scenario 4 Retrieving the updated employee

    @Given("a request is prepared to retrieve the updated employee")
    public void a_request_is_prepared_to_retrieve_the_updated_employee() {

        request = given().header(ApiConstants.Header_Content_Type, ApiConstants.Content_type_)
                .header(ApiConstants.Header_Authorization, generateTokenSteps.token)
                .queryParam("employee_id", employeeID);
    }

    @When("a GET call is made to retrieve the updated Employee")
    public void a_GET_call_is_made_to_retrieve_the_updated_Employee() {
        response = request.when().get(ApiConstants.GET_ONE_EMPLOYEE_URI);

    }

    @Then("the status code for retrieving the updated employee is {int}")
    public void the_status_code_for_retrieving_the_updated_employee_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the retrieved Employee_Middle_Name {string} matches the updated_middle_name")
    public void the_retrieved_Employee_Middle_Name_matches_the_updated_middle_name(String updatedMiddleName) {
        //response.then().assertThat().body(updatedMiddleName, equalTo(updated_employee_middle_name));

        String actualMiddleName = response.jsonPath().getString(updatedMiddleName);
        assertThat(actualMiddleName, equalTo(updated_employee_middle_name));
    }

    //-------------------Scenario 5 Partially bupdating the employee

    @Given("a request is prepared to partially update the employee")
    public void a_request_is_prepared_to_partially_update_the_employee() {

        updated_firstname = "Ksenya";
        JSONObject payload = new JSONObject();
        payload.put("employee_id", employeeID);
        payload.put("emp_firstname", updated_firstname);
        String payloadPartiallyUpdated = payload.toString();

        request = given().header(ApiConstants.Header_Content_Type, ApiConstants.Content_type_)
                .header(ApiConstants.Header_Authorization, generateTokenSteps.token)
                .body(payloadPartiallyUpdated);
    }

    @When("a PATCH call is made to partially update the employee")
    public void a_PATCH_call_is_made_to_partially_update_the_employee() {

        response = request.when().patch(ApiConstants.PARTIALLY_UPDATE_EMPLOYEE_URI);

    }

    @Then("the partially updated employee contains key {string} and value {string}")
    public void the_partially_updated_employee_contains_key_and_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the partially updated employee {string} matches the updated_firstname")
    public void the_partially_updated_employee_matches_the_updated_firstname(String value) {
        response.then().assertThat().body(value, equalTo(updated_firstname));
    }

    //-------------------Scenario 6 Delete the created employee

    @Given("a request is prepared to delete the employee")
    public void a_request_is_prepared_to_delete_the_employee() {
        request = given().header(ApiConstants.Header_Content_Type, ApiConstants.Content_type_)
                .header(ApiConstants.Header_Authorization, generateTokenSteps.token)
                .queryParam("employee_id", employeeID);
    }

    @When("a DELETE call is made")
    public void a_DELETE_call_is_made() {

        response = request.when().delete(ApiConstants.DELETE__EMPLOYEE_URI);
    }

    @Then("the employee is successfully deleted with the {string} in response {string}")
    public void the_employee_is_successfully_deleted_with_the_in_response(String key, String value) {

        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employee ID {string} is the same as the one stored in global")
    public void the_employee_ID_is_the_same_as_the_one_stored_in_global(String value) {

        response.then().assertThat().body(value, equalTo(employeeID));
    }

    //-----------------Scenatio 7 Get all Employees

    @Given("a request is prepared to get all employees")
    public void a_request_is_prepared_to_get_all_employees() {
        request = given().header(ApiConstants.Header_Content_Type, ApiConstants.Content_type_)
                .header(ApiConstants.Header_Authorization, generateTokenSteps.token);
    }

    @When("a GET call is made to retrive all employees")
    public void a_GET_call_is_made_to_retrive_all_employees() {

        response = request.when().get(ApiConstants.GET_ALL_EMPLOYEE_URI);
    }

    @Then("it contains key1 {string} and key2 {string}")
    public void it_contains_key1_and_key2(String key1, String key2) {
        response.then().assertThat().body(containsString(key1)).body(containsString(key2));

//          String payload = response.asString();
//          JsonPath js = new JsonPath(payload);
//          int count = js.getInt("Employees.size()");
//          //print all employees ids
//          for(int i = 0; i < count; i ++) {
//              String allEmployeeIDs = js.getString("Employees[" + i + "].employee_id");
//              System.out.println(allEmployeeIDs);
//          }

//        JsonParser parser = new JsonParser();
//        JsonObject response_gettAllEmployees = (JsonObject) parser.parse(response.asString());

        String response_string = response.asString();
        JsonObject response_getAllEmployees =JsonParser.parseString(response_string).getAsJsonObject() ;
        JsonArray array_ofAllEmployees = response_getAllEmployees.get("Employees").getAsJsonArray();
        for (int i=0; i<100;i++) {
            JsonObject Employee_0_information = array_ofAllEmployees.get(i).getAsJsonObject();
            String employee_id_x = Employee_0_information.get("employee_id").getAsString();
            System.out.println(employee_id_x);
        }





//        for(JsonElement x : array_ofAllEmployees) {
//
//            JsonObject employee_Data = x.getAsJsonObject();
//            String employee_firstname = employee_Data.get("emp_firstname").getAsString();
//
//            System.out.println(employee_firstname);
//        }

    }

    //------------------Scenario 8 Retrieve all Employees Status

    @Given("a request is prepared to get all employees status")
    public void a_request_is_prepared_to_get_all_employees_status() {
        request = given().header(ApiConstants.Header_Content_Type, ApiConstants.Content_type_)
                .header(ApiConstants.Header_Authorization, generateTokenSteps.token);
    }

    @When("a GET call is made to retrieve all employees status")
    public void a_GET_call_is_made_to_retrieve_all_employees_status() {
        response = request.when().get(ApiConstants.GET_ALL_EMPLOYEE_Status_URI);
    }

    @Then("it contains the value1 {string} value2 {string}")
    public void it_contains_the_value1_value2(String value1, String value2) {
        //response.then().assertThat().body(containsString(value1)).body(containsString(value2));


        String response_string = response.asString();
        JsonObject response_getAllEmployees = JsonParser.parseString(response_string).getAsJsonObject();
        JsonElement Employee_status_list = response_getAllEmployees.get("Employee Status List");

        String status_list = Employee_status_list.toString();
//            System.out.print(status_list);

        String Status = status_list.replace("[", "");
        String status = Status.replace("]", "");
        String[] Status_List = status.split(",");
        System.out.println(Status_List[6]);
    }

    //----------------Scenario 10  Get Job Titles

    @Given("a request is prepared to get job titles")
    public void a_request_is_prepared_to_get_job_titles() {
        request = given().header(ApiConstants.Header_Content_Type, ApiConstants.Content_type_)
                .header(ApiConstants.Header_Authorization, generateTokenSteps.token);
    }

    @When("a GET call is made to retrive all job titles")
    public void a_GET_call_is_made_to_retrive_all_job_titles() {
        response = request.when().get(ApiConstants.GET_ALL_JOB_TITLES_URI);
    }

    @Then("response contains value1 {string} and value2 {string}")
    public void response_contains_value1_and_value2(String value1, String value2) {
        //response.then().assertThat().body(containsString(value1)).body(containsString(value2));

        String respone_string = response.asString();
        JsonObject response_getAllEmployees = JsonParser.parseString(respone_string).getAsJsonObject();
        JsonElement EmployeeStatusLIST = (response_getAllEmployees.get("Employee Status List"));
        System.out.println(EmployeeStatusLIST);


    }
}

