package com.algocrafts;

import com.algocrafts.forms.RadioButton;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selectors.Name;
import com.ticketfly.TicketflyHomePage;
import org.junit.Test;

import static com.algocrafts.browsers.Browsers.CHROME;
import static com.algocrafts.selectors.LinkText.ALL_CANADA;
import static com.algocrafts.selectors.LinkText.CANADA;
import static com.algocrafts.selectors.Name.FILTER_EVENT;

public class TicketflyTest {

    @Test
    public void changeLocation() {
        TicketflyHomePage page = new TicketflyHomePage(CHROME);
        page.open();
        page.changeLocation(CANADA, ALL_CANADA);
    }

    @Test
    public void discoverMoreEvent() {
        TicketflyHomePage page = new TicketflyHomePage(CHROME);
        page.open();
        page.discoverMoreEvent();

        RadioButton<AbstractPage> filter = new RadioButton<>(page, FILTER_EVENT);
        System.out.println(filter.getValue());
        filter.setValue("Just Announced");
    }
}
