package com.hrms.stepdefinitions;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.DBUtils;
import com.hrms.utils.GlobalVariables;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class EmployeeListStepDefinition extends CommonMethods {


    @Then("user shall  be able to navigate to employee List page")
    public void user_shall_be_able_to_navigate_to_employee_List_page() {
        jsClick(dashboardPage.PIMButton);
        jsClick(dashboardPage.employeeList);
    }

    @When("enter employee id {string}")
    public void enter_employee_id(String ID) {
        sendText(employeeListPage.employeeIDsearchTextField, ID);
    }

    @When("user should be able click on search BTN")
    public void user_should_be_able_click_on_search_BTN() {
        employeeListPage.clickSearchBtn();
    }

    @Then("Verify that id {string} appears successfully")
    public void verify_that_id_appears_successfully(String id) {
        Assert.assertEquals("IDs do not match", id, employeeListPage.getIDText());

    }

    @When("enter employee first name {string} and  last name {string}")
    public void enter_employee_first_name_and_last_name(String firstName, String lastName) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='ac_input inputFormatHint']")));
        employeeListPage.employeeNameSearchTextField.click();
        String fullName = firstName + " " + lastName;
        employeeListPage.employeeNameSearchTextField.sendKeys(fullName);
    }


    @Then("verify employee full name {string} is displayed")
    public void verify_employee_full_name_is_displayed(String expectedFullName) {
        String actualFirstName = employeeListPage.getFirstNameText();
        String actualMiddleName = employeeListPage.getMiddleName();
        String actualLastName = employeeListPage.lastNameCell.getText();
        String actualFullName = actualFirstName + " " + actualMiddleName + " " + actualLastName;
        Assert.assertEquals("Verifying by Full Name", expectedFullName, actualFullName);
    }


    private List<String> uiJobTitlesList;
    private List<String> dbJobTitlesList;

    @Then("collect job titles from UI Employee List Job Title DropDown")
    public void collect_job_titles_from_UI_Employee_List_Job_Title_DropDown() {
        uiJobTitlesList = getAllDataFromDropDownIntoListOfString(employeeListPage.jobTitleDropDown);
    }


    @Then("collect job titles from hrms DB")
    public void collect_job_titles_from_hrms_DB() {

        String query = "select job_title from ohrm_job_title order by job_title asc;";
        GlobalVariables.dblist = DBUtils.getDBDataIntoList(query);

    }


    @Then("verify job titles data from UI Employee List Job Title DropDown and DB is matched")
    public void verify_job_titles_data_from_UI_Employee_List_Job_Title_DropDown_and_DB_is_matched() {

        dbJobTitlesList=GlobalVariables.dblist;
        Assert.assertEquals("Job titles data from UI Employee List Job Title DropDown and DB is NOT matched",
                dbJobTitlesList, uiJobTitlesList);


    }
}