package com.algocrafts.selenium;

import com.algocrafts.objectcache.SelfPopulatingCache;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.util.function.Supplier;

import static org.slf4j.LoggerFactory.getLogger;

public interface CachedWebDriverSupplier<T extends WebDriver> extends Supplier<T> {

    SelfPopulatingCache<CachedWebDriverSupplier<?>, ? extends WebDriver> store
            = SelfPopulatingCache.create(CachedWebDriverSupplier::init);

    Logger logger = getLogger(CachedWebDriverSupplier.class);

    T init();

    @SuppressWarnings("unchecked")
    default T get() {
        return (T) store.valueOf(this);
    }
}
