package com.commonUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.baseTest.BaseTest;

public class commonUtils extends BaseTest 
{

	    private static final int TIMEOUT = 60;
	    
	 // =========================================
	    // URL Launch
	    // =========================================
	    
	    public static boolean navigateURL(WebDriver driver, String url, int timeoutSeconds) {
if (driver == null || url == null || url.trim().isEmpty()) {
	            System.err.println("Driver and URL must not be null or empty.");
	            return false;
	        }

	        try {
	            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeoutSeconds));
	            driver.get(url);
	            return true;
	        } catch (InvalidArgumentException e) {
	            System.err.println("Invalid URL: " + url + " - " + e.getMessage());
	        } catch (TimeoutException e) {
	            System.err.println("Page load timed out after " + timeoutSeconds + " seconds: " + url);
	        } catch (WebDriverException e) {
	            System.err.println("WebDriver error opening URL '" + url + "': " + e.getMessage());
	        } catch (Exception e) {
	            System.err.println("Unexpected error opening URL '" + url + "': " + e.getMessage());
	        }
	        return false;
	    }



	    // =========================================
	    // WAIT FOR VISIBILITY
	    // =========================================
	    public static WebElement waitForVisibility(WebElement element) {

	        try {

	            WebDriverWait wait = new WebDriverWait(
	                    DriverFactory.getDriver(),
	                    Duration.ofSeconds(TIMEOUT)
	            );

	            return wait.until(
	                    ExpectedConditions.visibilityOf(element)
	            );

	        } catch (TimeoutException e) {

	            throw new RuntimeException(
	                    "Element was not visible within "
	                            + TIMEOUT + " seconds",
	                    e
	            );

	        } catch (Exception e) {

	            throw new RuntimeException(
	                    "Failed while waiting for element visibility",
	                    e
	            );
	        }
	    }
	    
	    
	    	
	    public static String captureScreenshot(
            WebDriver driver,
            String testName
    ) throws IOException {

        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        String screenshotsDir =
                System.getProperty("user.dir")
                + "/Screenshots/";

        File screenshotFolder = new File(screenshotsDir);
        if (!screenshotFolder.exists()) {
            screenshotFolder.mkdirs();
        }

        String path = screenshotsDir + testName + ".png";

        File dest = new File(path);

        FileUtils.copyFile(src, dest);

        return path;
    }

    // =========================================
    // CLICK METHOD
    // =========================================
    public static void click(WebElement element) {
	        try {

	            waitForVisibility(element);

	            WebDriverWait wait = new WebDriverWait(
	                    DriverFactory.getDriver(),
	                    Duration.ofSeconds(TIMEOUT)
	            );

	            wait.until(
	                    ExpectedConditions.elementToBeClickable(element)
	            );

	            element.click();

	        } catch (ElementClickInterceptedException e) {

	            throw new RuntimeException(
	                    "Element click was intercepted: "
	                            + element,
	                    e
	            );

	        } catch (ElementNotInteractableException e) {

	            throw new RuntimeException(
	                    "Element is not interactable: "
	                            + element,
	                    e
	            );

	        } catch (StaleElementReferenceException e) {

	            throw new RuntimeException(
	                    "Element became stale while clicking: "
	                            + element,
	                    e
	            );

	        } catch (TimeoutException e) {

	            throw new RuntimeException(
	                    "Element was not clickable within timeout: "
	                            + element,
	                    e
	            );

	        } catch (Exception e) {

	            throw new RuntimeException(
	                    "Failed to click on element: "
	                            + element,
	                    e
	            );
	        }
	    }
	    public static void highlightElement(WebElement element) {

	        WebDriver driver = DriverFactory.getDriver();

	        JavascriptExecutor js =
	                (JavascriptExecutor) driver;

	        try {

	            js.executeScript(
	                    "arguments[0].style.border='3px solid yellow';"
	                    + "arguments[0].style.background='yellow';",
	                    element
	            );

	        } catch (Exception e) {

	            System.out.println(
	                    "Unable to highlight element : "
	                            + e.getMessage()
	            );
	        }
	    }
	    // =========================================
	    // TYPE METHOD
	    // =========================================
	    public static void sendKeys(WebElement element, String text) {

	        try {

	            waitForVisibility(element);

	            element.clear();
	            element.sendKeys(text);

	        } catch (ElementNotInteractableException e) {

	            throw new RuntimeException(
	                    "Unable to type into element: "
	                            + element,
	                    e
	            );

	        } catch (StaleElementReferenceException e) {

	            throw new RuntimeException(
	                    "Element became stale while typing: "
	                            + element,
	                    e
	            );

	        } catch (Exception e) {

	            throw new RuntimeException(
	                    "Failed to enter text into element: "
	                            + element,
	                    e
	            );
	        }
	    }

	    // =========================================
	    // GET TEXT
	    // =========================================
	    public static String getText(WebElement element) {

	        try {

	            waitForVisibility(element);

	            return element.getText();

	        } catch (NoSuchElementException e) {	

	            throw new RuntimeException(
	                    "Element not found while getting text",
	                    e
	            );

	        } catch (Exception e) {

	            throw new RuntimeException(
	                    "Failed to get text from element",
	                    e
	            );
	        }
	    }

	    // =========================================
	    // DISPLAYED CHECK
	    // =========================================
	    public static boolean isDisplayed(WebElement element) {

	        try {

	            waitForVisibility(element);

	            return element.isDisplayed();

	        } catch (Exception e) {

	            return false;
	        }
	    }

	    // =========================================
	    // JAVASCRIPT CLICK
	    // =========================================
	    public static void jsClick(WebElement element) {

	        try {

	            JavascriptExecutor js =
	                    (JavascriptExecutor) DriverFactory.getDriver();

	            js.executeScript(
	                    "arguments[0].click();",
	                    element
	            );

	        } catch (Exception e) {

	            throw new RuntimeException(
	                    "JavaScript click failed",
	                    e
	            );
	        }
	    }
	    /**
	     * Wait until complete page is loaded
	     */
	    public static void waitForPageLoad(WebDriver driver, int timeoutInSeconds) {

	        try {

	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

	            ExpectedCondition<Boolean> pageLoadCondition = webDriver ->
	                    ((JavascriptExecutor) webDriver)
	                            .executeScript("return document.readyState")
	                            .equals("complete");

	            wait.until(pageLoadCondition);

	            System.out.println("Page loaded successfully.");

	        } catch (TimeoutException e) {

	            throw new RuntimeException(
	                    "Timeout: Page did not load within "
	                            + timeoutInSeconds + " seconds.",
	                    e);

	        } catch (Exception e) {

	            throw new RuntimeException(
	                    "Failed while waiting for page load.",
	                    e);
	        }
	    }

	    /**
	     * Wait until JQuery is completed
	     */
	    public static void waitForJQueryLoad(WebDriver driver, int timeoutInSeconds) {

	        try {

	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));

	            ExpectedCondition<Boolean> jQueryLoad = webDriver -> {

	                try {

	                    return (Boolean) ((JavascriptExecutor) webDriver)
	                            .executeScript("return jQuery.active==0");

	                } catch (Exception e) {

	                    // JQuery not present
	                    return true;
	                }
	            };

	            wait.until(jQueryLoad);

	            System.out.println("JQuery load completed.");

	        } catch (TimeoutException e) {

	            throw new RuntimeException(
	                    "Timeout: JQuery did not load within "
	                            + timeoutInSeconds + " seconds.",
	                    e);

	        } catch (Exception e) {

	            throw new RuntimeException(
	                    "Failed while waiting for JQuery load.",
	                    e);
	        }
	    }

	    /**
	     * Common method for complete page synchronization
	     */
	    public static void waitForFullPageLoad(WebDriver driver, int timeoutInSeconds) {

	        try {

	            waitForPageLoad(driver, timeoutInSeconds);

	            waitForJQueryLoad(driver, timeoutInSeconds);

	            System.out.println("Full page loaded successfully.");

	        } catch (Exception e) {

	            throw new RuntimeException(
	                    "Full page load synchronization failed.",
	                    e);
	        }
	    }
	    

	    // =========================================
	    // SCROLL INTO VIEW
	    // =========================================
	    public static void scrollIntoView(WebElement element) {

	        try {

	            JavascriptExecutor js =
	                    (JavascriptExecutor) DriverFactory.getDriver();

	            js.executeScript(
	                    "arguments[0].scrollIntoView(true);",
	                    element
	            );

	        } catch (Exception e) {

	            throw new RuntimeException(
	                    "Failed to scroll element into view",
	                    e
	            );
	        }
	    }
	}


