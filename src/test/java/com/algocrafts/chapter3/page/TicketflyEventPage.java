package com.algocrafts.chapter3.page;

import com.algocrafts.selenium.Browser;

public class TicketflyEventPage extends Page implements ChangeLocation {

    public TicketflyEventPage(Browser<?> browser) {
        super(browser, "http://www.ticketfly.com/events");
    }

}
