package com.orgsync;


import com.algocrafts.conditions.Equals;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Locator;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

import static com.algocrafts.converters.PageFunctions.THE_PAGE_TITLE;

public class OrgSyncHomePage extends Page {

    private final Locator<Page, Stream<Clickable>> allMenuLocator;

    public OrgSyncHomePage(Browser<? extends WebDriver> browser,
                           Clickable url,
                           Locator<Page, Stream<Clickable>> allMenuLocator) {
        super(browser, url, THE_PAGE_TITLE.and(new Equals("")), false);
        this.allMenuLocator = allMenuLocator;
    }

    public Stream<Clickable> getAllMenu() {
        return allMenuLocator.locate(this);
    }
}
