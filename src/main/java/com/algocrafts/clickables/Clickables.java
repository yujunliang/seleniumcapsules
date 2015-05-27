package com.algocrafts.clickables;

import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.SearchScope;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Clickables<T extends SearchScope<T>> implements Clickable {

    private static final Logger log = getLogger(Clickables.class);
    private final T where;
    private final Locator<T, Element> locator;

    public Clickables(T where, Locator<T, Element> locator) {
        this.locator = locator;
        this.where = where;
    }

    @Override
    public void click() {
        Element menu = locator.locate(where);
        log.info("clicking [" + menu + "]");
        menu.click();
    }

    @Override
    public String toString() {
        return locator.toString();
    }
}
