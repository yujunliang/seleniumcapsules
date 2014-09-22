package com.algocrafts.chapter2.factory;

import com.algocrafts.selenium.CachedWebDriverSupplier;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class InternetExplorerDriverSupplier implements CachedWebDriverSupplier<InternetExplorerDriver> {
    @Override
    public InternetExplorerDriver init() {
        return new InternetExplorerDriver();
    }

}