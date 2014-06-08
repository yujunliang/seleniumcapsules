package com.bookstore;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.selenium.Clickable;
import com.algocrafts.pages.AbstractPage;

import static com.algocrafts.conditions.PagePredicates.IS_COPYRIGHTED;

public class BookStoreHomePage extends AbstractPage {

    public BookStoreHomePage(Browsers browser, Clickable url) {
        super(browser, url, IS_COPYRIGHTED);
    }
}
