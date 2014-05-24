package com.algocrafts.browsers;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.function.Supplier;

public interface WebDriverSupplier<T extends WebDriver> extends Supplier<T>{

    File takeScreenShot(WebDriverSupplier<T> driver);

}
