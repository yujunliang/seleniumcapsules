package com.algocrafts.locators;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class ElementLocator<T extends SearchScope<T>>
        extends Locators<T, Element> {

    public ElementLocator(Supplier<By> selector) {
        super((T where) -> where.untilFound(selector));
    }
}
