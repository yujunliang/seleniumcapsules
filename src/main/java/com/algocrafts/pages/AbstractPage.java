package com.algocrafts.pages;

import com.algocrafts.conditions.IsStringEqual;
import com.algocrafts.forms.FormControl;
import com.algocrafts.locators.ElementTryLocator;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Value;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.PageFunctions.THE_PAGE_TITLE;
import static com.algocrafts.selectors.ClassName.PAGE_TITLE;

public abstract class AbstractPage implements Searchable<AbstractPage>, FormControl<AbstractPage> {

    private final Predicate<AbstractPage> condition;

    @Value("${quit.browser}")
    private boolean close;

    @Value("${take.screenshot}")
    private boolean takeScreenshot;

    private final Browser browser;

    private final Clickable clickable;

    public AbstractPage(AbstractPage page) {
        this(page.browser, null, null, page.close, page.takeScreenshot);
    }

    public AbstractPage(AbstractPage page, Clickable clickable, String title) {
        this(page.browser, clickable, THE_PAGE_TITLE.and(new IsStringEqual(title)), page.close, page.takeScreenshot);
    }

    public AbstractPage(AbstractPage page, Clickable clickable, Predicate<AbstractPage> condition) {
        this(page.browser, clickable, condition, page.close, page.takeScreenshot);
    }

    public AbstractPage(Browser browser, Clickable clickable, Predicate<AbstractPage> condition) {
        this.browser = browser;
        this.clickable = clickable;
        this.condition = condition;
    }

    public AbstractPage(Browser browser, Clickable clickable, Predicate<AbstractPage> condition, boolean close, boolean takeScreenshot) {
        this.browser = browser;
        this.clickable = clickable;
        this.condition = condition;
        this.close = close;
        this.takeScreenshot = takeScreenshot;
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
    public final Element findElement(By by) {
        try {
            return browser.findElement(by);
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public final Element untilFound(final By by) {
        return until((AbstractPage page) -> browser.findElement(by));
    }

    @Override
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
            return new ElementTryLocator<AbstractPage>(PAGE_TITLE).and(TEXT).apply(this);
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
        if (takeScreenshot) {
            browser.save(this.getTitle());
        }
    }

    public final void get(String url) {
        browser.get(url);
    }

}