package com.travelers.tests;

import com.travelers.configuration.BaseSeleniumTest;
import org.testng.annotations.Test;

public class FirstTest extends BaseSeleniumTest {

    @Test
    public void test() {
        driver.get("https://www.google.com");
    }

}
