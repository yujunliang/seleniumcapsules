package com.algocrafts.chapter2.factory;

import org.openqa.selenium.safari.SafariDriver;

import java.util.function.Supplier;

public class SafariDriverSupplier implements Supplier<SafariDriver> {
    @Override
    public SafariDriver get() {
        return new SafariDriver();
    }

}
