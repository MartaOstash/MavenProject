package com.hrms.utils;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hrms.testbase.PageInitializer;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;

public class apiCommonMethods extends PageInitializer {


    public static RequestSpecification getOneEmployeeRequest(String token, String employeeID) {
        RequestSpecification request = given()
                .header(ApiConstants.Header_Content_Type, ApiConstants.Content_type_)
                .header(ApiConstants.Header_Authorization, token)
                .queryParam("employee_id", employeeID);
        return request;
    }


    public static void createEmployeeBody() {

        File input = new File("/Users/martaostash/eclipse-workspace/CucumberProject2/CucumberFrameWorkBatch8/src/test/resources/JsonData");

        JsonObject createUserData = null;
        try {
            //parsing the input file
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));

            createUserData = fileElement.getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * get one employee request
     *
     * @param token
     * @param EmployeeBody
     * @return
     */


    public static RequestSpecification createEmployeeRequest(String token, String EmployeeBody) {
        return given().header(ApiConstants.Header_Content_Type, ApiConstants.Content_type_)
                .header(ApiConstants.Header_Authorization, token)
                .body(EmployeeBody);
    }


}





