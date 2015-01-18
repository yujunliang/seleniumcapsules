package com.extjs;

import com.algocrafts.datepicker.DayLocatorFactory;
import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.converters.ElementFunctions.CLICK;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.Id.EXTJS_CALENDAR;
import static com.algocrafts.selectors.TagName.TD;

public enum DayLocatorFactorys implements DayLocatorFactory {

    EXTJS_DAY_LOCATOR {
        public Locator<Page, Void> forDay(int day) {
            return Locators.<Page>element(EXTJS_CALENDAR)
                    .andthen(elements(TD))
                    .andthen(new FirstMatch<>(TEXT.and(new Equals(day))))
                    .andthen(GET)
                    .andthen(CLICK);
        }
    }
}
