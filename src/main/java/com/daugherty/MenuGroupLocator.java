package com.daugherty;


import com.algocrafts.locators.ElementLocator;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.linkText;


public class MenuGroupLocator implements Locator<Page, Element> {

    private final String headText;
    private final Supplier<By> id;

    public MenuGroupLocator(String menuGroup, Supplier<By> id) {
        this.headText = menuGroup;
        this.id = id;
    }

    public Element locate(Page page) {
        return new ElementLocator<Page>(id)
                .andthen(new ElementLocator<>(() -> linkText(headText))).locate(page);
    }

    @Override
    public String toString() {
        return "[" + headText + "]";
    }
}
