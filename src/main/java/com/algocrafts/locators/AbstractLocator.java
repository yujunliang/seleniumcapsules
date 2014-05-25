package com.algocrafts.locators;

import com.algocrafts.pages.Locator;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public abstract class AbstractLocator<Where extends Searchable<Where>, What> implements Locator<Where, What> {

    protected final By by;

    public AbstractLocator(Supplier<By> method) {
        this.by = method.get();
    }

    @Override
    public What apply(Where where) {
        return find(where);
    }

    protected abstract What find(Where where);

    @Override
    public String toString() {
        return by.toString();
    }
}