package com.algocrafts.browsers;


import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.CachedWebDriverSupplier;
import org.openqa.selenium.WebDriver;

public enum Browsers implements Browser {

    FIREFOX(new FirefoxDriverSupplier()) ,
    CHROME(new ChromeDriverSupplier()),
    HEADLESS_MAC(new HeadlessFirefoxDriverSupplierOnMacOs()),
    HEADLESS_LINUX(new HeadlessFirefoxDriverSupplierOnLinux()),
    SAFARI(new SafariDriverSupplier()),
    INTERNET_EXPLORER(new InternetExplorerDriverSupplier());

    private Browsers(CachedWebDriverSupplier<? extends WebDriver> driverDecorator) {
        this.webDriverSupplier = driverDecorator;
    }

    private final CachedWebDriverSupplier<? extends WebDriver> webDriverSupplier;

    public CachedWebDriverSupplier<? extends WebDriver> getSupplier() {
        return webDriverSupplier;
    }
}
