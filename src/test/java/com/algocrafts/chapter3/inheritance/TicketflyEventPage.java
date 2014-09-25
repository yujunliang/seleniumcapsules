package com.algocrafts.chapter3.inheritance;

import com.algocrafts.selenium.Browser;

public class TicketflyEventPage extends BaseTicketflyPage {

    public TicketflyEventPage(Browser<?> browser) {
        super(browser, "http://www.ticketfly.com/events");
    }

}
