package com.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.reports.ExtentManager;
import com.reporting.ExtentTestManager;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {

        ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
                ExtentManager
                .getInstance()
                .createTest(
                        result.getMethod().getMethodName()
                );

        ExtentTestManager.setTest(test);
    }

    @Override
    public void onFinish(ITestContext context) {

        ExtentManager.getInstance().flush();

        ExtentTestManager.unload();
    }
}