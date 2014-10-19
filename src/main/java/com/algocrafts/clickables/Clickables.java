package com.algocrafts.clickables;

import com.algocrafts.selenium.Clickable;
import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.SearchScope;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Clickables<Where extends SearchScope<Where>> implements Clickable {

    private static final Logger log = getLogger(Clickables.class);
    private final Where where;
    private final Locator<Where, Element> locator;

    public Clickables(Where where, Locator<Where, Element> locator) {
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
