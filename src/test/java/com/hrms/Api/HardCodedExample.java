package com.hrms.Api;

import com.hrms.utils.ApiConstants;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class HardCodedExample {
    /**
     * REST assured  - Java library specifically developed to automate REST endpoints
     * <p>
     * Given - Preparing your request
     * When - What action will you perform, what type of call are u making
     * Then - assertion/verification
     */

    //Base URI for all endpoints
    String baseURI = RestAssured.baseURI = "http://3.237.189.167/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MTQ5NzQ2OTksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYxNTAxNzg5OSwidXNlcklkIjoiMjY3NiJ9.Kp6FvPQey7ICpq8-ephWamne15VTOsHLxStfvZESwT8";


    @Test
    public void sampleTest() {

        //preparing request to get one employee

        //log all - shows you all the details you sent(headers, parameters...)
        RequestSpecification preparingGetOneEmployeeRequest =
                given().header("Authorization", token)
                        .header("Content-Type", "Application/json")
                        .queryParam("employee_id", "17369A").log().all();

        //sending the request to the endpoint
        Response getOneEmployeeResponse = preparingGetOneEmployeeRequest
                .when().get("/getOneEmployee.php");

        //printing the response
        System.out.println(getOneEmployeeResponse.asString());
        System.out.println(getOneEmployeeResponse.prettyPrint());

        //assert that status code is 200
        getOneEmployeeResponse.then().assertThat().statusCode(200);


    }

    @Test
    public void aPostCreateEmployee() {
        RequestSpecification createEmployeeRequest = given().header("Authorization", token)
                .header("Content-Type", "Application/json")
                .body("{\n" +
                        "  \"emp_firstname\": \"Mariii\",\n" +
                        "  \"emp_lastname\": \"Romaniuk\",\n" +
                        "  \"emp_middle_name\": null,\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2021-02-11\",\n" +
                        "  \"emp_status\": \"employee\",\n" +
                        "  \"emp_job_title\": \"IT Analyst\"\n" +
                        "}");

        //making post call to create employee
        Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");

        //printing emp id
        createEmployeeResponse.prettyPrint();


        //assert that  status code is 200
        createEmployeeResponse.then().assertThat().statusCode(201);

        //get emp id
        String employeeID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");

        //print emp id
        System.out.println(employeeID);

        //assert that message contains entry created
        createEmployeeResponse.then().assertThat().body("Message", equalTo("Entry Created"));

        //assert that emp id is 15841A
        createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("Mariii"));

    }

    @Test
    public void bgetCreatedEmployee() {
        RequestSpecification getCreatedEmployee = given().header("Authorization", token)
                .header("Content-Type", "Application/json")
                .queryParam("employee_id", "17369A ");

        //return type of when is RESPONSE
        Response getEmployeeResponse = getCreatedEmployee.when().get("/getOneEmployee.php");
        getEmployeeResponse.prettyPrint();

        //asserting emp_id
        String empID = getEmployeeResponse.jsonPath().getString("employee[0].employee_id");


        //we're checking if the empID is equal to the one mentioned in String
        boolean VerifyEmployeeID = empID.equalsIgnoreCase("55885.0");
        System.out.println(VerifyEmployeeID);

        // Assert.assertTrue(VerifyEmployeeID);


        getEmployeeResponse.then().assertThat().body("employee[0].employee_id", equalTo("55885.0"));


    }

    @Test
    public void cUpdateEmployee() {
        RequestSpecification updateEmployeeRequest = given().header("Authorization", token)
                .header("Content-Type", "Application/json").body("{\n" +
                        "  \"employee_id\": \"12345\",\n" +
                        "  \"emp_firstname\": \"ABC\",\n" +
                        "  \"emp_lastname\": \"Romaniuk\",\n" +
                        "  \"emp_middle_name\": null,\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2021-02-11\",\n" +
                        "  \"emp_status\": \"employee\",\n" +
                        "  \"emp_job_title\": \"IT Analyst\"\n" +
                        "}");
        Response UpdateEmployeeResponse = updateEmployeeRequest.when().put("/updateEmployee.php");
        UpdateEmployeeResponse.prettyPrint();

        JsonPath js = UpdateEmployeeResponse.jsonPath();
        String empFirstName = js.getString("employee[0].emp_firstname");
        System.out.println(empFirstName);

        assertThat(empFirstName, equalTo("ABC"));

        //other way
        UpdateEmployeeResponse.then().assertThat().body(empFirstName, equalTo("ABC"));


        //one more way


    }

    @Test
    public void dPartiallyUpdateEmployee() {
//        preparing /getOneEmployee.php request
        RequestSpecification partiallyUpdatedEmployeeRequest =
                given().header("Authorization", token)
                        .header("Content-Type", "Application/json")
                        .body("{\n" +
                                "            \"employee_id\": \"55885.0\",        \n" +
                                "            \"emp_lastname\": \"White\"\n" +
                                "        }");
        Response partiallyUpdatedEmployeeResponse = partiallyUpdatedEmployeeRequest
                .when()
                .patch("/updatePartialEmplyeesDetails.php");
        String response = partiallyUpdatedEmployeeResponse.prettyPrint();

        partiallyUpdatedEmployeeResponse.then().assertThat().statusCode(201);

        JsonPath jp = partiallyUpdatedEmployeeResponse.jsonPath();
        Object message = jp.get("Message");
        System.out.println(message);

        assertThat(message, equalTo("Entry updated"));
        partiallyUpdatedEmployeeResponse.then().body("Message", containsString("Entry updated"));
    }


    @Test
    public void deleteEmployee() {
        RequestSpecification deleteEmployeeRequest =
                given().header("Authorization", token)
                        .header("Content-Type", "Application/json")
                        .queryParam("employee_id", "55885.0");
        Response deleteEmployeeResponse = deleteEmployeeRequest.when().delete("/deleteEmployee.php");

        deleteEmployeeResponse.prettyPrint();

        //assertion
        deleteEmployeeResponse.then()
                .assertThat().body("message", containsString("Entry deleted"));


    }

    @Test
    public void getAllEmployees() {
        RequestSpecification getAllEmployeesRequest = given().header(ApiConstants.Header_Content_Type, ApiConstants.Content_type_)
                .header(ApiConstants.Header_Authorization, token);

        Response getAllEmployeesResponse = getAllEmployeesRequest.when().get("/getAllEmployees.php");

        /**printing the response*/
        getAllEmployeesResponse.prettyPrint();

        /**storing response as a string*/
        String response1 = getAllEmployeesResponse.asString();


         JsonPath js=new JsonPath(response1);

        int count=js.getInt("Employees.size()");
        System.out.println(count);

        for (int i = 0; i < count; i++) {
           String allEmployeeIds= js.getString("Employees["+i+"]employee_id");
            System.out.println(allEmployeeIds);

            }

        }



    }




