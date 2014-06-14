package com.ticketfly;


import com.algocrafts.clickables.Url;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selectors.LinkText;
import com.algocrafts.selenium.Browser;

import static com.algocrafts.selectors.LinkText.CHANGE_LOCATION;
import static com.algocrafts.selectors.LinkText.DISCOVER_MORE_EVENT;

public class TicketflyHomePage extends AbstractPage {

    public TicketflyHomePage(Browser<?> browser) {
        super(browser, new Url<>(browser, "http://www.ticketfly.com"), null);
    }

    public void changeLocation(LinkText first, LinkText second) {
        link(CHANGE_LOCATION).click();
        link(first).click();
        link(second).click();
    }

    public void discoverMoreEvent() {
        link(DISCOVER_MORE_EVENT).click();
    }

}
