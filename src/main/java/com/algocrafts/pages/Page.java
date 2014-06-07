package com.algocrafts.pages;

import com.algocrafts.conditions.IsStringEqual;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;

import java.util.function.Predicate;

import static com.algocrafts.converters.PageFunctions.THE_PAGE_TITLE;

public class Page extends AbstractPage {

    public Page(Page page) {
        this(page, null, (String) null);
    }

    public Page(Page page, Clickable clickable, String title) {
        this(page, clickable, THE_PAGE_TITLE.and(new IsStringEqual(title)));
    }

    public Page(Page page, Clickable clickable, Predicate<AbstractPage> condition) {
        super(page, clickable, condition);
    }

    public Page(Browser<?> browser) {
        this(browser, null, null, false);
    }

    public Page(Browser<?> browser, Clickable clickable, Predicate<AbstractPage> condition) {
        this(browser, clickable, condition, false);
    }

    public Page(Browser<?> browser, Clickable clickable, Predicate<AbstractPage> condition, boolean close) {
        super(browser, clickable, condition, close);
    }

}