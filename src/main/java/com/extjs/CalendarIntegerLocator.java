package com.extjs;


import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.Ordinal.ORDINAL;
import static com.algocrafts.converters.StringConverter.FIRST_HALF;
import static com.algocrafts.converters.StringConverter.SECOND_HALF;
import static com.algocrafts.converters.StringToInt.PARSE_INT;
import static com.algocrafts.converters.StringToMonth.TO_MONTH;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.Id.EXTJS_CALENDAR;
import static com.algocrafts.selectors.Id.EXTJS_CALENDAR_MONTH_YEAR;

public enum CalendarIntegerLocator implements Locator<Page, Integer> {

    /**
     * Locate the integer value representing displayed year on a calendar
     */
    DISPLAYED_YEAR(
            Locators.<Page>element(EXTJS_CALENDAR)
                    .andNext(element(EXTJS_CALENDAR_MONTH_YEAR))
                    .andNext(TEXT)
                    .andNext(SECOND_HALF)
                    .andNext(PARSE_INT)
    ),

    /**
     * Locate the integer value representing displayed month on a calendar
     */
    DISPLAYED_MONTH(
            Locators.<Page>element(EXTJS_CALENDAR)
                    .andNext(element(EXTJS_CALENDAR_MONTH_YEAR))
                    .andNext(TEXT)
                    .andNext(FIRST_HALF)
                    .andNext(TO_MONTH)
                    .andNext(ORDINAL)
    );

    private final Locator<Page, Integer> locator;

    private CalendarIntegerLocator(Locator<Page, Integer> locator) {
        this.locator = locator;
    }

    @Override
    public Integer locate(Page page) {
        return locator.locate(page);
    }
}
