package com.extjs;


import com.algocrafts.datepicker.Calendar;
import com.algocrafts.datepicker.Datepicker;
import com.algocrafts.clickables.Url;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import org.openqa.selenium.By;

import java.time.Month;

import static com.algocrafts.conditions.PagePredicates.EXTJS_CALENDAR_NOT_DISPLAYED;
import static com.algocrafts.converters.GetText.TEXT;
import static com.extjs.CalendarClicker.NEXT_MONTH;
import static com.extjs.CalendarClicker.PREVIOUS_MONTH;
import static com.extjs.CalendarIntegerLocator.DISPLAYED_MONTH;
import static com.extjs.CalendarIntegerLocator.DISPLAYED_YEAR;
import static com.extjs.DayLocatorFactorys.EXTJS_DAY_LOCATOR;
import static com.extjs.TriggerHover.TRIGGER;

public class ExtJSDatePickerPage extends Page {

    public ExtJSDatePickerPage(Browser browser) {
        super(browser, new Url<>(browser, "http://try.sencha.com/extjs/4.0.7/docs/Ext.menu.DatePicker.1/"), true);
    }

    private final Datepicker datepicker = new Datepicker(
            new Calendar(this, TRIGGER, DISPLAYED_YEAR, DISPLAYED_MONTH, PREVIOUS_MONTH, NEXT_MONTH, EXTJS_DAY_LOCATOR, EXTJS_CALENDAR_NOT_DISPLAYED)
    );

    public void pick(Month month, int day, int year) {
        datepicker.pick(month, day, year);
    }

    public String getDate() {
        return Locators.<Page>element(() -> By.id("component-1003"))
                .andThen(TEXT)
                .locate(this);
    }

}
