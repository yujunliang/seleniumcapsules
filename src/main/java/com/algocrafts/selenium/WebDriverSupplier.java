package com.algocrafts.selenium;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.function.Supplier;

public interface WebDriverSupplier<T extends WebDriver> extends Supplier<T>{

    T init();

    File takeScreenShot(WebDriverSupplier<T> driver);

    @Override
    default public T get() {
        return this.init();
    }
}
