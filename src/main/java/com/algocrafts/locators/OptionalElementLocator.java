package com.algocrafts.locators;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalElementLocator<T extends SearchScope<T>>
        extends Locators<T, Optional<Element>> {

    public OptionalElementLocator(Supplier<By> selector) {
        super((T where) ->
                        where.optionalElement(selector)
        );
    }
}
