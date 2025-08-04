package org.portfolio.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.portfolio.util.Utils;

public class HomePage extends Utils {
    private Utils utils;

    public HomePage(WebDriver driver){
        //inicializa driver desde utils
        super(driver);
        PageFactory.initElements(driver,this);
        utils = new Utils(driver);
    }

    //locators home
    @FindBy(linkText = "Logged in as")
    private WebElement linkLoggedIn;

    public void userLoggedIn(){
        utils.waitUntilElementIsVisible(linkLoggedIn,5);
    }

}
