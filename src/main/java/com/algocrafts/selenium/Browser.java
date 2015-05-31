package com.algocrafts.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;

import java.util.List;
import java.util.Set;

public interface Browser<T extends WebDriver> extends Actionable<T>,
        SearchScope<Browser<T>>,
        WebDriver,
        HasInputDevices, JavascriptExecutor, HasCapabilities {

    CachedWebDriverSupplier<T> getSupplier();

    @Override
    default void onTimeout() {
        if (logger.isDebugEnabled()) {
            save(this.getTitle());
        }
    }

    @Override
    default T init() {
        return getSupplier().init();
    }

    @Deprecated
    @Override
    default Element findElement(By by) {
        return new ElementFinder(by).locate(get());
    }

    @Deprecated
    @Override
    default List<WebElement> findElements(By by) {
        return new ElementsFinder(by).locate(get());
    }

    @Override
    default void get(String url) {
        get().get(url);
    }

    @Override
    default String getCurrentUrl() {
        return get().getCurrentUrl();
    }

    @Override
    default String getTitle() {
        return get().getTitle();
    }

    @Override
    default String getPageSource() {
        return get().getPageSource();
    }

    @Override
    default Set<String> getWindowHandles() {
        return get().getWindowHandles();
    }

    @Override
    default String getWindowHandle() {
        return get().getWindowHandle();
    }

    @Override
    default TargetLocator switchTo() {
        return get().switchTo();
    }

    @Override
    default Navigation navigate() {
        return get().navigate();
    }

    @Override
    default Options manage() {
        return get().manage();
    }

    @Override
    default void close() {
        get().close();
    }

    @Override
    default void quit() {
        store.valueOf(this).quit();
        store.remove(this);
    }

    @Override
    default Keyboard getKeyboard() {
        HasInputDevices t = (HasInputDevices) get();
        return t.getKeyboard();
    }

    @Override
    default Mouse getMouse() {
        HasInputDevices t = (HasInputDevices) get();
        return t.getMouse();
    }

    @Override
    default Capabilities getCapabilities() {
        HasCapabilities hasCapabilities = (HasCapabilities) get();
        return hasCapabilities.getCapabilities();
    }

    @Override
    default Object executeScript(String script, Object... args) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) get();
        return javascriptExecutor.executeScript(script, args);
    }

    @Override
    default Object executeAsyncScript(String script, Object... args) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) get();
        return javascriptExecutor.executeAsyncScript(script, args);
    }
}
