package com.jquery;


import com.algocrafts.calendar.Calendar;
import com.algocrafts.calendar.Datepicker;
import com.algocrafts.clickables.Url;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;

import java.time.Month;

import static com.algocrafts.conditions.PagePredicates.JQUERY_CALENDAR_NOT_DISPLAYED;
import static com.algocrafts.conditions.PagePredicates.REACHED_CALENDAR_PAGE;
import static com.algocrafts.converters.GetText.VALUE;
import static com.algocrafts.selectors.Id.DATE_PICKER;
import static com.jquery.CalendarClicker.*;
import static com.jquery.CalendarIntegerLocator.DISPLAYED_MONTH;
import static com.jquery.CalendarIntegerLocator.DISPLAYED_YEAR;
import static com.jquery.DayLocatorFactorys.JQUERY_DAY;

public class JQueryDatePickerPage extends Page {

    public JQueryDatePickerPage(Browser browser) {
        super(browser, new Url<>(browser, "http://jqueryui.com/datepicker/"), REACHED_CALENDAR_PAGE);
    }

    private final Datepicker datepicker = new Datepicker(
            new Calendar(this, TRIGGER, DISPLAYED_YEAR, DISPLAYED_MONTH, PREVIOUS_MONTH, NEXT_MONTH, JQUERY_DAY, JQUERY_CALENDAR_NOT_DISPLAYED)
    );

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return Locators.<Page>element(DATE_PICKER)
                .andThen(VALUE)
                .locate(this);
    }

}
