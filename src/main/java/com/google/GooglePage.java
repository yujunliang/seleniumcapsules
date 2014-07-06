package com.google;

import com.algocrafts.locators.Locators;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.conditions.ElementPredicates.NOT_NULL;
import static com.algocrafts.selectors.Name.Q;

public class GooglePage extends AbstractPage {
    public GooglePage(Browser browser, Clickable clickable) {
        super(browser, clickable, Locators.<AbstractPage>element(Q).and(NOT_NULL.and(DISPLAYED)));
    }
}
