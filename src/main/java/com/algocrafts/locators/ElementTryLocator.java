package com.algocrafts.locators;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class ElementTryLocator<Where extends Searchable<Where>>
        extends BaseLocator<Where, Element> {

    public ElementTryLocator(Supplier<By> selector) {
        super((Where where) ->
                where.findElement(selector.get()));
    }
}