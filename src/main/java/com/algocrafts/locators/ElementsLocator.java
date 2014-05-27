package com.algocrafts.locators;

import com.algocrafts.pages.Element;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ElementsLocator<Where extends Searchable<Where>>
        extends BaseLocator<Where, Stream<Element>> {

    public ElementsLocator(Supplier<By> selector) {
        super((Where where) -> where.findElements(() -> selector.get()));
    }
}
