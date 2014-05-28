package com.algocrafts.calendar;


import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.calendar.FlippingButton.MONTH_FLIPPER;
import static com.algocrafts.calendar.FlippingButton.YEAR_FLIPPER;

public class Calendar {

    private final AbstractPage page;
    private final Locator<AbstractPage, Void> trigger;
    private final Locator<AbstractPage, Integer> currentYear;
    private final Locator<AbstractPage, Integer> currentMonth;
    private final Locator<AbstractPage, Void> previousMonth;
    private final Locator<AbstractPage, Void> nextMonth;
    private final DayLocatorFactory dayLocator;

    /**
     * Constructor of the Calendar, an active page and locators
     * of the trigger element and other calendar control buttons.
     *
     * @param page
     * @param trigger
     * @param currentYear
     * @param currentMonth
     * @param previousMonth
     * @param nextMonth
     * @param dayLocator
     */
    public Calendar(AbstractPage page,
                    Locator<AbstractPage, Void> trigger,
                    Locator<AbstractPage, Integer> currentYear,
                    Locator<AbstractPage, Integer> currentMonth,
                    Locator<AbstractPage, Void> previousMonth,
                    Locator<AbstractPage, Void> nextMonth,
                    DayLocatorFactory dayLocator) {
        this.page = page;
        this.trigger = trigger;
        this.currentYear = currentYear;
        this.currentMonth = currentMonth;
        this.previousMonth = previousMonth;
        this.nextMonth = nextMonth;
        this.dayLocator = dayLocator;
    }

    /**
     * Pop up the date picker calendar.
     */
    public void show() {
        trigger.locate(page);
    }

    /**
     * Read the current year from the calendar.
     *
     * @return
     */
    public int currentYear() {
        return currentYear.locate(page);
    }

    /**
     * Read the current month from the calendar.
     *
     * @return
     */
    public int currentMonth() {
        return currentMonth.locate(page);
    }

    /**
     * Click the previous month button.
     */
    public void previousMonth() {
        previousMonth.locate(page);
    }

    /**
     * Click the next month button.
     */
    public void nextMonth() {
        nextMonth.locate(page);
    }

    /**
     * Pick the day from the calendar.
     *
     * @param day
     */
    public void pickDay(int day) {
        dayLocator.forDay(day).locate(page);
    }

    /**
     * Some calendar allows user to select a year from a dropdown(select) or
     * enter a value from an input field. This method is to cater that function.
     *
     * @param year
     */
    public void enterYear(int year) {
        YEAR_FLIPPER.flip(this, year);
    }

    /**
     * Some calendar allows user to select a month from a dropdown(select) or
     * enter a value from an input field. This method is to cater that function.
     *
     * @param month
     */
    public void enterMonth(Enum month) {
        MONTH_FLIPPER.flip(this, month.ordinal());
    }

    /**
     * Clicking previous year button once, or clicking the previous month
     * button 12 times if the next year button is not present on the calendar.
     */
    public void previousYear() {
        for (int i = 0; i < 12; i++) {
            previousMonth();
        }
    }

    /**
     * Clicking next year button once, or clicking the next month button
     * 12 times if the next year button is not present on the calendar.
     */
    public void nextYear() {
        for (int i = 0; i < 12; i++) {
            nextMonth();
        }
    }
}
