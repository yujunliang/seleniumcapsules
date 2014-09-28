package com.algocrafts.conditions;

import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.Id;

import java.util.function.Predicate;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.ElementPredicates.NOT_NULL;
import static com.algocrafts.conditions.OptionalPresents.PRESENTS;
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
                    .and(element(H1))
                    .and(TEXT)
                    .and(DATEPICKER)
    ),
    SHOPPING_CART_DISPLAYED(
            Locators.<Page>element(SHOPPING_CART)
                    .and(NOT_NULL.and(DISPLAYED))
    ),
    YAHOO_COPYRIGHTED(
            Locators.<Page>element(YAHOO_COPYRIGHT)
                    .and(element(EM))
                    .and(TEXT)
                    .and(YAHOO)
    ),
    IS_COPYRIGHTED(
            Locators.<Page>element(FOOTER)
                    .and(element(P))
                    .and(element(I))
                    .and(TEXT)
                    .and(MANNING)
    ),
    EXTJS_CALENDAR_NOT_DISPLAYED(
            Locators.<Page>optionalElement(EXTJS_CALENDAR)
                    .and(PRESENTS.negate().or(GET.and(DISPLAYED.negate()))))
    ,
    JQUERY_CALENDAR_NOT_DISPLAYED(
            Locators.<Page>optionalElement(UI_DATEPICKER_DIV)
                    .and(PRESENTS.negate().or(GET.and(DISPLAYED.negate())))
    );
    private final Predicate<Page> predicate;

    private PagePredicates(Predicate<Page> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean test(Page page) {
        return predicate.test(page);
    }

}
