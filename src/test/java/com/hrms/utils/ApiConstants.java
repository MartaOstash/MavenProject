package com.hrms.utils;

import io.restassured.RestAssured;

public class ApiConstants {

    public static final String BaseURI= RestAssured.baseURI="http://3.237.189.167/syntaxapi/api";
    public static final String GENERATE_TOKEN_URI=BaseURI+"/generateToken.php";
    public static final String CREATE_EMPLOYEE_URI=BaseURI+"/createEmployee.php";
    public static final String GET_ONE_EMPLOYEE_URI=BaseURI+"/getOneEmployee.php";
    public static final String UPDATE_EMPLOYEE_URI=BaseURI+"/updateEmployee.php";
    public static final String PARTIALLY_UPDATE_EMPLOYEE_URI=BaseURI+"/updatePartialEmplyeesDetails.php";
    public static final String GET_ALL_EMPLOYEE_Status_URI=BaseURI+"/employeeStatus.php";
    public static final String GET_ALL_EMPLOYEE_URI=BaseURI+"/getAllEmployees.php";
    public static final String DELETE__EMPLOYEE_URI=BaseURI+"/deleteEmployee.php";
    public static final String GET_ALL_JOB_TITLES_URI=BaseURI+"/jobTitle.php";

    public static final String Header_Content_Type="Content-type";
    public static final String Content_type_="application/json";

    public static final String Header_Authorization="Authorization";
    public static final String CREATE_EMPLOYEE_JSON=System.getProperty("user.dir")+"/src/test/resources/JsonData/createUser.json";



}

