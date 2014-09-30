package com.orgsync;

import com.algocrafts.locators.ElementLocator;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.selectors.Id.MAIN_NAV;
import static org.openqa.selenium.By.linkText;

public class MenuGroupLocator implements Locator<Page, Element> {

    private final String headText;

    public MenuGroupLocator(String menuGroup) {
        this.headText = menuGroup;
    }

    public Element locate(Page page) {
        return new ElementLocator<Page>(MAIN_NAV)
                .and(new ElementLocator<>(() -> linkText(headText))).locate(page);
    }

    @Override
    public String toString() {
        return "[" + headText + "]";
    }
}
