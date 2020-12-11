package com.travelers.pages;

import com.travelers.helpers.SeleniumHelper;
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

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.helper = new SeleniumHelper(driver);
    }

    public HomePage setCityHotel(String cityName) {
        searchSpan.click();
        searchCityInput.sendKeys(cityName);
        helper.waitForElementToBeDisplayed(By.className("select2-result-label"));
        searchCityInput.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage setRangeDate(String checkinDate, String checkoutDate) {
        checkinInput.sendKeys(checkinDate);
        checkoutInput.sendKeys(checkoutDate);
        return this;
    }

    public HomePage addTravelers(int adult, int child) {
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
        return this;
    }

    public ResultPage performSearch() {
        searchButton.click();
        return new ResultPage(driver);
    }


}

