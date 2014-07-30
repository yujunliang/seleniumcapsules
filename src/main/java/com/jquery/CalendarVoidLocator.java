package com.jquery;


import com.algocrafts.converters.FrameLocator;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.converters.ElementFunctions.CLICK;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.ClassName.*;
import static com.algocrafts.selectors.Id.DATE_PICKER;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;

public enum CalendarVoidLocator implements Locator<Page, Void> {
    TRIGGER(
            new FrameLocator(0)
                    .and(element(DATE_PICKER))
    ),
    NEXT_MONTH(
            Locators.<Page>element(UI_DATEPICKER_DIV)
                    .and(element(UI_DATEPICKER_NEXT))
    ),
    PREVIOUS_MONTH(
            Locators.<Page>element(UI_DATEPICKER_DIV)
                    .and(element(UI_DATEPICKER_PREV))
    ),
    CLOSE_BUTTON(
            Locators.<Page>element(UI_DATEPICKER_DIV)
                    .and(Locators.optionalElement(UI_DATEPICKER_CLOSE)).and(GET)
    );

    private final Locator<Page, Element> locator;

    private CalendarVoidLocator(Locator<Page, Element> locator) {
        this.locator = locator;
    }

    @Override
    public Void locate(Page page) {
        return locator.and(CLICK).locate(page);
    }
}
