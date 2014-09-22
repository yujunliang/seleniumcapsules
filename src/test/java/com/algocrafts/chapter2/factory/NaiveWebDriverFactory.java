package com.algocrafts.chapter2.factory;


import org.openqa.selenium.WebDriver;

public class NaiveWebDriverFactory {

    public static WebDriver getInstance(String webDriverType) {
        if (webDriverType.equals("firefox")) {
            new FirefoxDriverSupplier().get();
        } else if (webDriverType.equals("chrome")) {
            new ChromeDriverSupplier().get();
        } else if (webDriverType.equals("safari")) {
            new SafariDriverSupplier().get();
        }
        throw new UnsupportedOperationException("Unknow browser type");
    }
}
