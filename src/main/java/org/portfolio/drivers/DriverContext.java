package org.portfolio.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverContext {
    private static WebDriver driver;

    public static WebDriver getDriver(String browser){
        if (driver == null){
            switch(browser.toLowerCase()){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver()
                            .driverVersion("0.34.0")
                            .operatingSystem(OperatingSystem.WIN)
                            .setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Navegador no soportado "+browser);
            }
        }
        return driver;
    }

    public static void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
