package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeeListPage extends CommonMethods {

    @FindBy(xpath = "//input[@id='empsearch_id']")
    public WebElement employeeIDsearchTextField;

    @FindBy(id="middleName")
    public WebElement middleName;

    @FindBy(id = "empsearch_employee_name_empName")
    public WebElement employeeNameSearchTextField;

    @FindBy(id = "searchBtn")
    public WebElement searchBtn;

    @FindBy(xpath = "//table[@id = 'resultTable']/tbody/tr[1]/td[3]")
    public WebElement firstNameCell;

    @FindBy(xpath = "//table[@id ='resultTable']/tbody/tr[1]/td[4]")
    public WebElement lastNameCell;

    @FindBy(xpath = "//table[@id ='resultTable']/tbody/tr[1]/td[2]")
    public WebElement idCell;


    @FindBy(id ="empsearch_job_title")
    public WebElement jobTitleDropDown;

    public void clickSearchBtn() {
        jsClick(searchBtn);
    }


    public String getIDText() {
        return idCell.getText();

    }


    public String getFirstNameText() {
        return firstNameCell.getText();
    }

    public String getMiddleName(){
        return middleName.getText();
    }


    public String getLastNameText() {
        return lastNameCell.getText();

    }


    public EmployeeListPage() {
        PageFactory.initElements(driver, this);
    }
}