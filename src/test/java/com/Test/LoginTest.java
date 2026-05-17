package com.Test;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.commonUtils.commonUtils;
import com.framework.baseTest.BaseTest;
import com.webPages.AddToCart;
import com.webPages.LogOutPage;
import com.webPages.LoginPage;

@Listeners(com.listeners.TestListener.class)
public class LoginTest extends BaseTest {

	@Test
	public void validLoginTest() throws IOException {
		String url = jsonValues.get("Url").asText();
		commonUtils.navigateURL(driver, url, 10);
		LoginPage login = new LoginPage(driver);
		// 1. Successful Login Flow
		login.login(jsonValues.get("USERNAME").asText(), jsonValues.get("PASSWORD").asText());
		//Verify Home page is displayed 
		login.verifyHomePages();
		
		//Add the product to cart
		AddToCart addtoCart=new AddToCart(driver);
		addtoCart.addToCart();
		
		//Checkout Page
		addtoCart.pageCheckout(jsonValues.get("FirstName").asText(),jsonValues.get("LastName").asText(),jsonValues.get("ZipCode").asText());
		
		//Log-out the user 
		LogOutPage logout = new LogOutPage(driver);
		logout.LogOut();

	}
	
}