package com.algocrafts.clickables;


import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.SearchScope;

public class Button<T extends SearchScope<T>> extends Clickables<T> {

    public Button(T where, Locator<T, Element> locator) {
        super(where, locator);
    }
}
