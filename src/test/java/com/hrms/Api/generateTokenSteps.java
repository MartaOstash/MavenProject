package com.hrms.Api;

import com.hrms.utils.ApiConstants;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class generateTokenSteps {

    static String token;
    @Given("a JWT is generated")
    public void a_JWT_is_generated() {
        RequestSpecification generateTokenRequest= given().header("Content-Type","application/json")
                .body("{\n" +
                                "     \"email\": \"OleCgkklnkbds@gmail.com\",\n" +
                                "  \"password\": \"srin12322\"\n" +
                                "\n" +
                                "}\n");
        Response generateTokenResponse=generateTokenRequest.when().post(ApiConstants.GENERATE_TOKEN_URI);
        generateTokenResponse.prettyPrint();

        token="Bearer "+generateTokenResponse.jsonPath().getString("token");






    }
}
