package com.travelers.utils;

import com.travelers.exceptions.NoSuchDriverException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;

import java.io.File;

public class DriverFactory {

    private static WebDriver driverInstance;

    private DriverFactory() {
    }

    public static WebDriver getDriver() throws NoSuchDriverException {
        if (driverInstance == null) {
            getDriver(DriverType.CHROME);
        }
        return driverInstance;
    }

    public static WebDriver getDriver(DriverType driverType) throws NoSuchDriverException {
        if (driverInstance == null) {
            getSpecificDriver(driverType);
            driverInstance.manage().window().maximize();
        }
        return driverInstance;
    }

    private static void getSpecificDriver(DriverType driverType) throws NoSuchDriverException {
        switch (driverType) {
            case IE:
                File IEDriverExe = new File("C:\\Java\\Selenium_reservation_test" +
                        "\\src\\test\\resources\\executables.drivers\\IEDriverServer.exe");
                InternetExplorerDriverService driverService = new InternetExplorerDriverService.Builder()
                        .usingDriverExecutable(IEDriverExe)
                        .usingAnyFreePort()
                        .build();
                driverInstance = new InternetExplorerDriver(driverService);
                break;
            case CHROME:
                File chromeDriverExe = new File("C:\\Java\\Selenium_reservation_test" +
                        "\\src\\test\\resources\\executables.drivers\\chromedriver.exe");
                ChromeDriverService chromeDriverService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(chromeDriverExe)
                        .usingAnyFreePort()
                        .build();
                driverInstance = new ChromeDriver(chromeDriverService);
                break;
            case FIREFOX:
                File firefoxDriverExe = new File("C:\\Java\\Selenium_reservation_test" +
                        "\\src\\test\\resources\\executables.drivers\\chromedriver.exe");
                GeckoDriverService geckoDriverService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(firefoxDriverExe)
                        .usingAnyFreePort()
                        .build();
                driverInstance = new FirefoxDriver(geckoDriverService);
                break;
            default:
//                System.out.println("Brak drivera danego typu!");
                throw new NoSuchDriverException();
        }
    }

    public static void resetDriver() {
        driverInstance = null;
    }
}
