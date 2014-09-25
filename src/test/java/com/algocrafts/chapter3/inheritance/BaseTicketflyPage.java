package com.algocrafts.chapter3.inheritance;

import com.algocrafts.selectors.LinkText;
import com.algocrafts.selectors.Xpath;
import com.algocrafts.selenium.Browser;

import java.util.stream.Stream;

public class BaseTicketflyPage {
    private final Browser browser;
    private final String url;

    public BaseTicketflyPage(Browser<?> browser, String url) {
        this.browser = browser;
        this.url = url;
    }

    public void open() {
        browser.get(url);
    }

    public void changeLocation(LinkText first, LinkText second) {
        Stream.of(LinkText.CHANGE_LOCATION, first, second).forEach(
                linkText -> browser.untilFound(linkText).click());
    }

    public String currentLocation() {
        return browser.untilFound(Xpath.LOCATION).getText();
    }
}
