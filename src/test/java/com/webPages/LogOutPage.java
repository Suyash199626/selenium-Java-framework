package com.webPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.commonUtils.commonUtils;

public class LogOutPage
{

    private final WebDriver driver;

    public LogOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='Open Menu']")
    private WebElement threeLines;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logOutButton;

    
    public void clickOnUSer() {
    	threeLines.click();
    }

    public void clickOnLogout() {
    	logOutButton.click();
    }


    public final void LogOut() {
    	clickOnUSer();
    	commonUtils.waitForVisibility(logOutButton);
    	clickOnLogout();
        
    }
}