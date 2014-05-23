package com.algocrafts.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;

public class Firefox implements Driver {
    @Override
    public WebDriver get() {
        FirefoxBinary binary = new FirefoxBinary(new File("src/main/resources/Firefox/Contents/MacOS/firefox-bin"));
        FirefoxProfile profile = new FirefoxProfile(new File("src/main/resources/Firefox/Profiles/default"));
        return new FirefoxDriver(binary, profile);
    }

    @Override
    public File takeScreenShot(WebDriver driver) {
        FirefoxDriver screenshot = (FirefoxDriver) driver;
        return screenshot.getScreenshotAs(FILE);
    }
}
