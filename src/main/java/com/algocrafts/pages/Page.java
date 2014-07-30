package com.algocrafts.pages;

import com.algocrafts.conditions.Equals;
import com.algocrafts.forms.FormControl;
import com.algocrafts.locators.Locators;
import com.algocrafts.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.converters.PageFunctions.THE_PAGE_TITLE;
import static com.algocrafts.selectors.ClassName.PAGE_TITLE;
import static org.slf4j.LoggerFactory.getLogger;

public class Page implements Searchable<Page>, FormControl<Page> {

    public static final Logger logger = getLogger(Page.class);

    private final Predicate<Page> condition;

    private boolean close;

    private final Browser<?> browser;

    private final Clickable clickable;

    public Page(Page page) {
        this(page.browser, null, null, page.close);
    }

    public Page(Page page, Clickable clickable, String title) {
        this(page.browser, clickable, THE_PAGE_TITLE.and(new Equals(title)), page.close);
    }

    public Page(Page page, Clickable clickable, Predicate<Page> condition) {
        this(page.browser, clickable, condition, page.close);
    }

    public Page(Browser<?> browser) {
        this(browser, null, null, false);
    }

    public Page(Browser<?> browser, Clickable clickable) {
        this(browser, clickable, null, false);
    }

    public Page(Browser<?> browser, Clickable clickable, Predicate<Page> condition) {
        this(browser, clickable, condition, false);
    }

    public Page(Browser<?> browser, Clickable clickable, Predicate<Page> condition, boolean close) {
        this.browser = browser;
        this.clickable = clickable;
        this.condition = condition;
        this.close = close;
    }

    public final void open() {
        if (clickable != null) {
            clickable.click();
            if (condition != null) {
                until(condition);
            }
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
        if (close) {
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
            return Locators.<Page>optionalElement(PAGE_TITLE).and(GET).and(TEXT).locate(this);
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

    public final void get(String url) {
        browser.get(url);
    }
}