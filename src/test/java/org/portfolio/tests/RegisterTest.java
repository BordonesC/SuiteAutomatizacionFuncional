package org.portfolio.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.portfolio.models.RegisterData;
import org.portfolio.pages.LoginPage;
import org.portfolio.pages.RegisterPage;
import org.portfolio.util.DataProviders;
import org.portfolio.util.PdfReport;
import org.portfolio.util.Utils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Map;

public class RegisterTest extends BaseTest{
    private Utils utils;
    private LoginPage loginPage;
    private RegisterPage registerPage;

    @BeforeMethod
    @Parameters("browser")
    public void setUpTest(String browser, Method method) {
        //llama al setUp desde Utils e inicializa utils con el driver
        super.setUp(browser, method);
        utils = new Utils(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);

    }

    @Test(dataProvider = "signupData", dataProviderClass = DataProviders.class)
    //test para validar signup con DataProvider y expectedResult
    public void signUp(Map<String, String> testData) {
        RegisterData registerData = new RegisterData(
                testData.get("name"),
                testData.get("email"),
                testData.get("password"),
                testData.get("day"),
                testData.get("month"),
                testData.get("year"),
                testData.get("firstName"),
                testData.get("lastName"),
                testData.get("address1"),
                testData.get("country"),
                testData.get("state"),
                testData.get("city"),
                testData.get("zipcode"),
                testData.get("mobileNumber")
        );

        String expectedResult = testData.get("expectedResult");
        String testType = testData.get("testType");

        try {
            loginPage.signup(registerData);

            if ("validSignUp".equalsIgnoreCase(testType)) {
                registerPage.registerNewUser(registerData);
                WebElement loggedInText = driver.findElement(By.xpath("//h2[@data-qa='account-created']/b[text()='Account Created!']"));
                utils.waitUntilElementIsVisible(loggedInText, 5);
                Assert.assertTrue(loggedInText.getText().contains(expectedResult));
                PdfReport.addStep("Valid SignUp", PdfReport.EstadoPrueba.PASSED, true);

            } else if ("invalidSignUp".equalsIgnoreCase(testType)) {
                WebElement errorMessage = driver.findElement(By.xpath("//p[contains(text(),'Email Address already exist!')]"));
                utils.waitUntilElementIsVisible(errorMessage, 5);
                PdfReport.addStep("Valid SignUp", PdfReport.EstadoPrueba.FAILED,true);
                Assert.assertEquals(errorMessage.getText(), expectedResult);
            }
        } catch (Exception e) {
            PdfReport.addStep("Error durante la ejecución: " + e.getMessage(), PdfReport.EstadoPrueba.FAILED, true);
            throw e;
        }

    }
}
