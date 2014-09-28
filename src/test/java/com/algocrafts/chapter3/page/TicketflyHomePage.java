package com.algocrafts.chapter3.page;

import com.algocrafts.selenium.Browser;

public class TicketflyHomePage extends Page implements ChangeLocation {

    public TicketflyHomePage(Browser<?> browser) {
        super(browser, "http://www.ticketfly.com");
    }

}
