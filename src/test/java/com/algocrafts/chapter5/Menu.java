package com.algocrafts.chapter5;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Menu  {

    private static final Logger log = getLogger(Menu.class);
    private final Page page;
    private final Locator<Page, Element> locator;

    public Menu(Page page, Locator<Page, Element> locator) {
        this.locator = locator;
        this.page = page;
    }

    public void click() {
        Element menu = locator.locate(page);
        log.info("clicking [" + menu + "]");
        menu.click();
    }

    @Override
    public String toString() {
        return locator.toString();
    }
}
