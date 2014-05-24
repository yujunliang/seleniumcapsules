package com.algocrafts.browsers;

import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;


public class InternetExplorer implements WebDriverSupplier<InternetExplorerDriver> {
    @Override
    public InternetExplorerDriver init() {
        return new InternetExplorerDriver();
    }

    @Override
    public File takeScreenShot(WebDriverSupplier<InternetExplorerDriver> webDriver) {
        return webDriver.get().getScreenshotAs(FILE);
    }
}