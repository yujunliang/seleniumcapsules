package com.daugherty;


import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Browser;
import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Locator;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static org.openqa.selenium.By.cssSelector;

public class DaughertyHomePage extends Page {

    final Locator<Page, Stream<Clickable>> allMenuLocator;

    public DaughertyHomePage(Browser<? extends WebDriver> browser, Clickable url, Locator<Page, Stream<Clickable>> allMenuLocator) {
        super(browser, url);
        this.allMenuLocator = allMenuLocator;
    }

    public Stream<Clickable> getAllMenu() {
        return allMenuLocator.apply(this);
    }

    public String getTitle() {
        try {
            return Locators.<Page>optionalElement(() -> cssSelector("body > header.page-header > div > h1 > span")).andThen(GET).andThen(TEXT).locate(this);
        } catch (Exception e) {
            return "";
        }
    }
}
