package com.algocrafts.chapter2.factory;


import com.algocrafts.browsers.ChromeDriverSupplier;
import com.algocrafts.browsers.FirefoxDriverSupplier;
import com.algocrafts.browsers.HeadlessFirefoxDriverSupplierOnLinux;
import com.algocrafts.browsers.HeadlessFirefoxDriverSupplierOnMacOs;
import com.algocrafts.browsers.InternetExplorerDriverSupplier;
import com.algocrafts.browsers.SafariDriverSupplier;
import com.algocrafts.selenium.CachedWebDriverSupplier;
import org.openqa.selenium.WebDriver;

import java.util.function.Supplier;

public enum BetterWebDriverFactory implements Supplier {

    FIREFOX(new FirefoxDriverSupplier()) ,
    CHROME(new ChromeDriverSupplier()),
    HEADLESS_MAC(new HeadlessFirefoxDriverSupplierOnMacOs()),
    HEADLESS_LINUX(new HeadlessFirefoxDriverSupplierOnLinux()),
    SAFARI(new SafariDriverSupplier()),
    INTERNET_EXPLORER(new InternetExplorerDriverSupplier());

    private BetterWebDriverFactory(CachedWebDriverSupplier<? extends WebDriver> driverDecorator) {
        this.webDriverSupplier = driverDecorator;
    }

    private final CachedWebDriverSupplier<? extends WebDriver> webDriverSupplier;

    public WebDriver get() {
        return webDriverSupplier.get();
    }



}
