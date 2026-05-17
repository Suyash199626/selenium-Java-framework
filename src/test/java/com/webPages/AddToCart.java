package com.webPages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.commonUtils.commonUtils;
import com.reporting.Reporting;

public class AddToCart {

	private final WebDriver driver;

	public AddToCart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='inventory_item_name ']")
	private List<WebElement> productName;

	@FindBy(xpath = "//button[text()='Add to cart']")
	private WebElement addtoCart;

	@FindBy(xpath = "//a[@data-test='shopping-cart-link']")
	private WebElement clickOnCart;

	@FindBy(xpath = "//span[text()='Your Cart']")
	private WebElement verifyCart;

	@FindBy(xpath = "//div[@data-test='inventory-item']")
	private WebElement inventoryItem;

	@FindBy(xpath = "//button[text()='Checkout']")
	private WebElement buttonCheckout;

	@FindBy(xpath = "//span[text()='Checkout: Your Information']")
	private WebElement checkOutPage;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	private WebElement firstName;

	@FindBy(xpath = "//input[@placeholder='Last Name']")
	private WebElement lastName;

	@FindBy(xpath = "//input[@placeholder='Zip/Postal Code']")
	private WebElement zipCode;

	@FindBy(xpath = "//input[@data-test='continue']")
	private WebElement buttonContinue;

	@FindBy(xpath = "//button[@data-test='finish']")
	private WebElement buttonFinish;

	@FindBy(xpath = "//h2[text()='Thank you for your order!']")
	private WebElement checkoutConfirmation;

	public void productList() {
		if (productName.size() > 0) {
			productName.get(1);
			// click on add to cart button
			addtoCart.click();
		}

	}

	public void openCart() {

		clickOnCart.click();
	}

	public void verifyCartPage() throws IOException {

		if (verifyCart.isDisplayed()) {
			commonUtils.highlightElement(addtoCart);
			Reporting.logResult("User Click on Shopping Cart", "Cart page should be displayed Successfully",
					"Cart page is displayed successfully", "PASS");
			commonUtils.captureScreenshot(driver, "Add to cart");
		} else {
			Reporting.logResult("User Click on Shopping Cart", "Cart page should be displayed Successfully",
					"Cart page is not displayed", "FAIL");
			commonUtils.captureScreenshot(driver, "Add to cart");
			Assert.assertTrue(false);
		}
	}

	public void VerifyInventoryItem() throws IOException {
		if (inventoryItem.isDisplayed()) {
			commonUtils.highlightElement(inventoryItem);
			Reporting.logResult("User Click on Shopping Cart", "Product should be added to cart successfully",
					"Product is added to cart successfully", "PASS");
			commonUtils.captureScreenshot(driver, "Product added to cart");
		} else {
			Reporting.logResult("User Click on Shopping Cart", "Product should be added to cart successfully",
					"Product is not added to cart", "FAIL");
			commonUtils.captureScreenshot(driver, "Product added to cart");
			Assert.assertTrue(false);
		}
	}

	public void checkout() {
		buttonCheckout.click();
	}

	public void verifyCheckoutPage() throws IOException {
		if (checkOutPage.isDisplayed()) {
			commonUtils.highlightElement(checkOutPage);
			Reporting.logResult("User Click on Checkout", "Checkout page should be displayed successfully",
					"Checkout page is displayed successfully", "PASS");
			commonUtils.captureScreenshot(driver, "Checkout page");
		} else {
			Reporting.logResult("User Click on Shopping Cart", "Product should be added to cart successfully",
					"Product is not added to cart", "FAIL");
			commonUtils.captureScreenshot(driver, "Checkout page");
			Assert.assertTrue(false);
		}
	}

	public final void addToCart() throws IOException {
		productList();
		openCart();
		commonUtils.waitForVisibility(verifyCart);
		verifyCartPage();
		VerifyInventoryItem();
		checkout();
	}

	public void checkoutDetails(String FirstName, String LastName, String ZipCode) {
		firstName.sendKeys(FirstName);
		lastName.sendKeys(LastName);
		zipCode.sendKeys(ZipCode);
		buttonContinue.click();
		commonUtils.waitForVisibility(buttonFinish);
		buttonFinish.click();
	}

	public void verifyCheckoutConfirmation() throws IOException {

		if (checkoutConfirmation.isDisplayed()) {
			commonUtils.highlightElement(checkoutConfirmation);
			Reporting.logResult("User Click on finish button",
					" " + checkoutConfirmation.getText() + " message should be dispalyed successfully",
					" " + checkoutConfirmation.getText() + " message is dispalyed successfully", "PASS");
			commonUtils.captureScreenshot(driver, "checkout confirmation msg");
		} else {
			Reporting.logResult("User Click on finish button",
					" " + checkoutConfirmation.getText() + " message should be dispalyed successfully",
					" " + checkoutConfirmation.getText() + " message is not dispalyed", "FAIL");
			commonUtils.captureScreenshot(driver, "checkout confirmation msg");
			commonUtils.captureScreenshot(driver, "checkout confirmation msg");
			Assert.assertTrue(false);
		}
	}

	public final void pageCheckout(String FirstName, String LastName, String ZipCode) throws IOException {
		commonUtils.waitForVisibility(checkOutPage);
		verifyCheckoutPage();
		checkoutDetails(FirstName, LastName, ZipCode);
		verifyCheckoutConfirmation();

	}
}