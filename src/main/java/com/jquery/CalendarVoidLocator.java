package com.jquery;


import com.algocrafts.converters.FrameLocator;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locators;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.converters.ElementFunctions.CLICK_IF_NOT_NULL;
import static com.algocrafts.pages.Locators.element;
import static com.algocrafts.selectors.ClassName.*;
import static com.algocrafts.selectors.Id.DATE_PICKER;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;

public enum CalendarVoidLocator implements Locator<AbstractPage, Void> {
    TRIGGER(
            new FrameLocator(0)
                    .and(element(DATE_PICKER))
    ),
    NEXT_MONTH(
            Locators.<AbstractPage>element(UI_DATEPICKER_DIV)
                    .and(element(UI_DATEPICKER_NEXT))
    ),
    PREVIOUS_MONTH(
            Locators.<AbstractPage>element(UI_DATEPICKER_DIV)
                    .and(element(UI_DATEPICKER_PREV))
    ),
    CLOSE_BUTTON(
            Locators.<AbstractPage>element(UI_DATEPICKER_DIV)
                    .and(Locators.tryElement(UI_DATEPICKER_CLOSE))
    );

    private final Locator<AbstractPage, Element> locator;

    private CalendarVoidLocator(Locator<AbstractPage, Element> locator) {
        this.locator = locator;
    }

    @Override
    public Void locate(AbstractPage page) {
        return locator.and(CLICK_IF_NOT_NULL).locate(page);
    }
}
