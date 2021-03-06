package com.hrms.MYUDEMYTESTING;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.hrms.FilesForMYAPI.ReusableMethods;
import com.hrms.FilesForMYAPI.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

public class Basics {
    public static void main(String[] args) {
        //validate if Add place API is working as expected

        //given will take all the input details what you need to submit
        //when you will submit  the api --resource , http method(CRUD)
        //then - validate the response

        RestAssured.baseURI="https://rahulshettyacademy.com";
       String response = given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")

                //saving ugly json in payload class
                .body(payload.AddPlace())
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope",equalTo("APP"))
                .header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
                 System.out.println(response);

                 //we want to hold place id to do that we need:
        //for parsing Json all the values are now stored in js variable
        JsonPath js=new JsonPath(response);
        //extracting place_id
        //path may be different depending on the existence parents
        String placeId=js.getString("place_id");
        System.out.println(placeId);




        //updating place address
        String newAddress = "1012 ave N USA";
        given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));


        //get PLace
        String getPlaceResponse=given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().response().asString();

       JsonPath json = ReusableMethods.rawToJson(getPlaceResponse);
        String actualAddress=json.getString("address");
        System.out.println(actualAddress);

        //Cucumber Junit, Testng
        Assert.assertEquals(actualAddress,newAddress);
















    }
}
