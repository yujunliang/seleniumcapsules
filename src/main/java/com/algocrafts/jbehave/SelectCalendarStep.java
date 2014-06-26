package com.algocrafts.jbehave;

import com.algocrafts.browsers.Browsers;
import com.algocrafts.clickables.Url;
import com.jquery.JQueryDatePickerPage;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static com.algocrafts.converters.StringToMonth.TO_MONTH;
import static org.junit.Assert.assertEquals;

public class SelectCalendarStep {


    JQueryDatePickerPage page;

    @Given("I am on jQuery Calendar page using <browser>")
    public void given(String browser) {
        Browsers browser1 = Browsers.valueOf(browser);
        page = new JQueryDatePickerPage(browser1, new Url<>(browser1, "http://jqueryui.com/datepicker/"));
        page.open();
    }

    @When("I pick <month>, <day>, <year> from a datepicker")
    public void pick(String month, int day, int year) {
        page.pick(TO_MONTH.locate(month), day, year);
    }

    @Then("datepicker result is <date>")
    public void verify(String date) {
        assertEquals(date, page.getDate());
    }

    @Then("I close browser")
    public void close() {
        page.quit();
    }
}
