package com.algocrafts.chapter3;

import org.junit.Ignore;
import org.junit.Test;

import com.ticketfly.TicketflyPage;
import static com.algocrafts.browsers.Browsers.CHROME;
import static com.algocrafts.selectors.Name.FILTER_EVENT;

@Ignore
public class DiscoverMoreEventTest {

    @Test
    public void discoverMoreEvent() {
        TicketflyPage page = new TicketflyPage(CHROME);
        page.open();
        page.discoverMoreEvent();
        page.setRadioButton(FILTER_EVENT, "Just Announced");
    }

    @Test
    public void discoverMoreEventAnonymousClass() {
        new TicketflyPage(CHROME) {{
            open();
            discoverMoreEvent();
            setRadioButton(FILTER_EVENT, "Just Announced");
        }};
    }
}
