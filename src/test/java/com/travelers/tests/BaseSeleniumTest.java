package com.travelers.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.travelers.helpers.SeleniumHelper;
import com.travelers.utils.DriverFactory;
import com.travelers.utils.DriverType;
import com.travelers.exceptions.NoSuchDriverException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public abstract class BaseSeleniumTest {

    protected WebDriver driver;
    protected ExtentHtmlReporter reporter;
    protected ExtentReports reports;

    @BeforeTest
    public void setUpReporter() {
        reporter = new ExtentHtmlReporter("src//test//resources//reports//index.html");
        reports = new ExtentReports();
        reports.attachReporter(reporter);
    }

    @BeforeMethod
    public void setUp() throws NoSuchDriverException {
        driver = DriverFactory.getDriver(DriverType.CHROME);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        DriverFactory.resetDriver();
    }

    @AfterTest
    public void tearDownReporter() {
        reporter.flush();
        reports.flush();
    }

    MediaEntityModelProvider takeScreenShot() throws IOException {
        return MediaEntityBuilder.createScreenCaptureFromPath(
                SeleniumHelper.takeScreenShot(driver)).build();
    }

}
