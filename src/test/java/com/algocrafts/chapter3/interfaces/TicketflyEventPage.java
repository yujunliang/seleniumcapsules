package com.algocrafts.chapter3.interfaces;

import com.algocrafts.selenium.Browser;

public class TicketflyEventPage extends BaseTicketflyPage implements ChangeLocation{

    public TicketflyEventPage(Browser<?> browser) {
        super(browser, "http://www.ticketfly.com/events");
    }

}
