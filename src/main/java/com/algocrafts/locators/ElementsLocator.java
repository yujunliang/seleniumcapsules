package com.algocrafts.locators;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class ElementsLocator<T extends SearchScope<T>>
        extends Locators<T, Stream<Element>> {

    public ElementsLocator(Supplier<By> selector) {
        super((T where)
                        -> where.findElements(selector)
        );
    }
}
