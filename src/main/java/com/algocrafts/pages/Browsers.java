package com.algocrafts.pages;


import com.algocrafts.browsers.*;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.slf4j.LoggerFactory.getLogger;

public enum Browsers implements Browser {

    FIREFOX(new Firefox()),
    CHROME(new Chrome()),
    HEADLESS_MAC(new HeadlessMacOS()),
    HEADLESS_LINUX(new HeadlessLinux()),
    SAFARI(new Safari()),
    INTERNET_EXPLORER(new InternetExplorer());

    private Browsers(WebDriverSupplier driverDecorator) {
        this.driver = driverDecorator;
    }

    private final WebDriverSupplier<WebDriver> driver;

    private static final Logger logger = getLogger(Browsers.class);

    private static final ThreadLocal<WebDriver> store = new ThreadLocal<>();

    @Override
    public WebDriver get() {
        WebDriver webDriver = store.get();
        if (webDriver == null) {
            webDriver = driver.get();
            store.set(webDriver);
        }
        return webDriver;
    }

    public void quit() {
        store.get().quit();
        store.remove();
    }

    public void save(String title) {
        logger.info("Saving screenshot [title={}]", title);
        File scrFile = null;
        try {
            scrFile = takeScreenShot(driver);
            copyFile(scrFile, new File("target/screenshots/" + title + new Date().getTime() + ".png"));
            scrFile.delete();
        } catch (IOException e) {
            try {
                copyFile(scrFile, new File("target/screenshots/" + new Date().getTime() + ".png"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
