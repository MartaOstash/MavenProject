package com.hrms.MYUDEMYTESTING;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.hrms.FilesForMYAPI.ReusableMethods;
import com.hrms.FilesForMYAPI.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

public class oAuthTest {
    public static void main(String[] args) {
        String response = given().queryParam("access_token","")
                .when()
                .get("https://rahulshettyacademy.com/getCourse.php").asString();

        System.out.println(response);
    }
    }





