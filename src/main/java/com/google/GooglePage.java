package com.google;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;

import static com.algocrafts.selectors.Name.Q;
import static com.algocrafts.selectors.VariableXpath.GOOGLE_AUTOCOMPLETE;

public class GooglePage extends Page {
    public GooglePage(Browser browser, Clickable clickable) {
        super(browser, clickable);
    }

    public void autocomplete(String input) {
        autocomplete(Q, input, GOOGLE_AUTOCOMPLETE);
    }
}
