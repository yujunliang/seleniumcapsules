
package com.algocrafts.clickables;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.SearchScope;
import com.algocrafts.selenium.Locator;

public class Link<Where extends SearchScope<Where>> extends Clickables<Where> {

    public Link(Where where, Locator<Where, Element> locator) {
        super(where, locator);
    }

}
