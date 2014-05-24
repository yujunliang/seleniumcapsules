package com.algocrafts.conditions;

import com.algocrafts.locators.ElementLocator;
import com.algocrafts.locators.ElementTryLocator;
import com.algocrafts.pages.AbstractPage;

import java.util.function.Predicate;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.ElementPredicates.NOT_DISPLAYED;
import static com.algocrafts.conditions.StringEquals.*;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.searchmethods.Id.CONTENT;
import static com.algocrafts.searchmethods.Id.UI_DATEPICKER_DIV;
import static com.algocrafts.searchmethods.TagName.*;
import static com.manning.ManningId.*;

public enum PagePredicates implements Predicate<AbstractPage> {
    REACHED_CALENDAR_PAGE(
        new ElementLocator<AbstractPage>(CONTENT)
            .and(new ElementLocator<>(H1))
            .and(TEXT)
            .and(DATEPICKER)
    ),
    SHOPPING_CART_DISPLAYED(
        new ElementLocator<AbstractPage>(SHOPPING_CART)
            .and(DISPLAYED)
    ),
    YAHOO_COPYRIGHTED(
        new ElementLocator<AbstractPage>(YAHOO_COPYRIGHT)
            .and(new ElementLocator<>(EM))
            .and(TEXT)
            .and(YAHOO)
    ),
    IS_COPYRIGHTED(
        new ElementLocator<AbstractPage>(FOOTER)
            .and(new ElementLocator<>(P))
            .and(new ElementLocator<>(I))
            .and(TEXT)
            .and(MANNING)
    ),
    CALENDAR_NOT_DISPLAYED(
        new ElementTryLocator<AbstractPage>(UI_DATEPICKER_DIV)
            .and(NOT_DISPLAYED)
    );
    private final Predicate<AbstractPage> predicate;

    private PagePredicates(Predicate<AbstractPage> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(AbstractPage page) {
        return predicate.test(page);
    }

}
