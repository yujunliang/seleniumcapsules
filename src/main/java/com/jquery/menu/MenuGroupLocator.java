package com.jquery.menu;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

import static org.openqa.selenium.By.linkText;

public class MenuGroupLocator implements Locator<Page, Element> {

    private final String headText;

    public MenuGroupLocator(String menuGroup) {
        this.headText = menuGroup;
    }

    public Element locate(Page page) {
        return page.untilFound(() -> linkText(headText));
    }

    @Override
    public String toString() {
        return "[" + headText + "]";
    }
}
