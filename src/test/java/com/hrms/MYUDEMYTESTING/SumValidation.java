package com.hrms.MYUDEMYTESTING;

import com.hrms.FilesForMYAPI.payload;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;

public class SumValidation {
    @Test
    public void sumOfCourses() {
        int sum = 0;
        JsonPath js = new JsonPath(payload.CoursePrice());
        int count = js.getInt("courses.size()");
        for (int i = 0; i < count; i++) {
            int price = js.get("courses[" + i + "].price");
            int copies = js.get("courses[" + i + "].copies");
            int amount = price * copies;
            System.out.println(amount);

            //adding all the sums
            //ut first we need to assign sum to 0 at the very beginning
            sum = sum + amount;
        }
        System.out.println(sum);


        //verifying that sum of all courses = to purchase amount (it is not)

        //getting the purchase amount first
        int purchaseAmount = js.get("dashboard.purchaseAmount");
        //adding assertion
        Assert.assertEquals(purchaseAmount,sum);

    }
}