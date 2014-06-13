package com.algocrafts.pages;

import com.algocrafts.clickables.Url;
import com.algocrafts.conditions.Equals;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;

import java.util.function.Predicate;

import static com.algocrafts.converters.PageFunctions.THE_PAGE_TITLE;

public class Page extends AbstractPage {

    public static Page from(Browser browser, String url) {
        return new Page(browser, new Url<>(browser, url), null);
    }

    public Page(Page page) {
        this(page, null, (String) null);
    }

    public Page(Page page, Clickable clickable, String title) {
        this(page, clickable, THE_PAGE_TITLE.and(new Equals(title)));
    }

    public Page(Page page, Clickable clickable, Predicate<AbstractPage> condition) {
        super(page, clickable, condition);
    }

    public Page(Browser<?> browser) {
        this(browser, null, null, false);
    }

    public Page(Browser<?> browser, Clickable clickable){
        this(browser, clickable, null, false);
    }

    public Page(Browser<?> browser, Clickable clickable, Predicate<AbstractPage> condition) {
        this(browser, clickable, condition, false);
    }

    public Page(Browser<?> browser, Clickable clickable, Predicate<AbstractPage> condition, boolean close) {
        super(browser, clickable, condition, close);
    }

}