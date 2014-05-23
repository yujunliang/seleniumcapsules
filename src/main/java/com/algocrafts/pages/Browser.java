package com.algocrafts.pages;


import com.algocrafts.browsers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.apache.commons.io.FileUtils.copyFile;
import static org.slf4j.LoggerFactory.getLogger;

public enum Browser implements Supplier<WebDriver> {

    FIREFOX(new Firefox()),
    CHROME(new Chrome()),
    HEADLESS_MAC(new HeadlessMacOS()),
    HEADLESS_LINUX(new HeadlessLinux()),
    SAFARI(new Safari()),
    INTERNET_EXPLORER(new InternetExplorer());

    private Browser(Driver driverDecorator) {
        this.driver = driverDecorator;
    }

    private final Driver driver;

    private static final Logger logger = getLogger(Browser.class);

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

    public Element findElement(By by) {
        logger.info("Seeking [{}]", by);
        WebElement element = get().findElement(by);
        logger.info("Found [{}]", element);
        return new Element(element);
    }

    public Stream<Element> getElements(By by) {
        return get().findElements(by).stream().map(Element::new);
    }

    public void accept() {
        try {
            get().switchTo().alert().accept();
        } catch (Exception e) {
        }
    }

    public void cancel() {
        try {
            get().switchTo().alert().dismiss();
        } catch (Exception e) {
        }
    }

    public void frame(int i) {
        get().switchTo().frame(i);
    }

    public void defaultContent() {
        get().switchTo().defaultContent();
    }

    public void get(String url) {
        get().get(url);
    }

    public void mouseOver(WebElement element) {
        Actions builder = new Actions(get());
        Actions hoverOverRegistrar = builder.moveToElement(element);
        hoverOverRegistrar.perform();
    }

    public void save(String title) {
        logger.info("Saving screenshot [title={}]", title);
        File scrFile = null;
        try {
            scrFile = driver.takeScreenShot(get());
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

    public void close() {
        get().close();
    }
}
