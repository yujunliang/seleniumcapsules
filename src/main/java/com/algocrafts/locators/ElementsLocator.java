package com.algocrafts.locators;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ElementsLocator<Where extends SearchScope<Where>>
        extends Locators<Where, Stream<Element>> {

    public ElementsLocator(Supplier<By> selector) {
        super((Where where)
                        -> where.findElements(selector)
        );
    }
}
