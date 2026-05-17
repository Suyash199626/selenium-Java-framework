package com.commonUtils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver =
            new ThreadLocal<>();

    public static void initDriver() {

        String browser = ConfigReader.get("browser");

        switch (browser.toLowerCase()) {

            case "chrome":

                WDMConfig.setupChrome();

                ChromeOptions options =
                        new ChromeOptions();

                // Headless
                if (ConfigReader.getBoolean("headless")) {

                    options.addArguments("--headless=new");
                }

                // Browser configs
                options.addArguments("--start-maximized");

                options.addArguments("--disable-notifications");

                options.addArguments("--disable-popup-blocking");

                options.addArguments("--incognito");

                // Disable password popup
                Map<String, Object> prefs =
                        new HashMap<>();

                prefs.put(
                        "credentials_enable_service",
                        false
                );

                prefs.put(
                        "profile.password_manager_enabled",
                        false
                );

                options.setExperimentalOption(
                        "prefs",
                        prefs
                );

                driver.set(
                        new ChromeDriver(options)
                );

                break;

            default:

                throw new RuntimeException(
                        "Browser not supported"
                );
        }
    }

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void unload() {

        driver.remove();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();

            driver.remove();
        }
    }
}