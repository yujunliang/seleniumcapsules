package com.algocrafts.browsers;

import com.algocrafts.selenium.WebDriverSupplier;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class Firefox implements WebDriverSupplier<FirefoxDriver> {
    @Override
    public FirefoxDriver init() {
        FirefoxBinary binary = new FirefoxBinary(new File("src/main/resources/Firefox/Contents/MacOS/firefox-bin"));
        FirefoxProfile profile = new FirefoxProfile(new File("src/main/resources/Firefox/Profiles/default"));
        return new FirefoxDriver(binary, profile);
    }
}
