package com.algocrafts.chapter5;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import org.openqa.selenium.By;

public class MenuGroupLocator implements Locator<Page, Element> {

    private final String menuGroup;

    public MenuGroupLocator(String menuGroup) {
        this.menuGroup = menuGroup;
    }

    public Element locate(Page page) {
        return page.untilFound(() -> By.linkText(menuGroup));
    }

    @Override
    public String toString() {
        return "[" + menuGroup + "]";
    }
}