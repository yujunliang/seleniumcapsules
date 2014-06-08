
package com.algocrafts.clickables;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Searchable;
import com.algocrafts.selenium.Locator;

public class Link<Where extends Searchable<Where>> extends Clickables<Where> {

    public Link(Where where, Locator<Where, Element> locator) {
        super(where, locator);
    }

}
