package com.jquery;


import com.algocrafts.pages.Locators;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Locator;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.Ordinal.ORDINAL;
import static com.algocrafts.converters.StringToInt.PARSE_INT;
import static com.algocrafts.converters.StringToMonth.TO_MONTH;
import static com.algocrafts.selectors.ClassName.*;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;

public enum CalendarIntegerLocator implements Locator<AbstractPage, Integer> {

    CURRENT_YEAR(
            Locators.<AbstractPage>element(UI_DATEPICKER_DIV)
                    .and(Locators.element(UI_DATEPICKER_HEADER))
                    .and(Locators.element(UI_DATEPICKER_YEAR))
                    .and(TEXT)
                    .and(PARSE_INT)
    ),
    CURRENT_MONTH(
            Locators.<AbstractPage>element(UI_DATEPICKER_DIV)
                    .and(Locators.element(UI_DATEPICKER_MONTH))
                    .and(TEXT)
                    .and(TO_MONTH)
                    .and(ORDINAL)
    );

    private Locator<AbstractPage, Integer> locator;

    private CalendarIntegerLocator(Locator<AbstractPage, Integer> locator) {
        this.locator = locator;
    }

    @Override
    public Integer locate(AbstractPage page) {
        return locator.locate(page);
    }
}
