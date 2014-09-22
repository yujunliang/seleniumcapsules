package com.algocrafts.browsers;

import com.algocrafts.selenium.CachedWebDriverSupplier;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverSupplier implements CachedWebDriverSupplier<ChromeDriver> {

    @Override
    public ChromeDriver init() {
        return new ChromeDriver();
    }
}
