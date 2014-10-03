package com.jquery.datepicker.locators;


import com.algocrafts.converters.FrameLocator;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import org.openqa.selenium.By;

import static com.algocrafts.converters.ElementFunctions.CLICK;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.ClassName.*;
import static com.algocrafts.selectors.Id.DATE_PICKER;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;

public enum OldCalendarClicker implements Locator<Page, Void> {
    TRIGGER(
            (Page page) ->
                    page.frame(0)
                            .untilFound(() -> By.id("datepicker"))
    ),

    NEXT_MONTH(
            (Page page) ->
                    page.untilFound(() -> By.id("ui-datepicker-div"))
                            .untilFound(() -> By.className("ui-datepicker-next"))


    );


    private final Locator<Page, Element> locator;

    private OldCalendarClicker(Locator<Page, Element> locator) {
        this.locator = locator;
    }

    @Override
    public Void locate(Page page) {
        locator.locate(page).click();
        return null;
    }
}
