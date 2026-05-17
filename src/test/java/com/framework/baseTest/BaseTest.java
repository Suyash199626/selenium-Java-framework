package com.framework.baseTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.commonUtils.DriverFactory;
import com.commonUtils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class BaseTest {

	protected WebDriver driver;
	protected JsonNode jsonValues;
	 
    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        jsonValues= JsonUtils.readJson("TestData/TestData.json");  
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (DriverFactory.getDriver() != null) {

            DriverFactory.getDriver().quit();

            DriverFactory.unload();
        }
    }
}