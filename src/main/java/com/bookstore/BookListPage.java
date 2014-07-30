package com.bookstore;


import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Clickable;

import java.util.function.Predicate;

public class BookListPage extends Page {

    public BookListPage(Page page) {
        super(page);
    }

    public BookListPage(Page page, Clickable clickable, Predicate<Page> condition) {
        super(page, clickable, condition);
    }
}
