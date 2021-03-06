package com.hrms.pages;

import com.hrms.utils.CommonMethods;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class QualificationsPage extends CommonMethods {
    @FindBy(id = "addLanguage")
    public WebElement addLanguagesBtn;

    public void clickOnAddLanguagesBtn() {
        jsClick(addLanguagesBtn);
    }

    public QualificationsPage() {
        PageFactory.initElements(driver, this);
    }
}

