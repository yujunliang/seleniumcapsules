package com.jquery;

import com.algocrafts.calendar.DayLocatorFactory;
import com.algocrafts.conditions.IsStringEqual;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.converters.ElementFunctions.CLICK_IF_NOT_NULL;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.ClassName.UI_DATEPICKER_CALENDAR;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;
import static com.algocrafts.selectors.TagName.TD;

public class JQueryDayLocatorFactory implements DayLocatorFactory {

    public Locator<AbstractPage, Void> forDay(int day) {
        return new JQueryDayLocator(
                Locators.<AbstractPage>element(UI_DATEPICKER_DIV)
                        .and(element(UI_DATEPICKER_CALENDAR))
                        .and(elements(TD))
                        .and(new FirstMatch<>(TEXT.and(new IsStringEqual(day))))
                        .and(CLICK_IF_NOT_NULL)
        );
    }


}
