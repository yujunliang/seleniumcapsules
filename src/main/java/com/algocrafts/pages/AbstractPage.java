package com.algocrafts.pages;

import com.algocrafts.conditions.IsStringEqual;
import com.algocrafts.forms.FormControl;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.PageFunctions.THE_PAGE_TITLE;
import static com.algocrafts.selectors.ClassName.PAGE_TITLE;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractPage implements Searchable<AbstractPage>, FormControl<AbstractPage> {

    public static final Logger logger = getLogger(AbstractPage.class);

    private final Predicate<AbstractPage> condition;

    @Value("${quit.browser}")
    private boolean close;

    private final Browser browser;

    private final Clickable clickable;

    public AbstractPage(AbstractPage page) {
        this(page.browser, null, null, page.close);
    }

    public AbstractPage(AbstractPage page, Clickable clickable, String title) {
        this(page.browser, clickable, THE_PAGE_TITLE.and(new IsStringEqual(title)), page.close);
    }

    public AbstractPage(AbstractPage page, Clickable clickable, Predicate<AbstractPage> condition) {
        this(page.browser, clickable, condition, page.close);
    }

    public AbstractPage(Browser browser, Clickable clickable, Predicate<AbstractPage> condition) {
        this.browser = browser;
        this.clickable = clickable;
        this.condition = condition;
    }

    public AbstractPage(Browser browser, Clickable clickable, Predicate<AbstractPage> condition, boolean close) {
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

    /**
     * Find the first element or return null if nothing found.
     *
     * @param by selector
     * @return the first element or return null if nothing found.
     */
    @Override
    public final Element findElement(By by) {
        try {
            return browser.findElement(by);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * Find the first element or throw NoSuchElementException
     *
     * @param by selector
     * @return the first element or throw NoSuchElementException
     */
    @Override
    public final Element untilFound(final By by) {
        return until((AbstractPage page) -> browser.findElement(by));
    }

    @Override
    @SuppressWarnings("unchecked")
    public final Stream<Element> findElements(Supplier<By> by) {
        return browser.findElements(by);
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

    public String getTitle() {
        try {
            return Locators.<AbstractPage>tryElement(PAGE_TITLE).and(TEXT).locate(this);
        } catch (Exception e) {
            return "";
        }
    }

    public final AbstractPage frame(int i) {
        browser.frame(i);
        return this;
    }

    public final AbstractPage defaultContent() {
        browser.defaultContent();
        return this;
    }

    public final void save() {
        if (logger.isDebugEnabled()) {
            browser.save(this.getTitle());
        }
    }

    public final void get(String url) {
        browser.get(url);
    }

}