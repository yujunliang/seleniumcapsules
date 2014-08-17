package com.google;

import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.ElementPredicates.NOT_NULL;
import static com.algocrafts.selectors.Name.Q;
import static com.algocrafts.selectors.VariableXpath.AUTOCOMPLETE;

public class GooglePage extends Page {
    public GooglePage(Browser browser, Clickable clickable) {
        super(browser, clickable, Locators.<Page>element(Q).and(NOT_NULL.and(DISPLAYED)));
    }

    public void autocomplete(String input) {
        autocomplete(Q, input, AUTOCOMPLETE);
    }
}
