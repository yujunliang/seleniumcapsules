package com.algocrafts.pages;

import com.algocrafts.forms.FormControl;
import com.algocrafts.locators.Locators;
import com.algocrafts.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.List;
import java.util.function.Supplier;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.selectors.ClassName.PAGE_TITLE;
import static org.slf4j.LoggerFactory.getLogger;

public class Page implements FormControl<Page> {

    public static final Logger logger = getLogger(Page.class);

    private final Browser<?> browser;

    private final Clickable clickable;

    public Page(Page page) {
        this(page.browser, null);
    }

    public Page(Page page, Clickable clickable) {
        this(page.browser, clickable);
    }

    public Page(Browser<?> browser) {
        this(browser, null);
    }

    public Page(Browser<?> browser, Clickable clickable) {
        this.browser = browser;
        this.clickable = clickable;
    }

    public final void open() {
        if (clickable != null) {
            clickable.click();
        }
    }

    @Override
    public List<WebElement> findElements(By by) {
        return browser.findElements(by);
    }

    /**
     * Find the first element or throw NoSuchElementException
     *
     * @param by selector
     * @return the first element or throw NoSuchElementException
     */
    @Override
    public final Element findElement(final By by) {
        return browser.findElement(by);
    }

    @Override
    public final void onTimeout() {
        if (logger.isDebugEnabled()) {
            browser.save(this.getTitle());
        }
    }

    public final void accept() {
        browser.accept();
    }

    public final void cancel() {
        browser.cancel();
    }

    public final void close() {
        if (!Boolean.getBoolean("keep.browser.open")) {
            browser.close();
            quit();
        }
    }

    public final void quit() {
        browser.quit();
    }

    public final Locator<Element, Element> mouseOver() {
        return element -> {
            browser.mouseOver(element);
            return element;
        };
    }

    public final void mouseOver(Element element) {
        browser.mouseOver(element);
    }

    public final void dragAndDrop(Supplier<By> from, Supplier<By> to) {
        browser.dragAndDrop(from.get(), to.get());
    }

    public String getTitle() {
        try {
            return Locators.<Page>optionalElement(PAGE_TITLE).andThen(GET).andThen(TEXT).locate(this);
        } catch (Exception e) {
            return "";
        }
    }

    public final Page frame(int i) {
        browser.frame(i);
        return this;
    }

    public final Page defaultContent() {
        browser.defaultContent();
        return this;
    }

    public void reload() {
        browser.navigate().refresh();
    }
}