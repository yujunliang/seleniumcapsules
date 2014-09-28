package com.algocrafts.chapter3.interfaces;

import com.algocrafts.selectors.LinkText;
import com.algocrafts.selectors.Xpath;
import com.algocrafts.selenium.Browser;

import java.util.stream.Stream;

public class TicketflyHomePage extends BaseTicketflyPage implements ChangeLocation {

    public TicketflyHomePage(Browser<?> browser) {
        super(browser, "http://www.ticketfly.com");
    }

}
