package com.algocrafts.browsers;

import com.algocrafts.selenium.WebDriverSupplier;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class InternetExplorer implements WebDriverSupplier<InternetExplorerDriver> {
    @Override
    public InternetExplorerDriver init() {
        return new InternetExplorerDriver();
    }

}