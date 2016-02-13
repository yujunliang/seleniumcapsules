package com.algocrafts.datepicker;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

public class CalendarPicker {

    private final Page browser;  //<1>
    private final Locator<Page, Void> previous;      //<2>
    private final Locator<Page, Void> next;          //<3>
    private final Locator<Page, Integer> displayedValue; //<4>

    public CalendarPicker(Page browser,
                          Locator<Page, Void> previous,
                          Locator<Page, Void> next,
                          Locator<Page, Integer> displayedValue) {    //<5>
        this.browser = browser;
        this.previous = previous;
        this.next = next;
        this.displayedValue = displayedValue;
    }

    /**
     *
     * @param flipTo
     */
    void pick(int flipTo) {
        int difference = displayedValue.apply(browser) - flipTo;
        if (difference < 0) {
            for (int i = difference; i < 0; i++) {
                next.apply(browser);
            }
        } else if (difference > 0) {
            for (int i = 0; i < difference; i++) {
                previous.apply(browser);
            }
        }
    }
}
