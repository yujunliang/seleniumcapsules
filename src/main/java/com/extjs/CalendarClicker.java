package com.extjs;


import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.Id;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.converters.ElementFunctions.CLICK;
import static com.algocrafts.locators.Locators.element;

public enum CalendarClicker implements Locator<Page, Void> {
    NEXT_MONTH(
            Locators.<Page>element(Id.EXTJS_CALENDAR)
                    .and(element(Id.EXTJS_CALENDAR_NEXT_MONTH))
    ),
    PREVIOUS_MONTH(
            Locators.<Page>element(Id.EXTJS_CALENDAR)
                    .and(element(Id.EXTJS_CALENDAR_PREV_MONTH))
    );

    private final Locator<Page, Element> locator;

    private CalendarClicker(Locator<Page, Element> locator) {
        this.locator = locator;
    }

    @Override
    public Void locate(Page page) {
        return locator.and(CLICK).locate(page);
    }
}
