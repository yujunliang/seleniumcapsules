package com.algocrafts.browsers;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;


public class HeadlessMacOS implements WebDriverSupplier<FirefoxDriver> {
    @Override
    public FirefoxDriver init() {
        FirefoxBinary binary = new FirefoxBinary(new File("/opt/local/lib/firefox-x11/firefox-bin"));
        binary.setEnvironmentProperty("DISPLAY", ":88");
        return new FirefoxDriver(binary, new FirefoxProfile());
    }

    @Override
    public File takeScreenShot(WebDriverSupplier<FirefoxDriver> webDriver) {
        return webDriver.get().getScreenshotAs(FILE);
    }
}