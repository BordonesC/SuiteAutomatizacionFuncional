package org.portfolio.tests;

import org.openqa.selenium.WebDriver;
import org.portfolio.utils.DriverContext;
import org.testng.annotations.*;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser){
        driver = DriverContext.getDriver(browser);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
    }

    @AfterMethod
    public void tearDown(){
        DriverContext.quitDriver();
    }
}
