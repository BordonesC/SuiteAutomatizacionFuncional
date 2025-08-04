package org.portfolio.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.portfolio.models.RegisterData;
import org.portfolio.util.PdfReport;
import org.portfolio.util.Utils;

public class LoginPage extends Utils {

    public LoginPage(WebDriver driver){
        //inicializa driver desde utils
        super(driver);
        PageFactory.initElements(driver,this);
    }

    //locators login
    @FindBy(linkText = "Signup / Login")
    private WebElement linkSignupLogin;

    @FindBy(xpath = "//div[@class='login-form']//input[@name='email']")
    private WebElement inputEmailLogin;

    @FindBy(xpath = "//div[@class='login-form']//input[@name='password']")
    private WebElement inputPasswordLogin;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement buttonLogin;

    //locators signup
    @FindBy(xpath = "//div[@class='signup-form']//input[@name='email']")
    private WebElement inputEmailSignUp;

    @FindBy(xpath = "//div[@class='signup-form']//input[@name='name']")
    private WebElement inputNameSignUp;

    @FindBy(xpath = "//button[text()='Signup']")
    private WebElement buttonSignUp;


    public void login(String email, String password){
        try{
            linkSignupLogin.click();
            inputEmailLogin.sendKeys(email);
            inputPasswordLogin.sendKeys(password);
            buttonLogin.click();
            PdfReport.addStep("Ingresa data login ", PdfReport.EstadoPrueba.PASSED, true);
        } catch (Exception e) {
            PdfReport.addStep("Error durante la ejecución: " + e.getMessage(), PdfReport.EstadoPrueba.FAILED, true);
            throw new RuntimeException(e);
        }

    }

    public void signup(RegisterData registerData){
        try{
            linkSignupLogin.click();
            inputNameSignUp.sendKeys(registerData.getName());
            inputEmailSignUp.sendKeys(registerData.getEmail());
            buttonSignUp.click();
            PdfReport.addStep("Ingresa data signup ", PdfReport.EstadoPrueba.PASSED, true);
        } catch (Exception e) {
            PdfReport.addStep("Error durante la ejecución: " + e.getMessage(), PdfReport.EstadoPrueba.FAILED, true);
            throw new RuntimeException(e);
        }
    }
}
