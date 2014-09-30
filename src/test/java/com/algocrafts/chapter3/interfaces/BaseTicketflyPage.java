package com.algocrafts.chapter3.interfaces;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;

public class BaseTicketflyPage implements SearchScope<Page> {
    private final Browser browser;
    private final String url;

    public BaseTicketflyPage(Browser<?> browser, String url) {
        this.browser = browser;
        this.url = url;
    }

    public void open() {
        browser.get(url);
    }

    @Override
    public final void onTimeout() {
        browser.save(browser.getTitle());

    }

}
