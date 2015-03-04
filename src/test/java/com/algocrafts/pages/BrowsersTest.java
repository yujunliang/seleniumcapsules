package com.algocrafts.pages;

import com.algocrafts.browsers.Browsers;
import com.algocrafts.selenium.Browser;
import org.junit.Test;

public class BrowsersTest {

    @Test
    public void testGetSupplier() throws Exception {
        Browser firefox = Browsers.FIREFOX;
         firefox = Browsers.CHROME;
        firefox = Browsers.HEADLESS_LINUX;
        firefox = Browsers.SAFARI;
        firefox = Browsers.HEADLESS_MAC;
        firefox = Browsers.INTERNET_EXPLORER;
    }
}