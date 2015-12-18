package com.bookstore;


import com.algocrafts.browsers.Browsers;
import com.algocrafts.pages.Page;
import com.algocrafts.selectors.Xpath;
import com.algocrafts.selenium.Clickable;
import org.openqa.selenium.By;

import static com.algocrafts.selectors.Xpath.SEARCH_BUTTON;
import static com.bookstore.BookStoreId.SEARCH_INPUT;

public class BookstoreHomePage extends Page {

    public BookstoreHomePage(Browsers browser, Clickable url) {
        super(browser, url);
    }

    public void searchBook(String bookname) {
        put(SEARCH_INPUT, bookname);
        untilFound(SEARCH_BUTTON).click();
        untilFound(() -> By.partialLinkText(bookname)).click();
    }

}
