package com.algocrafts.browsers;

import com.algocrafts.selenium.CachedWebDriverSupplier;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class FirefoxDriverSupplier implements CachedWebDriverSupplier<FirefoxDriver> {
    @Override
    public FirefoxDriver init() {
//         return new FirefoxDriver();
        FirefoxBinary binary = new FirefoxBinary(new File("src/main/resources/Firefox/Contents/MacOS/firefox-bin"));
        FirefoxProfile profile = new FirefoxProfile(new File("src/main/resources/Firefox/Profiles/default"));
        return new FirefoxDriver(binary, profile);
    }
}
