package com.algocrafts.clickables;


import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locator;
import com.algocrafts.pages.Searchable;

public class Button<Where extends Searchable<Where>> extends Clickables<Where> {

    public Button(Where where, Locator<Where, Element> locator) {
        super(where, locator);
    }
}
