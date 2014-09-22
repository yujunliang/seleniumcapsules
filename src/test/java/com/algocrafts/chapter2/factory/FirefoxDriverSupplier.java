package com.algocrafts.chapter2.factory;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.function.Supplier;

public class FirefoxDriverSupplier implements Supplier<FirefoxDriver> {
    @Override
    public FirefoxDriver get() {
        String binaryDir = "src/main/resources/Firefox/Contents/MacOS/firefox-bin";
        File firefoxBinary = new File(binaryDir);
        FirefoxBinary binary = new FirefoxBinary(firefoxBinary);
        String dirName = "src/main/resources/Firefox/Profiles/default";
        File profileDir = new File(dirName);
        FirefoxProfile profile = new FirefoxProfile(profileDir);
        return new FirefoxDriver(binary, profile);
    }
}
