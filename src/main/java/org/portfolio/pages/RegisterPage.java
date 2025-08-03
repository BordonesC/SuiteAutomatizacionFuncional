package org.portfolio.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.portfolio.util.Utils;
import org.portfolio.models.RegisterData;


public class RegisterPage extends Utils {
    private Utils utils;


    public RegisterPage(WebDriver driver){
        //inicializa driver desde utils
        super(driver);
        PageFactory.initElements(driver,this);
        utils = new Utils(driver);
    }

    //locators register
    @FindBy(linkText = "Signup / Login")
    private WebElement linkSignupLogin;

    @FindBy(id = "password")
    private WebElement inputPasswordRegister;

    @FindBy(id = "days")
    private WebElement selectDaysRegister;

    @FindBy(id = "months")
    private WebElement selectMonthsRegister;

    @FindBy(id = "years")
    private WebElement selectYearsRegister;

    @FindBy(id = "first_name")
    private WebElement inputFirstNameRegister;

    @FindBy(id = "last_name")
    private WebElement inputLastNameRegister;

    @FindBy(id = "address1")
    private WebElement inputAddressRegister;

    @FindBy(id = "country")
    private WebElement selectCountryRegister;

    @FindBy(id = "state")
    private WebElement inputStateRegister;

    @FindBy(id = "city")
    private WebElement inputCityRegister;

    @FindBy(id = "zipcode")
    private WebElement inputZipCodeRegister;

    @FindBy(id = "mobile_number")
    private WebElement inputMobileNumberRegister;

    @FindBy(xpath = "//button[text()='Create Account']")
    private WebElement buttonSignUp;


    public void registerNewUser(RegisterData registerData) {
        utils.waitUntilElementIsVisible(inputPasswordRegister,5);
        inputPasswordRegister.sendKeys(registerData.getPassword());
        Select selectDay = new Select(selectDaysRegister);
        selectDay.selectByValue(registerData.getDay());
        Select selectMonth = new Select(selectMonthsRegister);
        selectMonth.selectByValue(registerData.getMonth());
        Select selectYear = new Select(selectYearsRegister);
        selectYear.selectByValue(registerData.getYear());
        utils.sendKeysWithScroll(inputFirstNameRegister,registerData.getFirstName());
        inputLastNameRegister.sendKeys(registerData.getLastName());
        inputAddressRegister.sendKeys(registerData.getAddress1());
        Select selectCountry = new Select(selectCountryRegister);
        selectCountry.selectByValue(registerData.getCountry());
        utils.sendKeysWithScroll(inputStateRegister,registerData.getState());
        inputCityRegister.sendKeys(registerData.getCity());
        inputZipCodeRegister.sendKeys(registerData.getZipcode());
        inputMobileNumberRegister.sendKeys(registerData.getMobileNumber());
        buttonSignUp.click();
    }

}
