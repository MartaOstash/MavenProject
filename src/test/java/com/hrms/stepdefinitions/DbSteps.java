package com.hrms.stepdefinitions;

import com.hrms.utils.DBUtils;
import com.hrms.utils.GlobalVariables;
import io.cucumber.java.en.Then;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DbSteps {
 //used for steps on database
    @Then("collect employee data from hrms database")
    //connection with the data
    public void collect_employee_data_from_hrms_database() throws SQLException {
        String query = "select emp_firstname,emp_middle_name,emp_lastname "
                + "   from hs_hr_employees"
                + "   where employee_id=" + GlobalVariables.emp_Id;
        GlobalVariables.dbList = DBUtils.getDBDataIntoMap(query);


    }

}
