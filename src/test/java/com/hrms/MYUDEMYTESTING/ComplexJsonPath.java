package com.hrms.MYUDEMYTESTING;

import com.hrms.FilesForMYAPI.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonPath {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(payload.CoursePrice());


        //print the total number of courses
        int count=js.getInt("courses.size()");
        System.out.println(count);

        //print purchase amount
        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //print the title of the 1st course
        String firstCourseTitle=js.getString("courses[0].title");
        System.out.println(firstCourseTitle);


        //print all course titles with the respective prices

        //using for loop it will iterate three times
        //it will iterate until it reaches the count which is the number of our courses
        for (int i = 0; i < count; i++) {
            //it will also print the title of all three courses
            //why ["+i+"] cause we're concatinating in between string
            String courseTitles=js.get("courses["+i+"].title");
            int coursePrices = js.get("courses["+i+"].price");
            System.out.println(courseTitles);

           // System.out.println(coursePrices);
            //or you can print it out like this as well, println ALWAYS expects String
            System.out.println(js.get("courses["+i+"].price").toString());

        }

        //print the number of copies sold by RPA course
        int numberOfRPACourses=js.get("courses[2].copies");
        System.out.println("number of copies sold by RPA course "+numberOfRPACourses);


        System.out.println("----------------------------------------------------------");

        //another way to print the number of copies sold by RPA course by using loop

        for (int i = 0; i < count; i++) {
            //grabbing the title RPA
            String courseTitles=js.get("courses["+i+"].title");

            //it will iterate until it matches the RPA
            if(courseTitles.equalsIgnoreCase("Appium")){

                //copies sold
                int copies=js.get("courses["+i+"].copies");
                System.out.println(copies);
                break;

            }



        }









    }
}
