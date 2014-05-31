package com.jquery;


import com.algocrafts.calendar.Calendar;
import com.algocrafts.calendar.DatePicker;
import com.algocrafts.pages.Locators;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Browsers;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;

import static com.algocrafts.conditions.PagePredicates.REACHED_CALENDAR_PAGE;
import static com.algocrafts.converters.GetText.VALUE;
import static com.algocrafts.selectors.Id.DATE_PICKER;
import static com.jquery.CalendarIntegerLocator.CURRENT_MONTH;
import static com.jquery.CalendarIntegerLocator.CURRENT_YEAR;
import static com.jquery.CalendarVoidLocator.*;

public class JQueryDatePickerPage extends AbstractPage {

    public JQueryDatePickerPage(Browser browser, Clickable clickable) {
        super(browser, clickable, REACHED_CALENDAR_PAGE);
    }

    private final DatePicker datepicker = new DatePicker(
            new Calendar(this, TRIGGER, CURRENT_YEAR, CURRENT_MONTH, PREVIOUS_MONTH, NEXT_MONTH, new JQueryDayLocatorFactory())
    );

    public void pick(Enum month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return Locators.<AbstractPage>element(DATE_PICKER)
                .and(VALUE)
                .locate(this);
    }

}
