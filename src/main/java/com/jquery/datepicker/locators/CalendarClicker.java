package com.jquery.datepicker.locators;


import com.algocrafts.converters.FrameLocator;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.converters.ElementFunctions.CLICK;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.ClassName.*;
import static com.algocrafts.selectors.Id.DATE_PICKER;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;

public enum CalendarClicker implements Locator<Page, Void> {
    TRIGGER(
            new FrameLocator(0)
                    .andThen(element(DATE_PICKER)).andThen(CLICK)
    ),
    NEXT_MONTH(
            Locators.<Page>element(UI_DATEPICKER_DIV)
                    .andThen(element(UI_DATEPICKER_NEXT)).andThen(CLICK)
    ),
    PREVIOUS_MONTH(
            Locators.<Page>element(UI_DATEPICKER_DIV)
                    .andThen(element(UI_DATEPICKER_PREV)).andThen(CLICK)
    ),
    NEXT_YEAR (
        (Page browser) -> {
            for (int i = 0; i < 12; i++) {
                NEXT_MONTH.locate(browser);
            }
            return null;
        }
    ),
    PREVIOUS_YEAR (
       (Page browser) -> {
            for (int i = 0; i < 12; i++) {
                PREVIOUS_MONTH.locate(browser);
            }
           return null;
        }
    ),
    CLOSE_BUTTON(
            Locators.<Page>element(UI_DATEPICKER_DIV)
                    .andThen(element(UI_DATEPICKER_CLOSE)).andThen(CLICK)
    );

    private final Locator<Page, Void> locator;

    CalendarClicker(Locator<Page, Void> locator) {
        this.locator = locator;
    }

    @Override
    public Void locate(Page page) {
        return locator.locate(page);
    }
}
