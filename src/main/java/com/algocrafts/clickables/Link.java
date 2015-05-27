
package com.algocrafts.clickables;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.SearchScope;
import com.algocrafts.selenium.Locator;

public class Link<T extends SearchScope<T>> extends Clickables<T> {

    public Link(T where, Locator<T, Element> locator) {
        super(where, locator);
    }

}
