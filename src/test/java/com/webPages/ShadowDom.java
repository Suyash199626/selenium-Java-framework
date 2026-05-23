package com.webPages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.commonUtils.commonUtils;
import com.reporting.Reporting;

public class ShadowDom {

	private final WebDriver driver;

	public ShadowDom(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void VerifyHomepage() throws IOException, InterruptedException {
		WebElement shadowHost = driver.findElement(By.cssSelector("shop-app"));

		SearchContext shadowRoot1 = shadowHost.getShadowRoot();

		WebElement appHeader = shadowRoot1.findElement(By.cssSelector("app-header"));
		if (appHeader.isDisplayed()) {
			commonUtils.highlightElement(appHeader);
			Reporting.logResult("User Navigates to the URL", "Homepage should be displayed successfully",
					"Homepage is displayed successfully", "PASS");
			commonUtils.captureScreenshot(driver, "Homepage");
		} else {
			Reporting.logResult("User Navigates to the URL", "Homepage should be displayed successfully",
					"Homepage is not displayed", "FAIL");
			commonUtils.captureScreenshot(driver, "Homepage");
			Assert.assertTrue(false);
		}
		  // shop-list shadow host
        WebElement shopList = shadowRoot1.findElement(By.cssSelector("shop-list"));

        SearchContext shadowRoot2 = shopList.getShadowRoot();

        // Locate first product
        WebElement firstProduct = shadowRoot2.findElement(
                By.cssSelector("ul > li:nth-child(1) a"));
        //click on product 
        firstProduct.click();
        commonUtils.waitForPageLoad(driver, 30);
        Thread.sleep(3000);
        //Click on Add to cart
        WebElement shopDetail=shadowRoot1.findElement(By.cssSelector("shop-detail"));
       SearchContext shadowRoot3= shopDetail.getShadowRoot();
       //Verify Add to Cart page
       
       if ( shadowRoot3.findElement(By.cssSelector("#content > div > shop-button > button")).isDisplayed()) {
			commonUtils.highlightElement(appHeader);
			Reporting.logResult("User clicks on Product", "Add To Cart page should be displayed successfully",
					"Add to Cart page is displayed successfully", "PASS");
			commonUtils.captureScreenshot(driver, "Add to Cart Page");
		} else {
			Reporting.logResult("User clicks on Product", "Add To Cart page should be displayed successfully",
					"Add to Cart page is not displayed", "FAIL");
			commonUtils.captureScreenshot(driver, "Add to Cart Page");
			Assert.assertTrue(false);
		}
       //Click on Add To Cart 
       shadowRoot3.findElement(By.cssSelector("#content > div > shop-button > button")).click();
       
       //Shop Cart Model
       WebElement shopCart=shadowRoot1.findElement(By.cssSelector("shop-cart-modal"));
       SearchContext shadowRoot4= shopCart.getShadowRoot();
       Thread.sleep(3000);
       //click on checkout option
       shadowRoot4.findElement(By.cssSelector("div:nth-child(3) > shop-button:nth-child(2) > a")).click();
       
       //Shop Cart Model
       WebElement checkoutForm=shadowRoot1.findElement(By.cssSelector("shop-checkout"));
       SearchContext shadowRoot5= checkoutForm.getShadowRoot();
       WebElement checkoutPage = shadowRoot5.findElement(By.cssSelector("#checkoutForm > form > header > h1"));
       if(shadowRoot5.findElement(By.cssSelector("#checkoutForm > form > header > h1")).isDisplayed())
       {
    	   commonUtils.highlightElement(checkoutPage);
    	   Reporting.logResult("User clicks on Checkout", "Checkout page should be displayed successfully",
					"Checkout page is displayed successfully", "PASS");
			commonUtils.captureScreenshot(driver, "Add to Cart Page");
       }
       else
       {
    	   Reporting.logResult("User clicks on Checkout", "Checkout page should be displayed successfully",
					"Checkout page is not displayed", "FAIL");
			commonUtils.captureScreenshot(driver, "Add to Cart Page");
       }
	}

}