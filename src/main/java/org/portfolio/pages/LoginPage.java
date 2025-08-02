package org.portfolio.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.portfolio.util.Utils;

public class LoginPage extends Utils {

    public LoginPage(WebDriver driver){
        //inicializa driver desde basePage
        super(driver);
        PageFactory.initElements(driver,this);
    }

    //locators
    @FindBy(linkText = "Signup / Login")
    private WebElement linkSignupLogin;

    @FindBy(name = "email")
    private WebElement inputEmail;

    @FindBy(name = "password")
    private WebElement inputPassword;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement buttonLogin;


    public void login(String email, String password){
        linkSignupLogin.click();
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        buttonLogin.click();
    }
}
