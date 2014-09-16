package com.algocrafts.browsers;

import com.algocrafts.selenium.WebDriverSupplier;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome implements WebDriverSupplier<ChromeDriver> {

    @Override
    public ChromeDriver init() {
        return new ChromeDriver();
    }
}
