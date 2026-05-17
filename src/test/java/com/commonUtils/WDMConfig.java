package com.commonUtils;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.commonUtils.ConfigReader;

public class WDMConfig {

    public static void setupChrome() {

        WebDriverManager wdm = WebDriverManager.chromedriver();

        // Apply config from properties
        if (ConfigReader.getBoolean("wdm.cache")) {
            wdm.cachePath("drivers");
        }

        if (!ConfigReader.get("wdm.driverVersion").isEmpty()) {
            wdm.driverVersion(ConfigReader.get("wdm.driverVersion"));
        }

        if (!ConfigReader.get("wdm.proxy").isEmpty()) {
            wdm.proxy(ConfigReader.get("wdm.proxy"));
        }

        if (ConfigReader.getBoolean("wdm.avoidBrowserDetection")) {
            wdm.avoidBrowserDetection();
        }

        wdm.setup();
    }
}