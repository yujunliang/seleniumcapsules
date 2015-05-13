package com.algocrafts.cucumber;

import com.algocrafts.browsers.Browsers;
import com.jquery.datepicker.JQueryDatePickerPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.algocrafts.converters.StringToMonth.TO_MONTH;
import static org.junit.Assert.assertEquals;

public class SelectCalendarStep {

    JQueryDatePickerPage page;

    @Given("^I am on jQuery Calendar page using (.+)$")
    public void given(String browser) {
        Browsers browser1 = Browsers.valueOf(browser);
        page = new JQueryDatePickerPage(browser1);
        page.open();
    }

    @When("^I pick (.+), (\\d+), (\\d+) from a datepicker$")
    public void pick(String month, int day, int year) {
        page.pick(TO_MONTH.locate(month), day, year);
    }

    @Then("^datepicker result is (.+)$")
    public void verify(String date) {
        assertEquals(date, page.getDate());
    }

    @Then("^I close browser$")
    public void close() {
        page.quit();
    }

}
