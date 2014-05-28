package com.algocrafts.browsers;

import com.algocrafts.selenium.WebDriverSupplier;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;

public class Safari implements WebDriverSupplier<SafariDriver> {
    @Override
    public SafariDriver init() {
        return new SafariDriver();
    }

    @Override
    public File takeScreenShot(WebDriverSupplier<SafariDriver> webDriver) {
        return webDriver.get().getScreenshotAs(FILE);
    }
}
