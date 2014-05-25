package com.jquery;


import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Element;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.locators.ElementTryLocator;
import com.algocrafts.locators.FrameLocator;
import com.algocrafts.pages.Locator;

import static com.algocrafts.converters.ElementFunctions.CLICK_IF_NOT_NULL;
import static com.algocrafts.searchmethods.ClassName.*;
import static com.algocrafts.searchmethods.Id.DATE_PICKER;
import static com.algocrafts.searchmethods.Id.UI_DATEPICKER_DIV;

public enum CalendarVoidLocator implements Locator<AbstractPage, Void> {
    TRIGGER(
        new FrameLocator(0)
            .and(new ElementLocator<>(DATE_PICKER))
    ),
    NEXT_MONTH(
        new ElementLocator<AbstractPage>(UI_DATEPICKER_DIV)
            .and(new ElementLocator<>(UI_DATEPICKER_NEXT))
    ),
    PREVIOUS_MONTH(
        new ElementLocator<AbstractPage>(UI_DATEPICKER_DIV)
            .and(new ElementLocator<>(UI_DATEPICKER_PREV))
    ),
    CLOSE_BUTTON(
        new ElementLocator<AbstractPage>(UI_DATEPICKER_DIV)
            .and(new ElementTryLocator<>(UI_DATEPICKER_CLOSE))
    );

    private Locator<AbstractPage, Element> locator;

    private CalendarVoidLocator(Locator<AbstractPage, Element> locator) {
        this.locator = locator;
    }

    @Override
    public Void apply(AbstractPage page) {
        return locator.and(CLICK_IF_NOT_NULL).apply(page);
    }
}
