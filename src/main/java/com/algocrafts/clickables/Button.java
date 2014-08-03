package com.algocrafts.clickables;


import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.SearchScope;

public class Button<Where extends SearchScope<Where>> extends Clickables<Where> {

    public Button(Where where, Locator<Where, Element> locator) {
        super(where, locator);
    }
}
