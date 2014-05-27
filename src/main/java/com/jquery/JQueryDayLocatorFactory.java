package com.jquery;

import com.algocrafts.calendar.DayLocatorFactory;
import com.algocrafts.conditions.IsStringEqual;
import com.algocrafts.converters.Filter;
import com.algocrafts.converters.FirstItem;
import com.algocrafts.pages.Locators;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locator;

import static com.algocrafts.converters.ElementFunctions.CLICK_IF_NOT_NULL;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.selectors.ClassName.UI_DATEPICKER_CALENDAR;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;
import static com.algocrafts.selectors.TagName.TD;

public class JQueryDayLocatorFactory implements DayLocatorFactory {

    public Locator<AbstractPage, Void> forDay(int day) {
        return new JQueryDayLocator(
                Locators.<AbstractPage>element(UI_DATEPICKER_DIV)
                        .and(Locators.element(UI_DATEPICKER_CALENDAR))
                        .and(Locators.elements(TD))
                        .and(new Filter<>(TEXT.and(new IsStringEqual(day))))
                        .and(new FirstItem<Element>().and(CLICK_IF_NOT_NULL))
        );
    }


}
