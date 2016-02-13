package com.jquery.datepicker.locators;


import com.algocrafts.conditions.Equals;
import com.algocrafts.conditions.PagePredicates;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.datepicker.DayPicker;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;

import static com.algocrafts.converters.ElementFunctions.CLICK;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.ClassName.UI_DATEPICKER_CALENDAR;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;
import static com.algocrafts.selectors.TagName.TD;

public class JQueryDayPicker implements DayPicker {

    private Page browser;
    private PagePredicates jqueryCalendarNotDisplayed;

    public JQueryDayPicker(Page browser, PagePredicates jqueryCalendarNotDisplayed) {
        this.browser = browser;
        this.jqueryCalendarNotDisplayed = jqueryCalendarNotDisplayed;
    }

    public void pick(int day) {
        Locators.<Page>element(UI_DATEPICKER_DIV)
                .andThen(element(UI_DATEPICKER_CALENDAR))
                .andThen(elements(TD))
                .andThen(new FirstMatch<>(TEXT.and(new Equals(day))))
                .andThen(GET)
                .andThen(CLICK).locate(browser);
        browser.until(jqueryCalendarNotDisplayed);
    }
}
