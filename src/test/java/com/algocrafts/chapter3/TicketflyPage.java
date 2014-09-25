package com.algocrafts.chapter3;

import com.algocrafts.selectors.LinkText;
import com.algocrafts.selectors.Xpath;
import com.algocrafts.selenium.Browser;

import java.util.stream.Stream;

public class TicketflyPage {
    private final Browser browser;
    private final String url = "http://www.ticketfly.com";

    public TicketflyPage(Browser<?> browser) {
        this.browser = browser;
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
