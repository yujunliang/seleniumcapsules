package com.daugherty;


import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Locator;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

public class DaughertyHomePage extends Page {

    final Locator<Page, Stream<Clickable>> allMenuLocator;

    public DaughertyHomePage(Browser<? extends WebDriver> browser, Clickable url, Locator<Page, Stream<Clickable>> allMenuLocator) {
        super(browser, url);
        this.allMenuLocator = allMenuLocator;
    }

    public Stream<Clickable> getAllMenu() {
        return allMenuLocator.apply(this);
    }
}
