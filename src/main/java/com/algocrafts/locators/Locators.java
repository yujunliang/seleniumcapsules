package com.algocrafts.locators;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Locators<T1 extends SearchScope<T1>, T2>
        implements Locator<T1, T2> {

    public static <T extends SearchScope<T>> Locators<T, Element> element(Supplier<By> selector) {
        return new ElementLocator<>(selector);
    }

    public static <T extends SearchScope<T>> Locators<T, Stream<Element>> elements(Supplier<By> selector) {
        return new ElementsLocator<>(selector);
    }

    public static <T extends SearchScope<T>> Locators<T, Optional<Element>> optionalElement(Supplier<By> selector) {
        return new OptionalElementLocator<>(selector);
    }

    public static SelectLocator select(Supplier<By> selector) {
        return new SelectLocator(selector);
    }

    private final Locator<T1, T2> locator;

    public Locators(Locator<T1, T2> locator) {
        this.locator = locator;
    }

    @Override
    public T2 locate(T1 where) {
        return locator.locate(where);
    }
}