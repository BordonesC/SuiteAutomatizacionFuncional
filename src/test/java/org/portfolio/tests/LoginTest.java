package org.portfolio.tests;

import org.portfolio.util.PdfReport;
import org.portfolio.util.Utils;
import org.portfolio.util.DataProviders;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.portfolio.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class LoginTest extends BaseTest{
    private Utils utils;
    private LoginPage loginPage;

    @BeforeMethod
    @Parameters("browser")
    public void setUpTest(String browser, Method method) {
        //llama al setUp desde Utils e inicializa utils con el driver
        super.setUp(browser, method);
        utils = new Utils(driver);
        loginPage = new LoginPage(driver);
    }

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class)
    //test para validar login con DataProvider y expectedResult
    public void login(String email, String password, String expectedResult, String testType){
        loginPage.login(email,password);

        if (testType.equalsIgnoreCase("validLogin")){
            WebElement loggedInText = driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]"));
            utils.waitUntilElementIsVisible(loggedInText,5);
            Assert.assertTrue(loggedInText.getText().contains(expectedResult));
            PdfReport.addStep("Valid Login", PdfReport.EstadoPrueba.PASSED, true);

        } else if (testType.equalsIgnoreCase("invalidLogin")) {
            WebElement errorMessage = driver.findElement(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]"));
            utils.waitUntilElementIsVisible(errorMessage,5);
            PdfReport.addStep("No valid Login", PdfReport.EstadoPrueba.FAILED, true);
            Assert.assertEquals(errorMessage.getText(),expectedResult);
        }
    }

}
