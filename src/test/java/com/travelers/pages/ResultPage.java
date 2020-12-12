package com.travelers.pages;

import com.travelers.helpers.SeleniumHelper;
import org.apache.log4j.Logger;
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
    private final Logger log = Logger.getLogger(ResultPage.class);

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
    }

    public List<String> getHotelNames() {
        log.info("Checking hotel names");
        helper.waitForElementToBeDisplayed(resultsTable);
        List<WebElement> hotelNamesWebElements = resultsTable.findElements(By.xpath(".//h4//b"));
        hotelNamesWebElements.forEach(element -> log.info(element.getText()));
        return hotelNamesWebElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getHotelPrices() {
        log.info("Checking hotel prices");
        helper.waitForElementToBeDisplayed(resultsTable);
        List<WebElement> hotelPricesWebElements = resultsTable.findElements(By.xpath("//div[@class='fs26 text-center']//b"));
        hotelPricesWebElements.forEach(element -> log.info(element.getText()));
        return hotelPricesWebElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
