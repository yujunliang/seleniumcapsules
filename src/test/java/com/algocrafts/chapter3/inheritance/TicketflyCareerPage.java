package com.algocrafts.chapter3.inheritance;

import com.algocrafts.selenium.Browser;

public class TicketflyCareerPage extends BaseTicketflyPage {

    public TicketflyCareerPage(Browser<?> browser) {
        super(browser, "http://www.ticketfly.com/careers");
    }

}
