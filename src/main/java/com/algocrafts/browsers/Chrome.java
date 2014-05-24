package com.algocrafts.browsers;

import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;


public class Chrome implements WebDriverSupplier<ChromeDriver> {
    @Override
    public ChromeDriver get() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome/chromedriver");
        return new ChromeDriver();
    }

    @Override
    public File takeScreenShot(WebDriverSupplier<ChromeDriver> supplier) {
        return supplier.get().getScreenshotAs(FILE);
    }
}
