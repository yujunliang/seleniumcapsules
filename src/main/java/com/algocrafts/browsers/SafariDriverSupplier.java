package com.algocrafts.browsers;

import com.algocrafts.selenium.CachedWebDriverSupplier;
import org.openqa.selenium.safari.SafariDriver;

public class SafariDriverSupplier implements CachedWebDriverSupplier<SafariDriver> {
    @Override
    public SafariDriver init() {
        return new SafariDriver();
    }

}
