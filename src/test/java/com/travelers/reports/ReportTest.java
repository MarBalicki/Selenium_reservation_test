package com.travelers.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.IOException;

public class ReportTest {

    public static void main(String[] args) throws IOException {
        ExtentHtmlReporter reporter = new ExtentHtmlReporter("index.html");
        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);

        ExtentTest test = reports.createTest("Our first test");
        test.log(Status.INFO, "First step");
        test.log(Status.ERROR, "Second step");
        test.log(Status.DEBUG, "Third step");
        test.pass("Test passed");
        String screenShotPath = "C:\\Java\\Selenium_reservation_test\\src\\main" +
                "\\resources\\screenshots\\test67746400.png";
        test.pass("Description", MediaEntityBuilder.createScreenCaptureFromPath(
                screenShotPath).build());

        reports.flush();
        reporter.flush();
    }
}
