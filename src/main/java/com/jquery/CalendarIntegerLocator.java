package com.jquery;


import com.algocrafts.selenium.Locators;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.Ordinal.ORDINAL;
import static com.algocrafts.converters.StringToInt.PARSE_INT;
import static com.algocrafts.converters.StringToMonth.TO_MONTH;
import static com.algocrafts.selenium.Locators.element;
import static com.algocrafts.selectors.ClassName.*;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;

public enum CalendarIntegerLocator implements Locator<AbstractPage, Integer> {

    /**
     * Locate the integer value representing current year on a calendar
     */
    CURRENT_YEAR(
            Locators.<AbstractPage>element(UI_DATEPICKER_DIV)
                    .and(element(UI_DATEPICKER_HEADER))
                    .and(element(UI_DATEPICKER_YEAR))
                    .and(TEXT)
                    .and(PARSE_INT)
    ),

    /**
     * Locate the integer value representing current year on a calendar
     */
    CURRENT_MONTH(
            Locators.<AbstractPage>element(UI_DATEPICKER_DIV)
                    .and(element(UI_DATEPICKER_MONTH))
                    .and(TEXT)
                    .and(TO_MONTH)
                    .and(ORDINAL)
    );

    private final Locator<AbstractPage, Integer> locator;

    private CalendarIntegerLocator(Locator<AbstractPage, Integer> locator) {
        this.locator = locator;
    }

    @Override
    public Integer locate(AbstractPage page) {
        return locator.locate(page);
    }
}
