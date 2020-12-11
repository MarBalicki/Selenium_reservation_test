package com.travelers.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;

public class SeleniumHelper {

    private final WebDriver driver;

    public SeleniumHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToBeDisplayed(By locator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeDisplayed(WebElement element) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver);
            wait.withTimeout(Duration.ofSeconds(15))
                    .pollingEvery(Duration.ofMillis(1000));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (NoSuchElementException ex) {
            System.out.println("Brak wyników");
        }
    }

    public static void takeScreenShot(WebDriver driver) {
        TakesScreenshot screenshoter = (TakesScreenshot) driver;
        File screenShot = screenshoter.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenShot.toPath(),
                    Paths.get("src/main/resources/screenshots/test"
                            + LocalDateTime.now().getNano() + ".png"));
        } catch (IOException ex) {
            System.out.println("File not found!");
        }
    }
}
