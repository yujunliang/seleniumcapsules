package com.algocrafts.locators;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locators;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class ElementLocator<Where extends Searchable<Where>>
        extends Locators<Where, Element> {

    public ElementLocator(Supplier<By> selector) {
        super((Where where) -> where.untilFound(selector.get()));
    }
}
