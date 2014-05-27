package com.algocrafts.locators;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class ElementLocator<Where extends Searchable<Where>>
        extends BaseLocator<Where, Element> {

    public ElementLocator(Supplier<By> selector) {
        super((Where where) -> where.untilFound(selector.get()));
    }
}