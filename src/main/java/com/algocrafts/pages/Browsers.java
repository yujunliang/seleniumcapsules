package com.algocrafts.pages;


import com.algocrafts.browsers.*;
import org.openqa.selenium.WebDriver;

public enum Browsers implements Browser {

    FIREFOX(new Firefox()) ,
    CHROME(new Chrome()),
    HEADLESS_MAC(new HeadlessMacOS()),
    HEADLESS_LINUX(new HeadlessLinux()),
    SAFARI(new Safari()),
    INTERNET_EXPLORER(new InternetExplorer());

    private Browsers(WebDriverSupplier<? extends WebDriver> driverDecorator) {
        this.webDriverSupplier = driverDecorator;
    }

    private final WebDriverSupplier<? extends WebDriver> webDriverSupplier;

    public WebDriverSupplier<? extends WebDriver> getSupplier() {
        return webDriverSupplier;
    }

}
