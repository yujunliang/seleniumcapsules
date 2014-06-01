package com.algocrafts.pages;

import com.algocrafts.locators.ElementLocator;
import com.algocrafts.locators.ElementTryLocator;
import com.algocrafts.locators.ElementsLocator;
import com.algocrafts.locators.SelectLocator;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Searchable;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class Locators<Where extends Searchable<Where>, What>
        implements Locator<Where, What> {

    public static <Where extends Searchable<Where>> Locators<Where, Element> element(Supplier<By> selector) {
        return new ElementLocator<>(selector);
    }

    public static <Where extends Searchable<Where>> Locators<Where, Stream<Element>> elements(Supplier<By> selector) {
        return new ElementsLocator<>(selector);
    }

    public static <Where extends Searchable<Where>> Locators<Where, Element> tryElement(Supplier<By> selector) {
        return new ElementTryLocator<>(selector);
    }

    public static <Where extends Searchable<Where>> Locators<Where, Select> select(Supplier<By> selector) {
        return new SelectLocator<>(selector);
    }

    private final Locator<Where, What> locator;

    public Locators(Locator<Where, What> locator) {
        this.locator = locator;
    }

    @Override
    public What locate(Where where) {
        return locator.locate(where);
    }
}