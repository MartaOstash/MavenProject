package com.hrms.MYUDEMYTESTING;

import com.hrms.FilesForMYAPI.ReusableMethods;
import com.hrms.FilesForMYAPI.payload;
import org.testng.annotations.DataProvider;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class DynamicJson {
    public DynamicJson() throws IOException {
    }

    @Test()
    public void addBook(String isbn, String aisle) {

        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().log().all()
                .header("Content-Type", "application/json")
                .body(payload.addBook(isbn, aisle))
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = ReusableMethods.rawToJson(response);
        String id = js.get("ID");
        System.out.println(id);

        //deleteBook scenario to fresh it every time
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {
        //array collection of elements
        //multidimensional array -collection of arrays
        return new Object[][]{{"gjdjd", "3234"}, {"jhvjwdqg", "4678"}, {"hdbakjsb", "456789"}};


    }


    //how to connect json file with automating
    //we have to convert content of teh file to String-->
    // covert into BYTE-->convert into String

//    RestAssured.baseURI="http://216.10.245.166";
//    String response = given().log().all()
//            .header("Content-Type","application/json")
//            .body(new String(Files.readAllBytes(Paths.get("hgdjhajhchj/frdgr/drgdb/grdrdvf//vrvrf"))))
//            .when().post("/Library/Addbook.php")
//            .then().assertThat().statusCode(200)
//            .extract().response().asString();
//    JsonPath js = ReusableMethods.rawToJson(response);
//    String id =js.get("ID");
//        System.out.println(id);
//
//}
}