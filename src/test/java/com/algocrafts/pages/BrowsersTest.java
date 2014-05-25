package com.algocrafts.pages;

import org.junit.Test;

import static org.junit.Assert.*;

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