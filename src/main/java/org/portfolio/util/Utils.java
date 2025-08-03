package org.portfolio.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

public class Utils {
    protected WebDriver driver;

    public Utils(WebDriver driver){
        this.driver = driver;
    }

    //pausa explícita
    public static void sleep(int seconds) {
        try {
            System.out.println("Ejecución pausada por " + seconds + " segundos...");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Hubo una interrupción durante la pausa.");
        }
    }

    //espera explícita hasta que el elemento sea visible
    public WebElement waitUntilElementIsVisible(WebElement element, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //espera que cargue la página
    public void waitPageLoad(int seconds){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(seconds));
    }

    //enmarcar objeto con el que se está interactuando
    public void objectOutline(WebElement element){
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            for (int i = 0; i < 3; i++) {
                js.executeScript("arguments[0].setAttribute('style',arguments[1]);",new Object[]{element,"border:5px solid LimeGreen;"});
                Thread.sleep(200L);
            }
        }catch (Exception e){
            Assert.assertFalse(false,"Se ha producido una excepción "+e.getMessage());
        }
    }

    //scroll al elemento
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(element));
    }

    //send keys con scroll al elemento
    public void sendKeysWithScroll(WebElement element, String text) {
        scrollToElement(element);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }



}
