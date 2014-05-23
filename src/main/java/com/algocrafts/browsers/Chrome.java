package com.algocrafts.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;


public class Chrome implements Driver {
    @Override
    public WebDriver get() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome/chromedriver");
        return new ChromeDriver();
    }

    @Override
    public File takeScreenShot(WebDriver webDriver) {
        ChromeDriver screenshot = (ChromeDriver) webDriver;
        return screenshot.getScreenshotAs(FILE);
    }
}
