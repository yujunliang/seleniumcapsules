package com.algocrafts.selenium;

import java.util.function.Predicate;

public class Locating<Where extends Searchable<Where>, What> {

    protected final Where where;
    protected final Locator<Where, What> locator;

    /**
     * Constructor of the Locating.
     *
     * @param where   where
     * @param locator locator
     */
    public Locating(Where where, Locator<Where, What> locator) {
        this.where = where;
        this.locator = locator;
    }

    public What locate() {
        return locator.locate(where);
    }

    public <T> T locate(Locator<What, T> locator) {
        return this.locator.and(locator).locate(where);
    }

    public boolean test(Predicate<What> predicate) {
        return this.locator.and(predicate).test(where);
    }

}
