package com.algocrafts.pages;

import com.algocrafts.conditions.IsStringEqual;
import com.algocrafts.forms.FormControl;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.List;
import java.util.function.Predicate;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.PageFunctions.THE_PAGE_TITLE;
import static com.algocrafts.selectors.ClassName.PAGE_TITLE;
import static org.slf4j.LoggerFactory.getLogger;

public class AbstractPage implements Searchable<AbstractPage>, FormControl<AbstractPage> {

    public static final Logger logger = getLogger(AbstractPage.class);

    private final Predicate<AbstractPage> condition;

    private boolean close;

    private final Browser<?> browser;

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

    public AbstractPage(Browser<?> browser, Clickable clickable, Predicate<AbstractPage> condition) {
        this(browser, clickable, condition, false);
    }

    public AbstractPage(Browser<?> browser, Clickable clickable, Predicate<AbstractPage> condition, boolean close) {
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
            return Locators.<AbstractPage>trying(PAGE_TITLE).and(TEXT).locate(this);
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