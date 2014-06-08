package com.algocrafts.locators;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public class ElementTryLocator<Where extends Searchable<Where>>
        extends Locators<Where, Element> {

    public ElementTryLocator(Supplier<By> selector) {
        super((Where where) ->
                        where.tryElement(selector)
        );
    }
}
