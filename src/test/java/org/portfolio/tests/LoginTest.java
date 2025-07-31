package org.portfolio.tests;

import org.openqa.selenium.By;
import org.testng.annotations.*;

public class LoginTest extends BaseTest{

    @Test
    //test para login con credenciales inválidas
    public void loginInvalidCredentials(){
        driver.findElement(By.linkText("Signup / Login")).click();
        driver.findElement(By.name("email")).sendKeys("user@novalido.com");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        assert driver.getPageSource().contains("Your email or password is incorrect!");
    }
}
