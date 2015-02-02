package com.algocrafts.datepicker;


import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

import java.util.Optional;
import java.util.function.Predicate;

import static com.algocrafts.datepicker.CalendarFlipper.MONTH_FLIPPER;
import static com.algocrafts.datepicker.CalendarFlipper.YEAR_FLIPPER;

public class Calendar {

    private final Page page;
    private final Locator<Page, Void> trigger;
    private final Locator<Page, Integer> displayedYear;
    private final Locator<Page, Integer> displayedMonth;
    private final Locator<Page, Void> previousMonth;
    private final Locator<Page, Void> nextMonth;
    private final DayLocatorFactory dayLocatorFactory;
    private final Predicate<Page> calendarClosed;
    private Optional<Locator<Page, Void>> closeButton = Optional.empty();
    private Optional<Locator<Page, Void>> previousYear = Optional.empty();
    private Optional<Locator<Page, Void>> nextYear = Optional.empty();


    /**
     * Constructor of the Calendar, an active page and locators
     * of the trigger element and other calendar control buttons.
     *
     * @param page              page
     * @param trigger           locator to trigger the display of the calendar
     * @param displayedYear     displayedValue year
     * @param displayedMonth    displayedValue month
     * @param previousMonth     previous month
     * @param nextMonth         next month
     * @param dayLocatorFactory day
     * @param calendarClosed    whether calendar is closed
     */
    Calendar(Page page,
                    Locator<Page, Void> trigger,
                    Locator<Page, Integer> displayedYear,
                    Locator<Page, Integer> displayedMonth,
                    Locator<Page, Void> previousMonth,
                    Locator<Page, Void> nextMonth,
                    DayLocatorFactory dayLocatorFactory,
                    Predicate<Page> calendarClosed) {
        this.page = page;
        this.trigger = trigger;
        this.displayedYear = displayedYear;
        this.displayedMonth = displayedMonth;
        this.previousMonth = previousMonth;
        this.nextMonth = nextMonth;
        this.dayLocatorFactory = dayLocatorFactory;
        this.calendarClosed = calendarClosed;
    }

    /**
     * Set optional close button
     *
     * @param closeButton locator for close button
     */
    public void setCloseButton(Locator<Page, Void> closeButton) {
        this.closeButton = Optional.of(closeButton);
    }

    /**
     * Set optional previous year button
     *
     * @param previousYear locator for previous year button
     */
    public void setPreviousYear(Locator<Page, Void> previousYear) {
        this.previousYear = Optional.of(previousYear);
    }

    /**
     * Set optional next year button
     *
     * @param nextYear locator for next year button
     */
    public void setNextYear(Locator<Page, Void> nextYear) {
        this.nextYear = Optional.of(nextYear);
    }

    /**
     * Pop up the date picker calendar.
     */
    public void show() {
        trigger.locate(page);
    }

    /**
     * Read the displayedValue year from the calendar.
     *
     * @return displayedValue year
     */
    public int displayedYear() {
        return displayedYear.locate(page);
    }

    /**
     * Read the displayedValue month from the calendar.
     *
     * @return displayedValue month
     */
    public int displayedMonth() {
        return displayedMonth.locate(page);
    }

    /**
     * Pick the day from the calendar.
     *
     * @param day day
     */
    public void pickDay(int day) {
        dayLocatorFactory.forDay(day).locate(page);
        if (closeButton.isPresent()) {
            closeButton.get().locate(page);
        }
        page.until(calendarClosed);
    }

    /**
     * Some calendar allows user to select a year from a dropdown(select) or
     * enter a value from an input field. This method is to cater that function.
     *
     * @param year year
     */
    public void pickYear(int year) {
        YEAR_FLIPPER.flip(this, year);
    }

    /**
     * Some calendar allows user to select a month from a dropdown(select) or
     * enter a value from an input field. This method is to cater that function.
     *
     * @param month month
     */
    public void pickMonth(Enum month) {
        MONTH_FLIPPER.flip(this, month.ordinal());
    }


    /**
     * Click the previous month button.
     */
    void previousMonth() {
        previousMonth.locate(page);
    }

    /**
     * Click the next month button.
     */
    void nextMonth() {
        nextMonth.locate(page);
    }

    /**
     * Clicking previous year button once, or clicking the previous month
     * button 12 times if the next year button is not present on the calendar.
     */
    void previousYear() {
        if (previousYear.isPresent()) {
            displayedYear.locate(page);
        } else {
            for (int i = 0; i < 12; i++) {
                previousMonth();
            }
        }
    }

    /**
     * Clicking next year button once, or clicking the next month button
     * 12 times if the next year button is not present on the calendar.
     */
    void nextYear() {
        if (nextYear.isPresent()) {
            nextYear.get().locate(page);
        } else {
            for (int i = 0; i < 12; i++) {
                nextMonth();
            }
        }

    }

}
