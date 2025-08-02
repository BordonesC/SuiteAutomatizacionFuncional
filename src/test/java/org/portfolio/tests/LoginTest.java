package org.portfolio.tests;

import org.openqa.selenium.*;
import org.portfolio.pages.LoginPage;
import org.portfolio.util.Utils;
import org.testng.annotations.*;

public class LoginTest extends BaseTest{
    private Utils utils;
    private LoginPage loginPage;

    @BeforeMethod
    @Parameters("browser")
    public void setUpTest(String browser) {
        //llama al setUp desde BaseTest e inicializa basePage con el driver
        super.setUp(browser);
        utils = new Utils(driver);
        loginPage = new LoginPage(driver);
    }


    @Test
    //test para login con credenciales inválidas
    public void loginInvalidCredentials(){
        loginPage.login("user@novalido.com","123456");
        WebElement errorMessage = utils.waitUntilVisible(By.xpath("//p[contains(text(),'Your email or password is incorrect!')]"),5);

        assert errorMessage.getText().contains("Your email or password is incorrect!");
    }
}
