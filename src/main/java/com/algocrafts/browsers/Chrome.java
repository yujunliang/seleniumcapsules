package com.algocrafts.browsers;

import com.algocrafts.selenium.WebDriverSupplier;
import org.openqa.selenium.chrome.ChromeDriver;

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
}
