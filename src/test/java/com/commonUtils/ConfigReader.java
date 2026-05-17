package com.commonUtils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop = new Properties();

    static {
        try (InputStream is = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("ConfigFiles/config.propertiese")) {

            if (is == null) {
                throw new RuntimeException("config.properties not found");
            }

            prop.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(prop.getProperty(key));
    }

    public static int getInt(String key) {
        return Integer.parseInt(prop.getProperty(key));
    }
}