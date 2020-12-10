package com.travelers.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseSeleniumTest {

    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        String driverPath = "C:\\Java\\Selenium_reservation_test" +
                "\\src\\main\\resources\\executables.drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
