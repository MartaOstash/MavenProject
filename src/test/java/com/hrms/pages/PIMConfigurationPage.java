package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PIMConfigurationPage extends CommonMethods {
    @FindBy(id = "menu_pim_Configuration")
    public WebElement configurationDropDown;

    @FindBy(id = "menu_pim_configurePim")
    public WebElement optionalFields;

    @FindBy(xpath = "//input[@value='Edit']")
    public WebElement editBtn;

    @FindBy(xpath = "//li[@class='checkbox']")
    public List<WebElement> checkboxes;

    @FindBy(id="btnSave")
    public WebElement saveBtn;

    public void clickOnConfigDD(){
        jsClick(configurationDropDown);
    }

    public void clickOnOptionalFields(){
        jsClick(optionalFields);
    }
    public void clickOnEditBtn(){
        jsClick(editBtn);
    }


    public PIMConfigurationPage(){
        PageFactory.initElements(driver,this);
    }
}
