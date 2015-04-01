package com.algocrafts.conditions;

import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;

import java.util.function.Predicate;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.ElementPredicates.NOT_NULL;
import static com.algocrafts.conditions.OptionalPresents.PRESENT;
import static com.algocrafts.conditions.StringEquals.*;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.Id.CONTENT;
import static com.algocrafts.selectors.Id.EXTJS_CALENDAR;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;
import static com.algocrafts.selectors.TagName.*;
import static com.bookstore.BookStoreId.*;

public enum PagePredicates implements Predicate<Page> {
    REACHED_CALENDAR_PAGE(
            Locators.<Page>element(CONTENT)
                    .andThen(element(H1))
                    .andThen(TEXT)
                    .and(DATEPICKER)
    ),
    SHOPPING_CART_DISPLAYED(
            Locators.<Page>element(SHOPPING_CART)
                    .and(NOT_NULL.and(DISPLAYED))
    ),
    YAHOO_COPYRIGHTED(
            Locators.<Page>element(YAHOO_COPYRIGHT)
                    .andThen(element(EM))
                    .andThen(TEXT)
                    .and(YAHOO)
    ),
    IS_COPYRIGHTED(
            Locators.<Page>element(FOOTER)
                    .andThen(element(P))
                    .andThen(element(I))
                    .andThen(TEXT)
                    .and(MANNING)
    ),
    EXTJS_CALENDAR_NOT_DISPLAYED(
            Locators.<Page>optionalElement(EXTJS_CALENDAR)
                    .and(PRESENT.negate().or(GET.and(DISPLAYED.negate()))))
    ,
    JQUERY_CALENDAR_NOT_DISPLAYED(
            Locators.<Page>optionalElement(UI_DATEPICKER_DIV)
                    .and(PRESENT.negate().or(GET.and(DISPLAYED.negate())))
    );
    private final Predicate<Page> predicate;

    PagePredicates(Predicate<Page> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(Page page) {
        return predicate.test(page);
    }

}
