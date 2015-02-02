package com.jquery.datepicker;

import com.algocrafts.datepicker.DayLocatorFactory;
import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.converters.ElementFunctions.CLICK;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.ClassName.UI_DATEPICKER_CALENDAR;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;
import static com.algocrafts.selectors.TagName.TD;

public enum JQueryDayLocatorFactory implements DayLocatorFactory {

    JQUERY_DAY {
        public Locator<Page, Void> forDay(int day) {
            return Locators.<Page>element(UI_DATEPICKER_DIV)
                    .andThen(element(UI_DATEPICKER_CALENDAR))
                    .andThen(elements(TD))
                    .andThen(new FirstMatch<>(TEXT.and(new Equals(day))))
                    .andThen(GET)
                    .andThen(CLICK);
        }
    }
}
