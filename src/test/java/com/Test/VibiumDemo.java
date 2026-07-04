package com.Test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.vibium.Browser;
import com.vibium.Page;
import com.vibium.Vibium;

public class VibiumDemo {

	@Test
	public void vibiumTest() throws IOException {

        Browser browser = Vibium.start();
        Page page = browser.page();
        page.go("https://www.google.com");
        System.out.println(page.title());
        browser.stop();
    }

}