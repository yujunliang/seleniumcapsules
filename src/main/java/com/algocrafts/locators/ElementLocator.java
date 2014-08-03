package com.algocrafts.locators;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class ElementLocator<Where extends SearchScope<Where>>
        extends Locators<Where, Element> {

    public ElementLocator(Supplier<By> selector) {
        super((Where where) -> where.untilFound(selector));
    }
}
