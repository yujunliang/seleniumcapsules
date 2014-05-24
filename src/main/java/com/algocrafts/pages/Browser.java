package com.algocrafts.pages;

import com.algocrafts.browsers.WebDriverSupplier;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

public interface Browser<T extends WebDriver> extends WebDriverSupplier<T>, WebDriver{

    public static final Logger logger = getLogger(Browsers.class);

    void save(String title);

    default public Stream<Element> getElements(By by) {
        return findElements(by).stream().map(Element::new);
    }

    default public void accept() {
        try {
            switchTo().alert().accept();
        } catch (Exception e) {
        }
    }

    default public void cancel() {
        try {
            switchTo().alert().dismiss();
        } catch (Exception e) {
        }
    }

    default public void frame(int i) {
        switchTo().frame(i);
    }

    default public void defaultContent() {
        switchTo().defaultContent();
    }

    default public File takeScreenShot(WebDriverSupplier driver) {
        return driver.takeScreenShot(this);
    }

    default public void mouseOver(Element element) {
        Actions builder = new Actions(get());
        Actions hoverOverRegistrar = builder.moveToElement(element);
        hoverOverRegistrar.perform();
    }

    @Override
    default public Element findElement(By by) {
        logger.info("Seeking [{}]", by);
        WebElement element = get().findElement(by);
        logger.info("Found [{}]", element);
        return new Element(element);
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
    default public List<WebElement> findElements(By by) {
        return get().findElements(by);
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
}
