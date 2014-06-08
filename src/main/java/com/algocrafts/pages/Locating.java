package com.algocrafts.pages;


import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.Searchable;

public class Locating<Where extends Searchable<Where>, What>  {

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
}
