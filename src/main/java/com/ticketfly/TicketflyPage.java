package com.ticketfly;


import com.algocrafts.clickables.Url;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selectors.LinkText;
import com.algocrafts.selenium.Browser;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.ClassName.TOOLS_LOCATION;
import static com.algocrafts.selectors.LinkText.CHANGE_LOCATION;
import static com.algocrafts.selectors.LinkText.DISCOVER_MORE_EVENT;
import static com.algocrafts.selectors.TagName.A;
import static com.algocrafts.selectors.TagName.STRONG;

public class TicketflyPage extends AbstractPage {

    public TicketflyPage(Browser<?> browser) {
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

    public String currentLocation() {
        return Locators.<AbstractPage>element(TOOLS_LOCATION)
                .and(element(A))
                .and(element(STRONG))
                .and(TEXT)
                .locate(this);
    }
}
