package com.algocrafts.cucumber;

import com.algocrafts.browsers.Browsers;
import com.algocrafts.selectors.LinkText;
import com.ticketfly.TicketflyPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.junit.Assert.assertEquals;

public class ChangeLocationStep {

    TicketflyPage page;

    @Given("^I am on TicketFly home page using (.+)$")
    public void given(String browser) {
        page = new TicketflyPage(Browsers.valueOf(browser));
        page.open();
    }

    @When("^I change the location to (.+) then (.+)$")
    public void changeLocation(String country, String region) {
        page.changeLocation(LinkText.valueOf(country), LinkText.valueOf(region));
    }

    @Then("^the default location is (.+)$")
    public void verifyLocation(String location) {
        assertEquals(location, page.currentLocation());
        page.close();
    }
}
