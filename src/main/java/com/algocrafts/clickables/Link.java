
package com.algocrafts.clickables;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import com.algocrafts.pages.Locator;

public class Link<Where extends Searchable<Where>> extends AbstractClicker<Where> {

    public Link(Where where, Locator<Where, Element> locator) {
        super(where, locator);
    }

}