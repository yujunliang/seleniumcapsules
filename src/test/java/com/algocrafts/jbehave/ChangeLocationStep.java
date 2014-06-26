package com.algocrafts.jbehave;

import com.algocrafts.browsers.Browsers;
import com.algocrafts.selectors.LinkText;
import com.ticketfly.TicketflyPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.assertEquals;

public class ChangeLocationStep {

    TicketflyPage page;

    @Given("I am on TicketFly home page using $browser")
    public void given(String browser) {
        page = new TicketflyPage(Browsers.valueOf(browser));
        page.open();
    }

    @When("I change the location to $county then $region")
    public void changeLocation(String country, String region) {
        page.changeLocation(LinkText.valueOf(country), LinkText.valueOf(region));
    }

    @Then("the default location is $location")
    public void verifyLocation(String location) {
        assertEquals(location, page.currentLocation());
    }
}
