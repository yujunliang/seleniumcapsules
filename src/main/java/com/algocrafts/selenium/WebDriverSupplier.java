package com.algocrafts.selenium;

import com.algocrafts.objectcache.SelfPopulatingCache;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.io.File;
import java.util.function.Supplier;

import static org.slf4j.LoggerFactory.getLogger;

public interface WebDriverSupplier<T extends WebDriver> extends Supplier<T> {


    public static final SelfPopulatingCache<WebDriverSupplier<?>, ? extends WebDriver> store =
            SelfPopulatingCache.create((WebDriverSupplier<?> supplier) -> supplier.init());

    public static final Logger logger = getLogger(WebDriverSupplier.class);

    T init();

    @SuppressWarnings("unchecked")
    default public T get() {
        return (T) store.valueOf(this);
    }
}
