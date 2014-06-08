package com.algocrafts.browsers;


import com.algocrafts.browsers.*;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.WebDriverSupplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;

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
