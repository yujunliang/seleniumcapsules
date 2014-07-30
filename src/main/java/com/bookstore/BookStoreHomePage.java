package com.bookstore;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Clickable;

import static com.algocrafts.conditions.PagePredicates.IS_COPYRIGHTED;

public class BookStoreHomePage extends Page {

    public BookStoreHomePage(Browsers browser, Clickable url) {
        super(browser, url, IS_COPYRIGHTED);
    }
}
