package com.algocrafts.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;

import java.util.List;
import java.util.Set;

public interface Browser<T extends WebDriver> extends WebDriverSupplier<T>,
        WebDriver, HasInputDevices, JavascriptExecutor, HasCapabilities {

    default public void accept() {
        try {
            switchTo().alert().accept();
        } catch (Exception e) {
            logger.info("Failed to click OK", e);
        }
    }

    default public void cancel() {
        try {
            switchTo().alert().dismiss();
        } catch (Exception e) {
            logger.info("Failed to click Cancel", e);
        }
    }

    default public void frame(int i) {
        switchTo().frame(i);
    }

    default public void defaultContent() {
        switchTo().defaultContent();
    }

    default public void mouseOver(Element element) {
        new Actions(get()).moveToElement(element).perform();
    }

    default public void dragAndDrop(By from, By to) {
        new Actions(get()).dragAndDrop(findElement(from), findElement(to)).perform();
    }

    @Override
    default public Element findElement(By by) {
        return new ElementFinder(by).locate(get());
    }

    @Override
    default public List<WebElement> findElements(By by) {
        return new ElementsFinder(by).locate(get());
    }

    @Override
    default public void get(String url) {
        get().get(url);
    }

    @Override
    default public String getCurrentUrl() {
        return get().getCurrentUrl();
    }

    @Override
    default public String getTitle() {
        return get().getTitle();
    }

    @Override
    default public String getPageSource() {
        return get().getPageSource();
    }

    @Override
    default public Set<String> getWindowHandles() {
        return get().getWindowHandles();
    }

    @Override
    default public String getWindowHandle() {
        return get().getWindowHandle();
    }

    @Override
    default public TargetLocator switchTo() {
        return get().switchTo();
    }

    @Override
    default public Navigation navigate() {
        return get().navigate();
    }

    @Override
    default public Options manage() {
        return get().manage();
    }

    @Override
    default public void close() {
        get().close();
    }

    @Override
    default public void quit() {
        store.valueOf(this).quit();
        store.remove(this);
    }

    @Override
    default public Keyboard getKeyboard() {
        HasInputDevices t = (HasInputDevices) get();
        return t.getKeyboard();
    }

    @Override
    default public Mouse getMouse() {
        HasInputDevices t = (HasInputDevices) get();
        return t.getMouse();
    }

    @Override
    default public Capabilities getCapabilities() {
        HasCapabilities hasCapabilities = (HasCapabilities) get();
        return hasCapabilities.getCapabilities();
    }

    @Override
    default public Object executeScript(String script, Object... args) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) get();
        return javascriptExecutor.executeScript(script, args);
    }

    @Override
    default public Object executeAsyncScript(String script, Object... args) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) get();
        return javascriptExecutor.executeAsyncScript(script, args);
    }

}
