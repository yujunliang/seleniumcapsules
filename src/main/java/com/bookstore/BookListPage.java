package com.bookstore;


import com.algocrafts.pages.AbstractPage;
import com.algocrafts.selenium.Clickable;

import java.util.function.Predicate;

public class BookListPage extends AbstractPage {

    public BookListPage(AbstractPage page) {
        super(page);
    }

    public BookListPage(AbstractPage page, Clickable clickable, Predicate<AbstractPage> condition) {
        super(page, clickable, condition);
    }
}
