package com.travelers.pages;

import com.travelers.helpers.SeleniumHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//*[@id='s2id_autogen8']/a/span[1]")
    private WebElement searchSpan;

    @FindBy(xpath = "//*[@id='select2-drop']/div/input")
    private WebElement searchCityInput;

    @FindBy(name = "checkin")
    private WebElement checkinInput;

    @FindBy(name = "checkout")
    private WebElement checkoutInput;

    @FindBy(id = "travellersInput")
    private WebElement travellersInput;

    @FindBy(id = "adultInput")
    private WebElement adultInput;

    @FindBy(id = "childInput")
    private WebElement childInput;

    @FindBy(id = "adultPlusBtn")
    private WebElement adultPlusBtn;

    @FindBy(id = "adultMinusBtn")
    private WebElement adultMinusBtn;

    @FindBy(id = "childPlusBtn")
    private WebElement childPlusBtn;

    @FindBy(id = "childMinusBtn")
    private WebElement childMinusBtn;

    @FindBy(xpath = "//button[text()=' Search']")
    private WebElement searchButton;

    private final WebDriver driver;
    private final SeleniumHelper helper;
    private final Logger log = Logger.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
    }

    public HomePage setCityHotel(String cityName) {
        log.info("Setting city name");
        searchSpan.click();
        searchCityInput.sendKeys(cityName);
        helper.waitForElementToBeDisplayed(By.className("select2-result-label"));
        searchCityInput.sendKeys(Keys.ENTER);
        log.info("City name is set");
        return this;
    }

    public HomePage setRangeDate(String checkinDate, String checkoutDate) {
        log.info("Setting date range");
        checkinInput.sendKeys(checkinDate);
        checkoutInput.sendKeys(checkoutDate);
        log.info("Date range is set");
        return this;
    }

    public void addTravelers(int adult, int child) {
        log.info("Setting travellers");
        travellersInput.click();
        helper.waitForElementToBeDisplayed(adultInput);
        adultInput.clear();
        adultInput.sendKeys(String.valueOf(adult));
//        adultInput.sendKeys("0");
//        while (adult-- > 0) {
//            adultPlusBtn.click();
//        }
        childInput.clear();
        childInput.sendKeys(String.valueOf(child));
//        childInput.sendKeys("0");
//        while (child-- > 0) {
//            childPlusBtn.click();
//        }
        log.info("Travellers are set");
    }

    public ResultPage performSearch() {
        log.info("Search action performed");
        searchButton.click();
        return new ResultPage(driver);
    }


}

