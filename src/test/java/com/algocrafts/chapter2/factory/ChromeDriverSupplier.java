package com.algocrafts.chapter2.factory;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.function.Supplier;

public class ChromeDriverSupplier implements Supplier<ChromeDriver> {

    @Override
    public ChromeDriver get() {
        return new ChromeDriver();
    }
}
