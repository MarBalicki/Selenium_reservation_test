package com.travelers.tests;

import com.aventstack.extentreports.ExtentTest;
import com.travelers.helpers.ExcelHelper;
import com.travelers.helpers.TestListener;
import com.travelers.pages.HomePage;
import com.travelers.pages.ResultPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Listeners(TestListener.class)
public class SearchHotelTest extends BaseSeleniumTest {

    @Test(dataProvider = "getData")
    public void setCityHotel(String cityName, String fromDate, String toDate,
                             String hotelName1, String hotelName1Price,
                             String hotelName2, String hotelName2Price,
                             String hotelName3, String hotelName3Price) throws IOException {
        ExtentTest test = reports.createTest("Search Hotel test");
        driver.get("http://www.kurs-selenium.pl/demo/");
        HomePage homePage = new HomePage(driver);

        test.info("On PHP Travels Home Page", takeScreenShot());
        homePage.setCityHotel(cityName)
                .setRangeDate(fromDate, toDate)
                .addTravelers(2, 3);
        test.info("Before performing search for " + cityName, takeScreenShot());
        ResultPage resultPage = homePage.performSearch();

        test.info("Checkin hotel names", takeScreenShot());
        List<String> hotelNames = resultPage.getHotelNames();
//        ResultPage resultPage = new ResultPage(driver);
//        List<String> hotelNames = resultPage.getHotelNames();
        Assert.assertEquals(4, hotelNames.size());
        Assert.assertEquals("Jumeirah Beach Hotel", hotelName1);
        Assert.assertEquals("Oasis Beach Tower", hotelName2);
        Assert.assertEquals("Rose Rayhaan Rotana", hotelName3);
//        List<String> hotelPrices = resultPage.getHotelPrices();
        test.info("Checkin hotel prices", takeScreenShot());
        List<String> hotelPrices = resultPage.getHotelPrices();
        Assert.assertEquals(4, hotelPrices.size());
        Assert.assertEquals("$22", hotelName1Price);
        Assert.assertEquals("$50", hotelName2Price);
        Assert.assertEquals("$80", hotelName3Price);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        return ExcelHelper.readExcelFile(new File("src/test/resources/files/Dane.xls"));
//        return new Object[][] {
//                {"Dubai", "13.12.2020", "20.12.2020", 2, 3},
//                {"Wroclaw", "13.12.2020", "20.12.2020", 2, 3},
////                {"Wroclaw", "13.12.2020", "24.12.2020", 2, 4},
////                {"Londek", "19.12.2020", "20.12.2020", 1, 2}
//        };
    }


}
