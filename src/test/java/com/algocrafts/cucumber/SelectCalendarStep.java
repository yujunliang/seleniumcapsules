package com.algocrafts.cucumber;

import com.algocrafts.browsers.Browsers;
import com.jquery.datepicker.JQueryDatePickerPage;
import cucumber.api.java8.En;

import static com.algocrafts.converters.StringToMonth.TO_MONTH;
import static org.junit.Assert.assertEquals;

public class SelectCalendarStep implements En {

    JQueryDatePickerPage page;

    public SelectCalendarStep() {
        Given("^I am on jQuery Calendar page using (.+)$", (String browser) -> {
            System.out.println("Running....");
            Browsers browser1 = Browsers.valueOf(browser);
            page = new JQueryDatePickerPage(browser1);
            page.open();
        });

        When("^I pick (.+), (\\d+), (\\d+) from a datepicker$", (String month, Integer day, Integer year) -> {
            page.pick(TO_MONTH.locate(month), day, year);
        });

        Then("^datepicker result is (.+)$", (String date) -> {
            assertEquals(date, page.getDate());
        });

        And("^I close browser$", () -> {
            page.quit();
        });

    }

}
