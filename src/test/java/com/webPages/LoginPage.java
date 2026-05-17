package com.webPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.IOException;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.commonUtils.commonUtils;
import com.reporting.Reporting;

public class LoginPage {

	private final WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Username']")
	private WebElement usernameTextBox;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement passwordTextBox;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginButton;

	@FindBy(xpath = "//div[text()='Swag Labs']")
	private WebElement homePage;

	public void enterUsername(String username) {
		usernameTextBox.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordTextBox.sendKeys(password);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public void verifyHomePages() throws IOException {
		commonUtils.waitForVisibility(homePage);
		if (homePage.isDisplayed()) {
			commonUtils.highlightElement(homePage);
			Reporting.logResult("User Clicks on Login button", "Homepage should be displayed successfully",
					"Homepage is dispalyed successfully", "PASS");
			commonUtils.captureScreenshot(driver, "Homepage");
		} else {
			Reporting.logResult("User Clicks on Login button", "Homepage should be displayed successfully",
					"Homepage is not dispalyed", "FAIL");
			commonUtils.captureScreenshot(driver, "Homepage");
			Assert.assertTrue(false);
		}
	}

	public final void login(String username, String password) {
		
		commonUtils.waitForFullPageLoad(driver, 60);
		commonUtils.waitForVisibility(loginButton);
		enterUsername(username);
		enterPassword(password);
		clickLogin();
	}
}