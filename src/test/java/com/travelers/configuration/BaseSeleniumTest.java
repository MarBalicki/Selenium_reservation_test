package com.travelers.configuration;

import com.travelers.helpers.DriverFactory;
import com.travelers.helpers.DriverType;
import com.travelers.helpers.NoSuchDriverException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseSeleniumTest {

    protected WebDriver driver;

    @BeforeTest
    public void setUp() throws NoSuchDriverException {
        driver = DriverFactory.getDriver(DriverType.IE);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
