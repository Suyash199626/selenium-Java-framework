# Selenium Java Automation Project

## Overview
This repository contains a Selenium WebDriver automation framework built with Java and Maven. The project uses TestNG for test execution, ExtentReports for reporting, and WebDriverManager for automatic browser driver management.

## Key Features
- Selenium WebDriver automation
- TestNG test framework
- ExtentReports test reporting
- WebDriverManager for driver setup
- Log4j for logging
- Apache POI support for Excel handling
- JSON processing with Jackson

## Project Structure
- `pom.xml` - Maven project configuration and dependencies
- `Test.xml` - TestNG suite configuration for running the `LoginTest`
- `src/main/java` - Main application or utility classes
- `src/test/java` - Test classes and page objects
- `src/test/resources` - Test resources and configuration files
- `drivers/` - Browser driver configuration and resolution settings
- `Reports/` - Generated HTML automation reports
- `Screenshots/` - Captured test screenshots
- `test-output/` - TestNG output and report artifacts

## Prerequisites
- Java JDK installed (Java 17+ recommended)
- Maven installed and available on `PATH`
- Internet access for WebDriverManager to download drivers
- Chrome browser installed for Chrome-based tests (or modify the project to use another browser)

## Dependencies
The project depends on:
- `selenium-java` 4.21.0
- `webdrivermanager` 5.8.0
- `testng` 7.10.2
- `extentreports` 5.1.1
- `log4j-api` / `log4j-core` 2.23.1
- `poi` / `poi-ooxml` 5.2.5
- `jackson-databind` 2.17.1
- `slf4j-simple` 2.0.13
- `commons-io` 2.15.1

## Running Tests
From the project root, run:

```powershell
mvn clean test -DsuiteXmlFile=Test.xml
```

Or run TestNG directly via the suite file if your IDE supports it.

## Report Output
- HTML reports are generated under `Reports/`
- TestNG output files are written to `test-output/`
- Screenshots are saved in `Screenshots/`

## Notes
- Update `Test.xml` to add or modify test classes and suites.
- Ensure browser drivers are compatible with your installed browser version.
- Use Maven to resolve dependencies and execute the test suite.
