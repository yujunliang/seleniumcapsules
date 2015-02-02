package com.algocrafts.datepicker;


import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

import java.util.Optional;
import java.util.function.Predicate;

import static com.algocrafts.datepicker.CalendarFlipper.MONTH_FLIPPER;
import static com.algocrafts.datepicker.CalendarFlipper.YEAR_FLIPPER;

public class CalendarBuilder {

    private Page page;
    private Locator<Page, Void> trigger;
    private Locator<Page, Integer> displayedYear;
    private Locator<Page, Integer> displayedMonth;
    private Locator<Page, Void> previousMonth;
    private Locator<Page, Void> nextMonth;
    private DayLocatorFactory dayLocatorFactory;
    private Predicate<Page> calendarClosed;


    public CalendarBuilder setPage(Page page) {
        this.page = page;
        return this;
    }

    public CalendarBuilder setTrigger(Locator<Page, Void> trigger) {
        this.trigger = trigger;
        return this;
    }

    public CalendarBuilder setDisplayedYear(Locator<Page, Integer> displayedYear) {
        this.displayedYear = displayedYear;
        return this;
    }

    public CalendarBuilder setDisplayedMonth(Locator<Page, Integer> displayedMonth) {
        this.displayedMonth = displayedMonth;
        return this;
    }

    public CalendarBuilder setPreviousMonth(Locator<Page, Void> previousMonth) {
        this.previousMonth = previousMonth;
        return this;
    }

    public CalendarBuilder setNextMonth(Locator<Page, Void> nextMonth) {
        this.nextMonth = nextMonth;
        return this;
    }

    public CalendarBuilder setDayLocatorFactory(DayLocatorFactory dayLocatorFactory) {
        this.dayLocatorFactory = dayLocatorFactory;
        return this;
    }

    public CalendarBuilder setCalendarClosed(Predicate<Page> calendarClosed) {
        this.calendarClosed = calendarClosed;
        return this;
    }

    public Calendar build() {
        return new Calendar(page, trigger,displayedYear,displayedMonth,previousMonth,nextMonth,dayLocatorFactory,calendarClosed);
    }

    
}
