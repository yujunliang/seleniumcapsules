package com.algocrafts.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;

public class Safari implements Driver {
    @Override
    public WebDriver get() {
        return new SafariDriver();
    }

    @Override
    public File takeScreenShot(WebDriver webDriver) {
        SafariDriver screenshot = (SafariDriver) webDriver;
        return screenshot.getScreenshotAs(FILE);
    }
}
