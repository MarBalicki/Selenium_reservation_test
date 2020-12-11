package com.travelers.tests;

import com.travelers.helpers.TestListener;
import com.travelers.utils.BaseSeleniumTest;
import com.travelers.pages.HomePage;
import com.travelers.pages.ResultPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

@Listeners(TestListener.class)
public class SearchHotelTest extends BaseSeleniumTest {

    @Test(dataProvider = "getData")
    public void setCityHotel(String cityName, String fromDate, String toDate, int adult, int child) {
        driver.get("http://www.kurs-selenium.pl/demo/");
        HomePage homePage = new HomePage(driver);
        ResultPage resultPage = homePage
                .setCityHotel(cityName)
                .setRangeDate(fromDate, toDate)
                .addTravelers(adult, child)
                .performSearch();

        List<String> hotelNames = resultPage.getHotelNames();
//        ResultPage resultPage = new ResultPage(driver);
//        List<String> hotelNames = resultPage.getHotelNames();
        Assert.assertEquals(4, hotelNames.size());
        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0));
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1));
//        List<String> hotelPrices = resultPage.getHotelPrices();
        List<String> hotelPrices = resultPage.getHotelPrices();
        Assert.assertEquals(4, hotelPrices.size());
        Assert.assertEquals("$22", hotelPrices.get(0));
        Assert.assertEquals("$50", hotelPrices.get(1));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        return ExcelHelper.readExcelFile(new File("src/main/resources/files/Dane.xlsx"));
        return new Object[][] {
                {"Dubai", "13.12.2020", "20.12.2020", 2, 3},
                {"Wroclaw", "13.12.2020", "20.12.2020", 2, 3},
//                {"Wroclaw", "13.12.2020", "24.12.2020", 2, 4},
//                {"Londek", "19.12.2020", "20.12.2020", 1, 2}
        };
    }
}
