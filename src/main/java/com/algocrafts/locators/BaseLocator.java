package com.algocrafts.locators;

import com.algocrafts.pages.Locator;
import com.algocrafts.pages.Searchable;

public class BaseLocator<Where extends Searchable<Where>, What>
        implements Locator<Where, What> {

    private final Locator<Where, What> loctor;

    public BaseLocator(Locator<Where, What> locator) {
        this.loctor = locator;
    }

    @Override
    public What locate(Where where) {
        return loctor.locate(where);
    }
}