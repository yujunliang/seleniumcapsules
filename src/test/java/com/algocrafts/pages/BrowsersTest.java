package com.algocrafts.pages;

import com.algocrafts.browsers.Browsers;
import org.junit.Test;

public class BrowsersTest {

    @Test
    public void testGetSupplier() throws Exception {
        Browsers firefox = Browsers.FIREFOX;
         firefox = Browsers.CHROME;
        firefox = Browsers.HEADLESS_LINUX;
        firefox = Browsers.SAFARI;
        firefox = Browsers.HEADLESS_MAC;
        firefox = Browsers.INTERNET_EXPLORER;
    }
}