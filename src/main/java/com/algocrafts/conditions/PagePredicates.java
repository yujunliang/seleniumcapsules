package com.algocrafts.conditions;

import com.algocrafts.locators.Locators;
import com.algocrafts.pages.AbstractPage;

import java.util.function.Predicate;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.StringEquals.*;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.Id.CONTENT;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;
import static com.algocrafts.selectors.TagName.*;
import static com.bookstore.BookStoreId.*;

public enum PagePredicates implements Predicate<AbstractPage> {
    REACHED_CALENDAR_PAGE(
            Locators.<AbstractPage>element(CONTENT)
                    .and(element(H1))
                    .and(TEXT)
                    .and(DATEPICKER)
    ),
    SHOPPING_CART_DISPLAYED(
            Locators.<AbstractPage>element(SHOPPING_CART)
                    .and(DISPLAYED)
    ),
    YAHOO_COPYRIGHTED(
            Locators.<AbstractPage>element(YAHOO_COPYRIGHT)
                    .and(element(EM))
                    .and(TEXT)
                    .and(YAHOO)
    ),
    IS_COPYRIGHTED(
            Locators.<AbstractPage>element(FOOTER)
                    .and(element(P))
                    .and(element(I))
                    .and(TEXT)
                    .and(MANNING)
    ),
    CALENDAR_NOT_DISPLAYED(
            Locators.<AbstractPage>tryElement(UI_DATEPICKER_DIV)
                    .and(DISPLAYED.negate())
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
