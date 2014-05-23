package com.algocrafts.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;


public class InternetExplorer implements Driver {
    @Override
    public WebDriver get() {
        return new InternetExplorerDriver();
    }

    @Override
    public File takeScreenShot(WebDriver webDriver) {
        InternetExplorerDriver screenshot = (InternetExplorerDriver) webDriver;
        return screenshot.getScreenshotAs(FILE);
    }
}