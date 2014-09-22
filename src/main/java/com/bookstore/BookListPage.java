package com.bookstore;


import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Clickable;

public class BookListPage extends Page {

    public BookListPage(Page page) {
        super(page);
    }

    public BookListPage(Page page, Clickable clickable) {
        super(page, clickable);
    }
}
