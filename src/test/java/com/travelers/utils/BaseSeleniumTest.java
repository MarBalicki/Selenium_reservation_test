package com.travelers.utils;

import com.travelers.utils.DriverFactory;
import com.travelers.utils.DriverType;
import com.travelers.helpers.NoSuchDriverException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseSeleniumTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() throws NoSuchDriverException {
        driver = DriverFactory.getDriver(DriverType.CHROME);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        DriverFactory.resetDriver();
    }

}
