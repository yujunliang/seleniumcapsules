package com.algocrafts.clickables;

import com.algocrafts.pages.Clickable;
import com.algocrafts.pages.Element;
import com.algocrafts.pages.Locator;
import com.algocrafts.pages.Searchable;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Clickables<Where extends Searchable<Where>> implements Clickable {

    private static final Logger log = getLogger(Clickables.class);
    private final Where where;
    private final Locator<Where, Element> locator;

    public Clickables(Where where, Locator<Where, Element> locator) {
        this.locator = locator;
        this.where = where;
    }

    @Override
    public void click() {
        Element apply = locator.locate(where);
        log.info("clicking [" + apply + "]");
        where.save();
        apply.click();
        where.save();
    }

    @Override
    public String toString() {
        return locator.toString();
    }
}
