package com.algocrafts.browsers;

import com.algocrafts.selenium.WebDriverSupplier;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;


public class Chrome implements WebDriverSupplier<ChromeDriver> {

    @Override
    public ChromeDriver init() {
        String key = "webdriver.chrome.driver";
        if (System.getProperty(key) == null) {
            String value = "src/main/resources/chrome/chromedriver";
            logger.info("System property[{}] not set, using the default value \"{}\".", key, value);
            System.setProperty(key, value);
        }
        return new ChromeDriver();
    }

    @Override
    public File takeScreenShot(WebDriverSupplier<ChromeDriver> supplier) {
        return supplier.get().getScreenshotAs(FILE);
    }

}
