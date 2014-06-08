package com.algocrafts.selenium;

import com.algocrafts.objectcache.SelfPopulatingCache;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.function.Supplier;

import static com.algocrafts.objectcache.SelfPopulatingCache.create;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.slf4j.LoggerFactory.getLogger;

public interface WebDriverSupplier<T extends WebDriver> extends Supplier<T> {

    public static final SelfPopulatingCache<WebDriverSupplier<?>, ? extends WebDriver> store =
            create((WebDriverSupplier<?> supplier) -> supplier.get());

    public static final Logger logger = getLogger(WebDriverSupplier.class);

    default public File takeScreenShot(WebDriverSupplier<T> driver) {
        return driver.takeScreenShot(this);
    }

    @SuppressWarnings("unchecked")
    default public T get() {
        return (T) store.valueOf(this);
    }

    default public void save(String title) {
        logger.info("Saving screenshot [title={}]", title);
        File scrFile = null;
        try {
            scrFile = this.takeScreenShot(this);
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
