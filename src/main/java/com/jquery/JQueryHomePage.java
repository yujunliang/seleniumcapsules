package com.jquery;


import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Locator;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

public class JQueryHomePage extends Page {

    private final Locator<Page, Stream<Clickable>> allMenuLocator;

    public JQueryHomePage(Browser<? extends WebDriver> browser,
                          Clickable url,
                          Locator<Page, Stream<Clickable>> allMenuLocator) {
        super(browser, url);
        this.allMenuLocator = allMenuLocator;
    }

    public Stream<Clickable> getAllMenu() {
        return allMenuLocator.locate(this);
    }
}
