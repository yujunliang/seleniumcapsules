package com.algocrafts.clickables;


import com.algocrafts.pages.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Searchable;

public class Button<Where extends Searchable<Where>> extends Clickables<Where> {

    public Button(Where where, Locator<Where, Element> locator) {
        super(where, locator);
    }
}
