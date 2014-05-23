package com.algocrafts.clickables;

import com.algocrafts.pages.Locator;
import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Element;

public class Menu extends Button<AbstractPage> {

    public Menu(AbstractPage page, Locator<AbstractPage, Element> locator) {
        super(page, locator);
    }

}
