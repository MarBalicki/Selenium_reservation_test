package com.travelers.helpers;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.time.LocalDateTime;

public class SeleniumHelper {

    private final WebDriver driver;
    private final Logger log = Logger.getLogger(SeleniumHelper.class);

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
            log.debug("Lack of results");
        }
    }

    public static String takeScreenShot(WebDriver driver) throws IOException {
        TakesScreenshot screenshoter = (TakesScreenshot) driver;
        File screenShot = screenshoter.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("src/test/resources/screenshots/test"
                    + LocalDateTime.now().getNano() + ".png");
            Files.copy(screenShot.toPath(), destinationFile.toPath());
        return destinationFile.getAbsolutePath();
    }
}
