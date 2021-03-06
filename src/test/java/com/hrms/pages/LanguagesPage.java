package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LanguagesPage extends CommonMethods {
    @FindBy (id = "btnAdd")
    public WebElement addButton;

    @FindBy(id ="language_name")
    public WebElement addLanguageNameField;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    public void clickOnSaveBtn(){
        jsClick(saveButton);

    }
    public void clickOnAddButton(){
        jsClick(addButton);
    }



    public LanguagesPage(){
        PageFactory.initElements(driver,this);
    }

}
