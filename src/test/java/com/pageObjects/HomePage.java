package com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver ldriver;

    public HomePage(WebDriver rdriver) {
        ldriver = rdriver;
        PageFactory.initElements(rdriver, this);
    }

    @FindBy(xpath = "//*[@class='count']")
    @CacheLookup
    WebElement countField;

    @FindBy(xpath = "//*[@class='clickButton']")
    @CacheLookup
    WebElement btnPlus;

    public void clickPlus() {
        btnPlus.click();
    }

    public String getCount() {
        return countField.getText();
    }


}
