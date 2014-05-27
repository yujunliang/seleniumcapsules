package com.algocrafts.locators;

import com.algocrafts.pages.Locator;
import com.algocrafts.pages.Searchable;
import org.openqa.selenium.By;

import java.util.function.Supplier;

public abstract class AbstractLocator<Where extends Searchable<Where>, What> implements Locator<Where, What> {

    protected final By by;
    private final Locator<Where, What> loctor;

    public AbstractLocator(Supplier<By> method, Locator<Where, What> loctor) {
        this.loctor = loctor;
        this.by = method.get();
    }

    @Override
    public What locate(Where where) {
        return loctor.locate(where);
    }

    @Override
    public String toString() {
        return by.toString();
    }
}