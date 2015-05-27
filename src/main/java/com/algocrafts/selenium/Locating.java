package com.algocrafts.selenium;

import java.util.function.Predicate;

public class Locating<T1 extends SearchScope<T1>, T2> {

    private final T1 where;
    private final Locator<T1, T2> locator;

    /**
     * Constructor of the Locating.
     *
     * @param where   where
     * @param locator locator
     */
    public Locating(T1 where, Locator<T1, T2> locator) {
        this.where = where;
        this.locator = locator;
    }

    public T2 locate() {
        return locator.locate(where);
    }

    public <T> T locate(Locator<T2, T> locator) {
        return this.locator.andThen(locator).locate(where);
    }

    public <T> T use(Locator<T1, T> locator) {
        return locator.locate(where);
    }

    public boolean test(Predicate<T2> predicate) {
        return this.locator.and(predicate).test(where);
    }

    @Override
    public String toString() {
        return locator.toString();
    }

}
