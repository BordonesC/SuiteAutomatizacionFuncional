package org.portfolio.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.portfolio.drivers.DriverContext;
import org.portfolio.util.PdfReport;
import org.portfolio.util.Utils;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected WebDriver driver;
    protected Utils utils;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);


    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser, Method method){
        logger.info("Iniciando el test: " + method.getName());
        driver = DriverContext.getDriver(browser);
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
        PdfReport.setDriver(driver);

        utils = new Utils(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        String methodName = result.getMethod().getMethodName();
        String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        String fileName = "reporte_" + methodName + "_" + timestamp + ".pdf";

        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                logger.info("Test '" + methodName + "' finalizado correctamente.");
                break;
            case ITestResult.FAILURE:
                logger.error("Test '" + methodName + "' falló.", result.getThrowable());
                break;
            case ITestResult.SKIP:
                logger.warn("Test '" + methodName + "' fue omitido.");
                break;
        }

        PdfReport.generarPDF(fileName);
        PdfReport.clearReport();
        DriverContext.quitDriver();
    }
}
