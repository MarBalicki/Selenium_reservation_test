package com.travelers.helpers;

import com.travelers.exceptions.NoSuchDriverException;
import com.travelers.utils.DriverFactory;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener implements ITestListener {

    private final Logger log = Logger.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.debug("On test start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.debug("On test success");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        try {
            log.debug("On test failure");
            SeleniumHelper.takeScreenShot(DriverFactory.getDriver());
        } catch (NoSuchDriverException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.debug("On test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.debug("On test failed but within success percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.debug("On test start");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.debug("On test finish");
    }
}
