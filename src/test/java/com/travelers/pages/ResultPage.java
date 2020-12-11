package com.travelers.pages;

import com.travelers.helpers.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ResultPage {

    @FindBy(xpath = "//table[@class='bgwhite table table-striped']")
    private WebElement resultsTable;

    private final SeleniumHelper helper;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
    }

    public List<String> getHotelNames() {
        helper.waitForElementToBeDisplayed(resultsTable);
        List<WebElement> hotelNamesWebElements = resultsTable.findElements(By.xpath(".//h4//b"));
        return hotelNamesWebElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getHotelPrices() {
        helper.waitForElementToBeDisplayed(resultsTable);
        List<WebElement> hotelPricesWebElements = resultsTable.findElements(By.xpath("//div[@class='fs26 text-center']//b"));
        return hotelPricesWebElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
