package com.google;

import com.algocrafts.locators.ElementLocator;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Browser;
import com.algocrafts.pages.Clickable;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.searchmethods.Name.Q;


public class GooglePage extends AbstractPage {
    public GooglePage(Browser browser, Clickable clickable) {
        super(browser, clickable, new ElementLocator<AbstractPage>(Q).and(DISPLAYED));
    }
}
