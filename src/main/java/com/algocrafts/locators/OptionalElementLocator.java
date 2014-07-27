package com.algocrafts.locators;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalElementLocator<Where extends Searchable<Where>>
        extends Locators<Where, Optional<Element>> {

    public OptionalElementLocator(Supplier<By> selector) {
        super((Where where) ->
                        where.optionalElement(selector)
        );
    }
}
