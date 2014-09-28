package com.algocrafts.chapter3.page;

import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Page implements SearchScope<Page> {

    private final Browser<?> browser;
    private final String url;

    public Page(Page page, String url) {
        this(page.browser, url);
    }

    public Page(Browser<?> browser, String url) {
        this.browser = browser;
        this.url = url;
    }

    public final void open() {
        browser.get(url);
    }

    @Override
    public List<WebElement> findElements(By by) {
        return browser.findElements(by);
    }

    @Override
    public final Element findElement(final By by) {
        return browser.findElement(by);
    }

    @Override
    public final void onTimeout() {
        browser.save(browser.getTitle());

    }
}
