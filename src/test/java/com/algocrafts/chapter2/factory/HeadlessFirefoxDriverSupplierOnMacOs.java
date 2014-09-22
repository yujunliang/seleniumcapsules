package com.algocrafts.chapter2.factory;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.function.Supplier;


public class HeadlessFirefoxDriverSupplierOnMacOs implements Supplier<FirefoxDriver> {
    @Override
    public FirefoxDriver get() {
        FirefoxBinary binary = new FirefoxBinary(new File("/opt/local/lib/firefox-x11/firefox-bin"));
        binary.setEnvironmentProperty("DISPLAY", ":88");
        return new FirefoxDriver(binary, new FirefoxProfile());
    }
}