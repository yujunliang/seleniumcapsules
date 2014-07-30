package com.algocrafts.clickables;

import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Element;

public class Menu extends Button<Page> {

    public Menu(Page page, Locator<Page, Element> locator) {
        super(page, locator);
    }

}
